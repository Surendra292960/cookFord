package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.domain.use_cases.authenticated_use_case.TimeSlotsUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.afterNoonEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.eveningEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.morningEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageTimeSlotsViewModel @Inject constructor(private val userSession: UserSession, private val timeSlotUseCase: TimeSlotsUseCase) :
    ViewModel() {

    private val _manageTimeSlotsState: MutableStateFlow<ManageTimeSlotsState>  = MutableStateFlow(ManageTimeSlotsState())
    val manageTimeSlotsState: StateFlow<ManageTimeSlotsState> = _manageTimeSlotsState

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private val _onProcessSuccess = MutableSharedFlow<String>()
    val onProcessSuccess = _onProcessSuccess.asSharedFlow()

    init {
        getTimeSlotsRequest()
    }

    /**
     * Function called on any ManageTimeSlots event [ManageTimeSlotsUiEvent]
     */
    fun onUiEvent(manageTimeSlotsUiEvent: ManageTimeSlotsUiEvent) {
        when (manageTimeSlotsUiEvent) {

            // morning changed event
            is ManageTimeSlotsUiEvent.MorningChange -> {
                _manageTimeSlotsState.value = _manageTimeSlotsState.value.copy(
                    morning = manageTimeSlotsUiEvent.inputValue,
                    errorState = _manageTimeSlotsState.value.errorState.copy(
                        morningErrorState = if (manageTimeSlotsUiEvent.inputValue.isEmpty()) {
                            // morning empty state
                            morningEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // afternoon changed event
            is ManageTimeSlotsUiEvent.AfternoonChange -> {
                _manageTimeSlotsState.value = _manageTimeSlotsState.value.copy(
                    afternoon = manageTimeSlotsUiEvent.inputValue,
                    errorState = _manageTimeSlotsState.value.errorState.copy(
                        afternoonErrorState = if (manageTimeSlotsUiEvent.inputValue.isEmpty()) {
                            // afternoon empty state
                            afterNoonEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // evening changed event
            is ManageTimeSlotsUiEvent.EveningChange -> {
                _manageTimeSlotsState.value = _manageTimeSlotsState.value.copy(
                    evening = manageTimeSlotsUiEvent.inputValue,
                    errorState = _manageTimeSlotsState.value.errorState.copy(
                        eveningErrorState = if (manageTimeSlotsUiEvent.inputValue.isEmpty()) {
                            // evening empty state
                            eveningEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Submit SignUp event
            is ManageTimeSlotsUiEvent.Submit -> {
                val inputsValidated = timeSlotUseCase.invoke(_manageTimeSlotsState)
                if (inputsValidated) {
                    _viewState.update { currentState -> currentState.copy(isLoading = true) }
                    // TODO Trigger registration in authentication flow
                    Log.d("TAG", "onUiEvent ManageTimeSlotsUiEvent: ${Gson().toJson(_manageTimeSlotsState.value)}")
                }
            }
        }
    }

    private fun getTimeSlotsRequest() = viewModelScope.launch(IO) {
        _viewState.update { currentState -> currentState.copy(isLoading = true) }
        timeSlotUseCase.invoke().collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        Log.d("TAG", "makeTimeSlotsRequest timeSlotsResponse: ${Gson().toJson(result)}")
                        result.data?.let { response->
                            _manageTimeSlotsState.emit(manageTimeSlotsState.value.copy(timeSlotsResponse = response))
                            _manageTimeSlotsState.value = _manageTimeSlotsState.value.copy(isSuccessful = result.status)
                            _viewState.update { currentState -> currentState.copy(isLoading = false) }
                        }
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeTimeSlotsRequest: ${result.message}")
                    _viewState.update { currentState -> currentState.copy(isLoading = false) }
                    _onProcessSuccess.emit(result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeTimeSlotsRequest: Loading")
                }
            }
        }
    }
}
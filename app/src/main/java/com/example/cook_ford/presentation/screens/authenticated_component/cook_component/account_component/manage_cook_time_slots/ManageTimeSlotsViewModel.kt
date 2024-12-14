package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.afterNoonEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.eveningEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.morningEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ManageTimeSlotsViewModel @Inject constructor(private val userSession: UserSession) :
    ViewModel() {

    private val _manageTimeSlotsState = mutableStateOf(ManageTimeSlotsState())
    val manageTimeSlotsState: State<ManageTimeSlotsState> = _manageTimeSlotsState

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()


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
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    _viewState.update { currentState -> currentState.copy(isLoading = true) }
                    // TODO Trigger registration in authentication flow
                    Log.d("TAG", "onUiEvent ManageTimeSlotsUiEvent: ${Gson().toJson(_manageTimeSlotsState.value)}")
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val morning = manageTimeSlotsState.value.morning
        val afterNoon = manageTimeSlotsState.value.afternoon
        val evening = manageTimeSlotsState.value.evening


        // morning empty
        if (morning.isEmpty()) {
            _manageTimeSlotsState.value = _manageTimeSlotsState.value.copy(
                errorState = ManageTimeSlotsErrorState(
                    morningErrorState = morningEmptyErrorState
                )
            )
            return false
        }
        // afternoon empty
        if (afterNoon.isEmpty()) {
            _manageTimeSlotsState.value = _manageTimeSlotsState.value.copy(
                errorState = ManageTimeSlotsErrorState(
                    afternoonErrorState = afterNoonEmptyErrorState
                )
            )
            return false
        }
        // morning empty
        if (evening.isEmpty()) {
            _manageTimeSlotsState.value = _manageTimeSlotsState.value.copy(
                errorState = ManageTimeSlotsErrorState(
                    eveningErrorState = eveningEmptyErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            _manageTimeSlotsState.value = _manageTimeSlotsState.value.copy(errorState = ManageTimeSlotsErrorState())
            return true
        }
    }
}
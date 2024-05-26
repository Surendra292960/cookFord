package com.example.cook_ford.presentation.screens.report

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.report.state.ReportErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.cook_ford.presentation.screens.report.state.reportEmptyErrorState
import com.example.cook_ford.presentation.screens.report.state.ReportState
import com.example.cook_ford.presentation.screens.report.state.ReportUiEvent


@HiltViewModel
class ReportViewModel  @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _reportState = MutableStateFlow(ReportState())
    val reportState = _reportState.asStateFlow()

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    init {
        getProfileId()?.let {
            Log.d("TAG", " stateHandle  : $it")
            getProfileId()?.let { makeProfileRequestForReview(profileId = it) }
        }
    }

    fun getProfileId() = stateHandle.get<String>(AppConstants.PROFILE_ID)

    fun onUiEvent(reportUiEvent: ReportUiEvent) {
        when (reportUiEvent) {
            //Report changed
            is ReportUiEvent.ReportChanged -> {
                _reportState.value = _reportState.value.copy(
                    report = reportUiEvent.inputValue,
                    errorState = _reportState.value.errorState.copy(
                        reportErrorState = if (reportUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            reportEmptyErrorState
                    )
                )
            }

            ReportUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                Log.d("TAG", "onUiEvent: $inputsValidated")
                if (inputsValidated) {
                    // TODO Trigger login in authentication flow
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val report = _reportState.value.report.trim()

        // Review empty
        if (report.isEmpty()) {
            _reportState.value = _reportState.value.copy(
                errorState = ReportErrorState(
                    reportErrorState = reportEmptyErrorState
                )
            )
            return false
        }
        // No errors
        else {
            // Set default error state
            _reportState.value = _reportState.value.copy(errorState = ReportErrorState())
            return true
        }
    }

    private fun makeProfileRequestForReview(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequestForReview-> profileId: $profileId")
        //_reportState.value = _reportState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _reportState.value = _reportState.value.copy(isLoading = false, profile = response, isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequestForReview->: ${Gson().toJson(_reportState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequestForReview-> Error: ${Gson().toJson(_reportState.value)}")
                    _reportState.value = _reportState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequestForReview->: Loading")
                    _reportState.value = _reportState.value.copy(isLoading = true)
                }
            }
        }
    }
}
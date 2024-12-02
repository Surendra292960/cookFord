package com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.report_screen_component

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.report_screen_component.state.ReportState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.report_screen_component.state.ReportErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.report_screen_component.state.reportEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.report_screen_component.state.ReportUiEvent
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.report_screen_component.state.issueEmptyErrorState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


@HiltViewModel
class ReportViewModel  @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    val selectedItem = mutableSetOf<String>()

    private val _reportState = mutableStateOf(ReportState())
    val reportState : State<ReportState> = _reportState

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private val _onProcessSuccess = MutableSharedFlow<String>()
    val onProcessSuccess = _onProcessSuccess.asSharedFlow()

    val issueList = listOf("issue in cook`s food", "profile not updated", "issue in cook`s profile")


    fun onUiEvent(reportUiEvent: ReportUiEvent) {
        when (reportUiEvent) {

            //Issue changed
            is ReportUiEvent.IssueChanged -> {
                Log.d("TAG", "onUiEvent: ${reportUiEvent.inputValue}")
                _reportState.value = _reportState.value.copy(
                    issue = reportUiEvent.inputValue,
                    errorState = _reportState.value.errorState.copy(
                       reportErrorState = if (reportUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            issueEmptyErrorState
                    )
                )
            }
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
        val issue = _reportState.value.issue.trim()

        // Issue empty
        if (issue.isEmpty()) {
            Log.d("TAG", "validateInputs: issue isEmpty")
            viewModelScope.launch {
                _onProcessSuccess.emit("Please select issue")
                _reportState.value = _reportState.value.copy(
                    errorState = ReportErrorState(
                        issueErrorState = issueEmptyErrorState
                    )
                )
            }
            return false
        }
        // Review empty
        if (report.isEmpty()) {
            Log.d("TAG", "validateInputs: report isEmpty")
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
                            _reportState.value = _reportState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
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

    fun setProfileData(profileResponse: ProfileResponse?) {
        _reportState.value = _reportState.value.copy(isLoading = false, profileResponse = profileResponse, isSuccessful = true)
    }
}
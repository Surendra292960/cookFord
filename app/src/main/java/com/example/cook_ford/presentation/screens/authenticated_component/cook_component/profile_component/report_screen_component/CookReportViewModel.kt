package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.domain.use_cases.authenticated_use_case.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.state.CookReportErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.state.CookReportState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.state.CookReportUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.state.cookIssueEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.state.cookReportEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CookReportViewModel  @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    val selectedItem = mutableSetOf<String>()

    private val _cookReportState = mutableStateOf(CookReportState())
    val cookReportState : State<CookReportState> = _cookReportState

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private val _onProcessSuccess = MutableSharedFlow<String>()
    val onProcessSuccess = _onProcessSuccess.asSharedFlow()

    val issueList = listOf("issue in cook`s food", "profile not updated", "issue in cook`s profile")


    fun onUiEvent(reportUiEvent: CookReportUiEvent) {
        when (reportUiEvent) {

            //Issue changed
            is CookReportUiEvent.CookIssueChanged -> {
                Log.d("TAG", "onUiEvent: ${reportUiEvent.inputValue}")
                _cookReportState.value = _cookReportState.value.copy(
                    cookIssue = reportUiEvent.inputValue,
                    errorState = _cookReportState.value.errorState.copy(
                       reportErrorState = if (reportUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cookIssueEmptyErrorState
                    )
                )
            }
            //Report changed
            is CookReportUiEvent.CookReportChanged -> {
                _cookReportState.value = _cookReportState.value.copy(
                    cookReport = reportUiEvent.inputValue,
                    errorState = _cookReportState.value.errorState.copy(
                        reportErrorState = if (reportUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cookReportEmptyErrorState
                    )
                )
            }

            CookReportUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                Log.d("TAG", "onUiEvent: $inputsValidated")
                if (inputsValidated) {
                    // TODO Trigger login in authentication flow
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val report = _cookReportState.value.cookReport.trim()
        val issue = _cookReportState.value.cookIssue.trim()

        // Issue empty
        if (issue.isEmpty()) {
            Log.d("TAG", "validateInputs: issue isEmpty")
            viewModelScope.launch {
                _onProcessSuccess.emit("Please select issue")
                _cookReportState.value = _cookReportState.value.copy(
                    errorState = CookReportErrorState(
                        issueErrorState = cookIssueEmptyErrorState
                    )
                )
            }
            return false
        }
        // Review empty
        if (report.isEmpty()) {
            Log.d("TAG", "validateInputs: report isEmpty")
            _cookReportState.value = _cookReportState.value.copy(
                errorState = CookReportErrorState(
                    reportErrorState = cookReportEmptyErrorState
                )
            )
            return false
        }
        // No errors
        else {
            // Set default error state
            _cookReportState.value = _cookReportState.value.copy(errorState = CookReportErrorState())
            return true
        }
    }

    private fun makeProfileRequestForReview(authToken:String, profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequestForReview-> profileId: $profileId")
        //_cookReportState.value = _cookReportState.value.copy(isLoading = true)
        profileUseCase.invoke(authToken, profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _cookReportState.value = _cookReportState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequestForReview->: ${Gson().toJson(_cookReportState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequestForReview-> Error: ${Gson().toJson(_cookReportState.value)}")
                    _cookReportState.value = _cookReportState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequestForReview->: Loading")
                    _cookReportState.value = _cookReportState.value.copy(isLoading = true)
                }
            }
        }
    }

    fun setProfileData(profileResponse: ProfileResponse?) {
        _cookReportState.value = _cookReportState.value.copy(isLoading = false, profileResponse = profileResponse, isSuccessful = true)
    }
}
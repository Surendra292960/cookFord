package com.example.cook_ford.presentation.screens.authenticated_screen_component.user_component.profile_component.report_screen_component.state
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING


data class ReportState(
    val report: String = EMPTY_STRING,
    val issue: String = EMPTY_STRING,
    val errorState: ReportErrorState = ReportErrorState(),
    val isLoading: Boolean = false,
    val profileResponse: ProfileResponse? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)

/**
 * Error state in report holding respective
 * text field validation errors
 */
data class ReportErrorState(
    val reportErrorState: ErrorState = ErrorState(),
    val issueErrorState: ErrorState = ErrorState(),
)
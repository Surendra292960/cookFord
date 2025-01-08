package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.state
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING


data class CookReportState(
    val cookReport: String = EMPTY_STRING,
    val cookIssue: String = EMPTY_STRING,
    val errorState: CookReportErrorState = CookReportErrorState(),
    val isLoading: Boolean = false,
    val profileResponse: ProfileResponse? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)

/**
 * Error state in report holding respective
 * text field validation errors
 */
data class CookReportErrorState(
    val reportErrorState: ErrorState = ErrorState(),
    val issueErrorState: ErrorState = ErrorState(),
)
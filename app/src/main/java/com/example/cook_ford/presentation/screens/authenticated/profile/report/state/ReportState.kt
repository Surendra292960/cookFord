package com.example.cook_ford.presentation.screens.authenticated.profile.report.state
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState
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
package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState


val cookReportEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.report_error_msg_empty
)

val cookIssueEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.issue_error_msg_empty
)
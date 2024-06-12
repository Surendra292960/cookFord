package com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.report_screen_component.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState


val reportEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.report_error_msg_empty
)

val issueEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.issue_error_msg_empty
)
package com.example.cook_ford.presentation.screens.report.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState


val reportEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.report_error_msg_empty
)
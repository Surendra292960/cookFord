package com.example.cook_ford.presentation.screens.sign_in.state

import com.example.cook_ford.R

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_in_error_msg_empty_email
)

val phoneEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_in_error_msg_empty_mobile
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_in_error_msg_empty_password
)
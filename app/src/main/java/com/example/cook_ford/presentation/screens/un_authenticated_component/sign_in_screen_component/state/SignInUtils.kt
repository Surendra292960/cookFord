package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state

import com.example.cook_ford.R

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_in_error_msg_empty_email
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_in_error_msg_empty_password
)

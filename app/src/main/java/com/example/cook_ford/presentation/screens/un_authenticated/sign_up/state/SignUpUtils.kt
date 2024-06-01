package com.example.cook_ford.presentation.screens.un_authenticated.sign_up.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState

val usernameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_name
)
val invalidUserNameErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_valid_email
)

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_email
)

val phoneEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_in_error_msg_empty_mobile
)

val invalidPasswordErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_valid_password
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_password
)

val confirmPasswordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_confirm_password
)

val passwordMismatchErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_password_mismatch
)

package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.cook_sign_up.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState

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
    errorMessageStringResource = R.string.sign_up_error_msg_empty_mobile
)

val otpEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_otp
)

val invalidPhoneEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_valid_mobile
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

val genderSelectionErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_gender_not_selected
)

val addressEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_address
)

val cityErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_city
)

val stateErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_state
)

val zipCodeErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_zip_code
)

package com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.add_cook_address.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState

val addressEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_address
)

val cityEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_city
)

val stateEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_state
)

val zipCodeEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.sign_up_error_msg_empty_zip_code
)

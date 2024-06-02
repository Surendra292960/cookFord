package com.example.cook_ford.presentation.screens.authenticated.account.cook.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState

val cook_nameEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.cook_error_msg_empty_name
)

val cook_phoneEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.cook_error_msg_empty_phone
)


val cook_alternate_phoneEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.cook_error_msg_empty_alternate_phone
)


package com.example.cook_ford.presentation.screens.authenticated.accounts.cook_screen_component.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState

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


val cook_cityEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.cook_error_msg_empty_city
)



val cook_jobTypeEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.cook_error_msg_empty_job_type
)



val cook_profileImageEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.cook_error_msg_empty_profile_image
)


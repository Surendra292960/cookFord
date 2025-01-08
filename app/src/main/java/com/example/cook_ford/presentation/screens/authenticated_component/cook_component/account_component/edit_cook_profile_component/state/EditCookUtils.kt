package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState

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

val cook_cuisineEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.cook_error_msg_empty_cuisine
)

val cook_languageEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.cook_error_msg_empty_language
)


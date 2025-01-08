package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState


val morningEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.time_slots_error_msg_empty_morning
)

val afterNoonEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.time_slots_error_msg_empty_afternoon
)

val eveningEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.time_slots_error_msg_empty_morning
)

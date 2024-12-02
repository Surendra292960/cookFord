package com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.details_screen_component.state.note_satate

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState


val noteEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.note_error_msg_empty
)

val reviewEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.review_error_msg_empty
)

val ratingEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.rating_error_msg_empty
)
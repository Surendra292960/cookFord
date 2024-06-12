package com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.reviews_screen_component.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState


val reviewEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.review_error_msg_empty
)
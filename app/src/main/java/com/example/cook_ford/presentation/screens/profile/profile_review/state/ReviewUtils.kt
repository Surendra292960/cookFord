package com.example.cook_ford.presentation.screens.profile.profile_review.state

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState


val reviewEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.review_error_msg_empty
)
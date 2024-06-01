package com.example.cook_ford.presentation.screens.authenticated.profile.details.state.note_satate

import com.example.cook_ford.R
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState


val noteEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.note_error_msg_empty
)
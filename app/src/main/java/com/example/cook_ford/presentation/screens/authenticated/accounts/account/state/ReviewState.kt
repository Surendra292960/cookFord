package com.example.cook_ford.presentation.screens.authenticated.accounts.account.state

import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants

data class ReviewState(
    val review: String = AppConstants.EMPTY_STRING,
    val rating: Float =0.0f,
    val errorState: ReviewErrorState = ReviewErrorState(),
    val isLoading: Boolean = false,
    val profile: ProfileResponse? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)

/**
 * Error state in Note holding respective
 * text field validation errors
 */
data class ReviewErrorState(
    val reviewErrorState: ErrorState = ErrorState(),
    val ratingErrorState: ErrorState = ErrorState(),
)
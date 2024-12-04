package com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.account_screen_component.state

import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants

data class CookReviewState(
    val review: String = AppConstants.EMPTY_STRING,
    val rating: Float =0.0f,
    val errorState: CookReviewErrorState = CookReviewErrorState(),
    val isLoading: Boolean = false,
    val profile: ProfileResponse? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)

/**
 * Error state in Note holding respective
 * text field validation errors
 */
data class CookReviewErrorState(
    val reviewErrorState: ErrorState = ErrorState(),
    val ratingErrorState: ErrorState = ErrorState(),
)
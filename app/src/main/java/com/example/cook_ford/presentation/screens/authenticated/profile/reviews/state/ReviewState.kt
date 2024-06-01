package com.example.cook_ford.presentation.screens.authenticated.profile.reviews.state
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

data class ReviewState(
    val review: String = EMPTY_STRING,
    val errorState: ReviewErrorState = ReviewErrorState(),
    val isLoading: Boolean = false,
    val profile: ProfileResponse? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)

/**
 * Error state in review holding respective
 * text field validation errors
 */
data class ReviewErrorState(
    val reviewErrorState: ErrorState = ErrorState(),
)
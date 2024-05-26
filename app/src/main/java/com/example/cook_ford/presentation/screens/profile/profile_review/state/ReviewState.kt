package com.example.cook_ford.presentation.screens.profile.profile_review.state
import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

data class ReviewState(
    val review: String = EMPTY_STRING,
    val errorState: ReviewErrorState = ReviewErrorState(),
    var isSignInSuccessful: Boolean = false
)

/**
 * Error state in review holding respective
 * text field validation errors
 */
data class ReviewErrorState(
    val reviewErrorState: ErrorState = ErrorState(),
)
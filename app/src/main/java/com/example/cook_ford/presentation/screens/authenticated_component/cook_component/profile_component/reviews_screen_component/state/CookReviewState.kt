package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

data class CookReviewState(
    val cookReview: String = EMPTY_STRING,
    val errorState: CookReviewErrorState = CookReviewErrorState(),
    val isLoading: Boolean = false,
    val profileResponse: ProfileResponse? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)

/**
 * Error state in review holding respective
 * text field validation errors
 */
data class CookReviewErrorState(
    val reviewErrorState: ErrorState = ErrorState(),
)
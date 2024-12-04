package com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.account_screen_component.state
sealed class CookReviewUiEvent {
    data class ReViewChanged(val inputValue: String) : CookReviewUiEvent()
    data class RatingChanged(val inputValue: Float) : CookReviewUiEvent()
    data object Submit : CookReviewUiEvent()
}
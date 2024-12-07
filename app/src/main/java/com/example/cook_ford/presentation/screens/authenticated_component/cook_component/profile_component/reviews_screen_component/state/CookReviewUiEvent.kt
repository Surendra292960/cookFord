package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state

sealed class CookReviewUiEvent {
    data class CookReviewChanged(val inputValue: String) : CookReviewUiEvent()
    data object Submit : CookReviewUiEvent()
}
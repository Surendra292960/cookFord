package com.example.cook_ford.presentation.screens.profile.profile_review.state

sealed class ReviewUiEvent {
    data class ReviewChanged(val inputValue: String) : ReviewUiEvent()
    data object Submit : ReviewUiEvent()
}
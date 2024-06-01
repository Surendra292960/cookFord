package com.example.cook_ford.presentation.screens.authenticated.profile.reviews.state

sealed class ReviewUiEvent {
    data class ReviewChanged(val inputValue: String) : ReviewUiEvent()
    data object Submit : ReviewUiEvent()
}
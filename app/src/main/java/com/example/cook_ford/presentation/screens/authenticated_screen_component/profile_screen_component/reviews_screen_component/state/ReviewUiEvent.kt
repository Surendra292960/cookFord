package com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.reviews_screen_component.state

sealed class ReviewUiEvent {
    data class ReviewChanged(val inputValue: String) : ReviewUiEvent()
    data object Submit : ReviewUiEvent()
}
package com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.account_screen_component.state

sealed class ReviewUiEvent {
    data class ReViewChanged(val inputValue: String) : ReviewUiEvent()
    data class RatingChanged(val inputValue: Float) : ReviewUiEvent()
    data object Submit : ReviewUiEvent()
}
package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.account_screen_component.state

sealed class ReviewUiEvent {
    data class ReViewChanged(val inputValue: String) : ReviewUiEvent()
    data class RatingChanged(val inputValue: Float) : ReviewUiEvent()
    data object Submit : ReviewUiEvent()
}
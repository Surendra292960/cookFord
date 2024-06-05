package com.example.cook_ford.presentation.screens.authenticated.account.accounts.state

sealed class ReviewUiEvent {
    data class ReViewChanged(val inputValue: String) : ReviewUiEvent()
    data class RatingChanged(val inputValue: Float) : ReviewUiEvent()
    data object Submit : ReviewUiEvent()
}
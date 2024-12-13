package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_cuisine_themes.state

sealed class CuisineUiEvent {
    data class OneImageChange(val inputValue: String) : CuisineUiEvent()
    data class TwoImageChange(val inputValue: String) : CuisineUiEvent()
    data class ThreeImageChange(val inputValue: String) : CuisineUiEvent()
    data class FourImageChange(val inputValue: String) : CuisineUiEvent()
    data class FiveImageChange(val inputValue: String) : CuisineUiEvent()
    data class SixImageChange(val inputValue: String) : CuisineUiEvent()
    data object Submit : CuisineUiEvent()
}

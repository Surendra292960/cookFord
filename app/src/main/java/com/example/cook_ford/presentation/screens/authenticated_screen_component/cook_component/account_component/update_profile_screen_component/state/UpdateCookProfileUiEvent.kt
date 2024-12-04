package com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.update_profile_screen_component.state

sealed class UpdateCookProfileUiEvent {
    data class UserNameChanged(val inputValue: String) : UpdateCookProfileUiEvent()
    data class EmailChanged(val inputValue: String) : UpdateCookProfileUiEvent()
    data class PhoneChanged(val inputValue: String) : UpdateCookProfileUiEvent()
    data class GenderChange(val inputValue: String) : UpdateCookProfileUiEvent()
    data class ProfileImageChanged(val inputValue: String) : UpdateCookProfileUiEvent()
    data object Submit : UpdateCookProfileUiEvent()
}
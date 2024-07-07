package com.example.cook_ford.presentation.screens.authenticated.accounts.update_profile_screen_component.state

sealed class UpdateProfileUiEvent {
    data class UserNameChanged(val inputValue: String) : UpdateProfileUiEvent()
    data class EmailChanged(val inputValue: String) : UpdateProfileUiEvent()
    data class PhoneChanged(val inputValue: String) : UpdateProfileUiEvent()
    data class GenderChange(val inputValue: String) : UpdateProfileUiEvent()
    data class ProfileImageChanged(val inputValue: String) : UpdateProfileUiEvent()
    data object Submit : UpdateProfileUiEvent()
}
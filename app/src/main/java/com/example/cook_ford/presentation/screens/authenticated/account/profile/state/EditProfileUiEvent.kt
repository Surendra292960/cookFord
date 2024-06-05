package com.example.cook_ford.presentation.screens.authenticated.account.profile.state

sealed class EditProfileUiEvent {
    data class UserNameChanged(val inputValue: String) : EditProfileUiEvent()
    data class EmailChanged(val inputValue: String) : EditProfileUiEvent()
    data class PhoneChanged(val inputValue: String) : EditProfileUiEvent()
    data class GenderChange(val inputValue: String) : EditProfileUiEvent()
    data class ProfileImageChanged(val inputValue: String) : EditProfileUiEvent()
    data object Submit : EditProfileUiEvent()
}
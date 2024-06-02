package com.example.cook_ford.presentation.screens.authenticated.account.cook.state

sealed class AddCookProfileUiEvent {
    data class UserNameChanged(val inputValue: String) : AddCookProfileUiEvent()
    data class PhoneChanged(val inputValue: String) : AddCookProfileUiEvent()
    data class AlternatePhoneChanged(val inputValue: String) : AddCookProfileUiEvent()
    data class GenderChange(val inputValue: String) : AddCookProfileUiEvent()
    data object Submit : AddCookProfileUiEvent()
}
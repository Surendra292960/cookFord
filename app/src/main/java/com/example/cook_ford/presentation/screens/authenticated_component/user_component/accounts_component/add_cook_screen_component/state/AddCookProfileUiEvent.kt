package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.add_cook_screen_component.state

sealed class AddCookProfileUiEvent {
    data class UserNameChanged(val inputValue: String) : AddCookProfileUiEvent()
    data class PhoneChanged(val inputValue: String) : AddCookProfileUiEvent()
    data class ProfileImageChanged(val inputValue: String) : AddCookProfileUiEvent()
    data class AlternatePhoneChanged(val inputValue: String) : AddCookProfileUiEvent()
    data class CityChanged(val inputValue: String) : AddCookProfileUiEvent()
    data class GenderChange(val inputValue: String) : AddCookProfileUiEvent()
    data class JobTypeChange(val inputValue: MutableSet<String>) : AddCookProfileUiEvent()
    data object Submit : AddCookProfileUiEvent()
}
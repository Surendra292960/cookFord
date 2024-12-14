package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state

/**
 * SignUp Screen Events
 */
sealed class CookPersonalInfoUiEvent {
    data class FirstNameChange(val inputValue: String) : CookPersonalInfoUiEvent()
    data class LastNameChange(val inputValue: String) : CookPersonalInfoUiEvent()
    data class AddressChange(val inputValue: String) : CookPersonalInfoUiEvent()
    data class CityChange(val inputValue: String) : CookPersonalInfoUiEvent()
    data class StateChange(val inputValue: String) : CookPersonalInfoUiEvent()
    data class ZipCodeChange(val inputValue: String) : CookPersonalInfoUiEvent()
    data object Submit : CookPersonalInfoUiEvent()
}
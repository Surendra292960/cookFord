package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.cook_sign_up.state

/**
 * SignUp Screen Events
 */
sealed class CookUiEvent {
    data class UserNameChanged(val inputValue: String) : CookUiEvent()
    data class EmailChanged(val inputValue: String) : CookUiEvent()
    data class PasswordChanged(val inputValue: String) : CookUiEvent()
    data class ConfirmPasswordChanged(val inputValue: String) : CookUiEvent()
    data class GenderChange(val inputValue: String) : CookUiEvent()
    data class AddressChange(val inputValue: String) : CookUiEvent()
    data class CityChange(val inputValue: String) : CookUiEvent()
    data class StateChange(val inputValue: String) : CookUiEvent()
    data class ZipCodeChange(val inputValue: String) : CookUiEvent()
    data object Submit : CookUiEvent()
}
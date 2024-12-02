package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.state

/**
 * SignUp Screen Events
 */
sealed class CookSignUpUiEvent {
    data class UserNameChanged(val inputValue: String) : CookSignUpUiEvent()
    data class EmailChanged(val inputValue: String) : CookSignUpUiEvent()
    data class PasswordChanged(val inputValue: String) : CookSignUpUiEvent()
    data class ConfirmPasswordChanged(val inputValue: String) : CookSignUpUiEvent()
    data class GenderChange(val inputValue: String) : CookSignUpUiEvent()
    data class AddressChange(val inputValue: String) : CookSignUpUiEvent()
    data class CityChange(val inputValue: String) : CookSignUpUiEvent()
    data class StateChange(val inputValue: String) : CookSignUpUiEvent()
    data class ZipCodeChange(val inputValue: String) : CookSignUpUiEvent()
    data object Submit : CookSignUpUiEvent()
}
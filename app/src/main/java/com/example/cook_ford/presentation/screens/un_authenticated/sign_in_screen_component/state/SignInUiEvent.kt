package com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state

/**
 * Login Screen Events
 */
sealed class SignInUiEvent {
    data class EmailChanged(val inputValue: String) : SignInUiEvent()
    data class PhoneChanged(val inputValue: String) : SignInUiEvent()
    data class PasswordChanged(val inputValue: String) : SignInUiEvent()
    data object Submit : SignInUiEvent()
}
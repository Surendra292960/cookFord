package com.example.cook_ford.presentation.screens.sign_in.state

/**
 * Login Screen Events
 */
sealed class SignInUiEvent {
    data class UserNameChanged(val inputValue: String) : SignInUiEvent()
    data class PhoneChanged(val inputValue: String) : SignInUiEvent()
    data class PasswordChanged(val inputValue: String) : SignInUiEvent()
    data object Submit : SignInUiEvent()
}
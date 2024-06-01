package com.example.cook_ford.presentation.screens.un_authenticated.sign_up.state

/**
 * SignUp Screen Events
 */
sealed class SignUpUiEvent {
    data class UserNameChanged(val inputValue: String) : SignUpUiEvent()
    data class EmailChanged(val inputValue: String) : SignUpUiEvent()
    data class PhoneChanged(val inputValue: String) : SignUpUiEvent()
    data class PasswordChanged(val inputValue: String) : SignUpUiEvent()
    data class ConfirmPasswordChanged(val inputValue: String) : SignUpUiEvent()
    data object Submit : SignUpUiEvent()
}
package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state


/**
 * SignUp Screen Events
 */
sealed class SignUpUiEvent {
    data class UserNameChanged(val inputValue: String) : SignUpUiEvent()
    data class EmailChanged(val inputValue: String) : SignUpUiEvent()
    data class PasswordChanged(val inputValue: String) : SignUpUiEvent()
    data class ConfirmPasswordChanged(val inputValue: String) : SignUpUiEvent()
    data class GenderChange(val inputValue: String) : SignUpUiEvent()
    data object Submit : SignUpUiEvent()
}
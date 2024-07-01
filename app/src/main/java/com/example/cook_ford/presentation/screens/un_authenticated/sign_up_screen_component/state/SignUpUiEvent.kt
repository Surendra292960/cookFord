package com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state

import com.example.cook_ford.presentation.screens.authenticated.accounts.add_cook_screen_component.state.AddCookProfileUiEvent

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
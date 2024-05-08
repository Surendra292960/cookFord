package com.example.cook_ford.presentation.screens.sign_up.state

import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING


/**
 * SignUp State holding ui input values
 */
data class SignUpState(
    val name: String = EMPTY_STRING,
    val username: String = EMPTY_STRING,
    val phone: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val confirmPassword: String = EMPTY_STRING,
    val errorState: RegistrationErrorState = RegistrationErrorState(),
    val isSignUpSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class RegistrationErrorState(
    val nameErrorState: ErrorState = ErrorState(),
    val userNameErrorState: ErrorState = ErrorState(),
    val phoneErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState()
)
package com.example.cook_ford.presentation.screens.un_authenticated.sign_up.state
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

/**
 * SignUp State holding ui input values
 */
data class SignUpState(
    val username: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val phone: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val confirmPassword: String = EMPTY_STRING,
    val errorState: SignUpErrorState = SignUpErrorState(),
    val isSignUpSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class SignUpErrorState(
    val usernameErrorState: ErrorState = ErrorState(),
    val emailErrorState: ErrorState = ErrorState(),
    val phoneErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState()
)
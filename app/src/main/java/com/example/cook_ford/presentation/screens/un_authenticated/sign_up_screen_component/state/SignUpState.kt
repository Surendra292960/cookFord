package com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

/**
 * SignUp State holding ui input values
 */
data class SignUpState(
    val username: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val confirmPassword: String = EMPTY_STRING,
    val gender: String = EMPTY_STRING,
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
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState()
)
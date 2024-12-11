package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

/**
 * SignUp State holding ui input values
 */
data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val gender: String = "",
    val userType: String = "",
    val isSignUpSuccessful: Boolean = false,
    val errorState: SignUpErrorState = SignUpErrorState()
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
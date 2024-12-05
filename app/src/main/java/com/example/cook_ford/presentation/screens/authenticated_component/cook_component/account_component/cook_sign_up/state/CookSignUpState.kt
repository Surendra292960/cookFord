package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.cook_sign_up.state
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

/**
 * SignUp State holding ui input values
 */
data class CookSignUpState(
    val username: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val confirmPassword: String = EMPTY_STRING,
    val gender: String = EMPTY_STRING,
    val address: String = EMPTY_STRING,
    val city: String = EMPTY_STRING,
    val state: String = EMPTY_STRING,
    val zipCode: String = EMPTY_STRING,
    val errorState: CookSignUpErrorState = CookSignUpErrorState(),
    val isSignUpSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class CookSignUpErrorState(
    val usernameErrorState: ErrorState = ErrorState(),
    val emailErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val confirmPasswordErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState()
)
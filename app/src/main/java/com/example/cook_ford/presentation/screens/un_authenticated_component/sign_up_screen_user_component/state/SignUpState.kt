package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state
import com.example.cook_ford.data.remote.auth_response.SignUpResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants

/**
 * SignUp State holding ui input values
 */
data class SignUpState(
    val username: String = AppConstants.EMPTY_STRING,
    val email: String = AppConstants.EMPTY_STRING,
    val password: String = AppConstants.EMPTY_STRING,
    val confirmPassword: String = AppConstants.EMPTY_STRING,
    val gender: String = AppConstants.EMPTY_STRING,
    val userType: String = AppConstants.EMPTY_STRING,
    val signUpResponse: SignUpResponse = SignUpResponse(),
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
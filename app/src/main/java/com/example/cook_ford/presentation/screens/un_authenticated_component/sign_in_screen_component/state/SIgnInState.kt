package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state

import com.example.cook_ford.data.remote.auth_response.SignInResponse
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING


/**
 * SignIn State holding ui input values
 */
data class SignInState(
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val errorState: SignInErrorState = SignInErrorState(),
    val signInResponse: SignInResponse = SignInResponse(),
    var isSignInSuccessful: Boolean = false
)

/**
 * Error state in login holding respective
 * text field validation errors
 */
data class SignInErrorState(
    val emailErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)
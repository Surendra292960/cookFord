package com.example.cook_ford.presentation.screens.sign_in.state

import com.example.cook_ford.utils.AppConstants.EMPTY_STRING


/**
 * SignIn State holding ui input values
 */
data class SignInState(
    val username: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val errorState: SignInErrorState = SignInErrorState(),
    val isSignInSuccessful: Boolean = false
)

/**
 * Error state in login holding respective
 * text field validation errors
 */
data class SignInErrorState(
    val userNameErrorState: ErrorState = ErrorState(),
    val phoneErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)
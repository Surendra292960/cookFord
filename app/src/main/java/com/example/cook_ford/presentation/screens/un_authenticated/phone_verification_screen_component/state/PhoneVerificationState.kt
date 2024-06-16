package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants

data class PhoneVerificationState(
    val phone: String = AppConstants.EMPTY_STRING,
    val otp: String = AppConstants.EMPTY_STRING,
    val errorState: PhoneVerificationErrorState = PhoneVerificationErrorState(),
    var nevigateToOTPScreen: Boolean = false,
    var nevigateToSignIn: Boolean = false,
    var isSuccessful: Boolean = false
)

/**
 * Error state in login holding respective
 * text field validation errors
 */
data class PhoneVerificationErrorState(
    val phoneErrorState: ErrorState = ErrorState(),
    val otpErrorState: ErrorState = ErrorState(),
)
package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state

sealed class OTPVerificationUiEvent {
    data class OTPChanged(val inputValue: String) : OTPVerificationUiEvent()
    data object Submit : OTPVerificationUiEvent()
}
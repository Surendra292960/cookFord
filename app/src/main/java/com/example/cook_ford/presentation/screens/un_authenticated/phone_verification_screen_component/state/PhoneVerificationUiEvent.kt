package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state

sealed class PhoneVerificationUiEvent {
    data class PhoneChanged(val inputValue: String) : PhoneVerificationUiEvent()
    //data class SendOtp(val inputValue: String) : PhoneVerificationUiEvent()
    data object Submit : PhoneVerificationUiEvent()
}
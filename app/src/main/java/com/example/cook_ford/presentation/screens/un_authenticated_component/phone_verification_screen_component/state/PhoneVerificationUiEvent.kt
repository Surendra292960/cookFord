package com.example.cook_ford.presentation.screens.un_authenticated_component.phone_verification_screen_component.state

sealed class PhoneVerificationUiEvent {
    data class PhoneChanged(val inputValue: String) : PhoneVerificationUiEvent()
    data object Submit : PhoneVerificationUiEvent()
}
package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_aadhaar.state

sealed class AadhaarUiEvent {
    data class AadhaarFrontChange(val inputValue: String) : AadhaarUiEvent()
    data class AadhaarChange(val inputValue: String) : AadhaarUiEvent()
    data object Submit : AadhaarUiEvent()
}

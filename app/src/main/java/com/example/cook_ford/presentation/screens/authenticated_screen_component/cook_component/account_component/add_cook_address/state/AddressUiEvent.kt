package com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.add_cook_address.state

/**
 * SignUp Screen Events
 */
sealed class AddressUiEvent {
    data class AddressChange(val inputValue: String) : AddressUiEvent()
    data class CityChange(val inputValue: String) : AddressUiEvent()
    data class StateChange(val inputValue: String) : AddressUiEvent()
    data class ZipCodeChange(val inputValue: String) : AddressUiEvent()
    data object Submit : AddressUiEvent()
}
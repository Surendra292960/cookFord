package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.add_cook_address.state

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
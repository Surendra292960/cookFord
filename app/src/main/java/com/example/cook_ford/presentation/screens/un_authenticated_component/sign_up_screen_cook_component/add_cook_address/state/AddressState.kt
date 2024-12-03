package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.add_cook_address.state
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

/**
 * Address State holding ui input values
 */
data class AddressState(
    val address: String = EMPTY_STRING,
    val city: String = EMPTY_STRING,
    val state: String = EMPTY_STRING,
    val zipCode: String = EMPTY_STRING,
    val errorState: AddressErrorState = AddressErrorState(),
    val isSignUpSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class AddressErrorState(
    val addressErrorState: ErrorState = ErrorState(),
    val cityErrorState: ErrorState = ErrorState(),
    val stateErrorState: ErrorState = ErrorState(),
    val zipCodeErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState()
)
package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

/**
 * CookPersonalInfoState State holding ui input values
 */
data class CookPersonalInfoState(
    val firstName: String = EMPTY_STRING,
    val lastName: String = EMPTY_STRING,
    val address: String = EMPTY_STRING,
    val city: String = EMPTY_STRING,
    val state: String = EMPTY_STRING,
    val zipCode: String = EMPTY_STRING,
    val religion: String = EMPTY_STRING,
    val errorState: CookPersonalInfoErrorState = CookPersonalInfoErrorState(),
    var isLoading: Boolean = true,
    var profileResponse: ProfileResponse? = null,
    val isSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class CookPersonalInfoErrorState(
    val firstNameErrorState: ErrorState = ErrorState(),
    val lastNameErrorState: ErrorState = ErrorState(),
    val addressErrorState: ErrorState = ErrorState(),
    val cityErrorState: ErrorState = ErrorState(),
    val stateErrorState: ErrorState = ErrorState(),
    val zipCodeErrorState: ErrorState = ErrorState(),
    val religionErrorState: ErrorState = ErrorState(),
)
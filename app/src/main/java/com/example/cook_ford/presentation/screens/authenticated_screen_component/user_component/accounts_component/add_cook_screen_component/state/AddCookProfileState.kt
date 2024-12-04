package com.example.cook_ford.presentation.screens.authenticated_screen_component.user_component.accounts_component.add_cook_screen_component.state

import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING


/**
 * EditProfile State holding ui input values
 */
data class AddCookProfileState(
    val username: String = EMPTY_STRING,
    val phone: String = EMPTY_STRING,
    val alternatePhone: String = EMPTY_STRING,
    val gender: String = EMPTY_STRING,
    val profileImage: String = EMPTY_STRING,
    val city: String = EMPTY_STRING,
    val jobType: MutableSet<String>?= mutableSetOf(),
    val errorState: AddCookProfileErrorState = AddCookProfileErrorState(),
    var isCookAddedSuccessful: Boolean = false
)

/**
 * Error state in Profile holding respective
 * text field validation errors
 */
data class AddCookProfileErrorState(
    val usernameErrorState: ErrorState = ErrorState(),
    val phoneErrorState: ErrorState = ErrorState(),
    val alternatePhoneErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState(),
    val cityErrorState: ErrorState = ErrorState(),
    val jobTypeErrorState: ErrorState = ErrorState(),
    val profileImageErrorState: ErrorState = ErrorState(),
)
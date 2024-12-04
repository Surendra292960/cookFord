package com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.update_profile_screen_component.state

import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING

/**
 * EditProfile State holding ui input values
 */
data class UpdateCookProfileState(
    val username: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val phone: String = EMPTY_STRING,
    val gender: String = EMPTY_STRING,
    val profileImage: String = EMPTY_STRING,
    val errorState: UpdateCookProfileErrorState = UpdateCookProfileErrorState(),
    var isEditSuccessful: Boolean = false,
    var isLoading: Boolean = true,
    var profileResponse: ProfileResponse? = null,
    val errorMessage: String?= EMPTY_STRING,
    var isSuccessful: Boolean = false

)

/**
 * Error state in Profile holding respective
 * text field validation errors
 */
data class UpdateCookProfileErrorState(
    val usernameErrorState: ErrorState = ErrorState(),
    val emailErrorState: ErrorState = ErrorState(),
    val phoneErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState(),
    val profileImageErrorState: ErrorState = ErrorState(),
)
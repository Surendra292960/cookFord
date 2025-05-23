package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.list_screen_component.state

import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.utils.AppConstants

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: List<ProfileResponse>? = null,
    val errorMessage: String = AppConstants.EMPTY_STRING,
    var isSuccessful: Boolean = false
)
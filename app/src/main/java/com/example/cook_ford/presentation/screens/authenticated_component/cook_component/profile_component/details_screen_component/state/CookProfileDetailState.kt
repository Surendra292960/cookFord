package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.state

import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.utils.AppConstants

data class CookProfileDetailState(
    var isLoading: Boolean = true,
    var profileResponse: List<ProfileResponse>? = null,
    val errorMessage: String?= AppConstants.EMPTY_STRING,
    var isSuccessful: Boolean = false
)
package com.example.cook_ford.presentation.screens.profile.profile_details.state

import com.example.cook_ford.data.remote.profile_response.ProfileResponse

data class ProfileDetailState(
    val isLoading: Boolean = false,
    val profile: List<ProfileResponse>? = null,
    val errorMessage: String = "",
    var isSuccessful: Boolean = false
)
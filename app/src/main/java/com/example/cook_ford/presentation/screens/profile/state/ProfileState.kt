package com.example.cook_ford.presentation.screens.profile.state

import com.example.cook_ford.utils.AppConstants

data class ProfileState(

    var id: String? = AppConstants.EMPTY_STRING,

    var username: String? = AppConstants.EMPTY_STRING,

    var email: String? = AppConstants.EMPTY_STRING,

    var password: String? = AppConstants.EMPTY_STRING,

    var phone: String? = AppConstants.EMPTY_STRING,

    var gender: String? = AppConstants.EMPTY_STRING,

    var createdAt: String? = AppConstants.EMPTY_STRING,

    var updatedAt: String? = AppConstants.EMPTY_STRING,

    var v: Int? = AppConstants.ZERO,

    var isSuccessful: Boolean = false
)
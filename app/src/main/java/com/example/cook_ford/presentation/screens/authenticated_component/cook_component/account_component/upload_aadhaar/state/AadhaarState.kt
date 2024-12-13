package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_aadhaar.state

import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants

data class AadhaarState(
    val aadhaarFront: String = AppConstants.EMPTY_STRING,
    val aadhaarBack: String= AppConstants.EMPTY_STRING,
    val errorState: AadhaarErrorState = AadhaarErrorState(),
    var isLoading: Boolean = true,
    val errorMessage: String?= AppConstants.EMPTY_STRING,
    var isSuccessful: Boolean = false
)

data class AadhaarErrorState(
    val aadhaarFront: ErrorState = ErrorState(),
    val aadhaarBack: ErrorState = ErrorState(),
)

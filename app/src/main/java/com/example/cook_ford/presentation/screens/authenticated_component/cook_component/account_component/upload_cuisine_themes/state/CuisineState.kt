package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_cuisine_themes.state

import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants

data class CuisineState(
    val one: String = AppConstants.EMPTY_STRING,
    val two: String= AppConstants.EMPTY_STRING,
    val three: String= AppConstants.EMPTY_STRING,
    val four: String= AppConstants.EMPTY_STRING,
    val five: String= AppConstants.EMPTY_STRING,
    val six: String= AppConstants.EMPTY_STRING,
    val errorState: CuisineErrorState = CuisineErrorState(),
    var isLoading: Boolean = true,
    val errorMessage: String?= AppConstants.EMPTY_STRING,
    var isSuccessful: Boolean = false
)

data class CuisineErrorState(
    val one: ErrorState = ErrorState(),
    val two: ErrorState = ErrorState(),
    val three: ErrorState = ErrorState(),
    val four: ErrorState = ErrorState(),
    val five: ErrorState = ErrorState(),
    val six: ErrorState = ErrorState(),
)

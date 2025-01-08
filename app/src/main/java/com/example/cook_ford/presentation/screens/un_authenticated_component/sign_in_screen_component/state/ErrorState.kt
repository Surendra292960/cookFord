package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state
import androidx.annotation.StringRes
import com.example.cook_ford.R

/**
 * Error state holding values for error ui
 */
data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)
package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.cook_preferences_component.state
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants

/**
 * CookPreferences State holding ui input values
 */
data class UserPreferencesState (
    val cookType: String = AppConstants.EMPTY_STRING,
    val numberOfVisit: Int = AppConstants.ZERO,
    val readyToRelocate: Boolean = false,
    val gender: String = AppConstants.EMPTY_STRING,
    val morning: List<String>?= emptyList(),
    val afternoon: List<String>?= emptyList(),
    val evening: List<String>?= emptyList(),
    val cousin: List<String>?= emptyList(),
    val language: List<String>?= emptyList(),
    val experience: Int = AppConstants.ZERO,
    val errorState: UserPreferencesErrorState = UserPreferencesErrorState(),
    var isLoading: Boolean = true,
    val errorMessage: String?= AppConstants.EMPTY_STRING,
    var isSuccessful: Boolean = false
)

/**
 * Error state in CookPreferences holding respective
 * text field validation errors
 */
data class UserPreferencesErrorState(
    val cookTypeErrorState: ErrorState = ErrorState(),
    val numberOfVisitErrorState: ErrorState = ErrorState(),
    val readyToRelocateErrorState: ErrorState = ErrorState(),
    val genderErrorState: ErrorState = ErrorState(),
    val morningErrorState: ErrorState = ErrorState(),
    val afternoonErrorState: ErrorState = ErrorState(),
    val eveningErrorState: ErrorState = ErrorState(),
    val cousinErrorState: ErrorState = ErrorState(),
    val languageErrorState: ErrorState = ErrorState(),
    val experienceErrorState: ErrorState = ErrorState(),
)
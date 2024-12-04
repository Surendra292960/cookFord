package com.example.cook_ford.presentation.screens.authenticated_screen_component.user_component.accounts_component.cook_preferences_component.state


sealed class UserPreferencesUiEvent {
    data class CookTypeChanged(val inputValue: String) : UserPreferencesUiEvent()
    data class NumberOfVisitChanged(val inputValue: String) : UserPreferencesUiEvent()
    data class ReadyToRelocateChanged(val inputValue: String) : UserPreferencesUiEvent()
    data class GenderChange(val inputValue: String) : UserPreferencesUiEvent()
    data class MorningChange(val inputValue: String) : UserPreferencesUiEvent()
    data class AfternoonChange(val inputValue: String) : UserPreferencesUiEvent()
    data class EveningChange(val inputValue: String) : UserPreferencesUiEvent()
    data class CousinChange(val inputValue: String) : UserPreferencesUiEvent()
    data class LanguageChange(val inputValue: String) : UserPreferencesUiEvent()
    data class ExperienceChange(val inputValue: String) : UserPreferencesUiEvent()

    data object Submit : UserPreferencesUiEvent()

}

package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_preferences.state


sealed class CookPreferencesUiEvent {
    data class CookTypeChanged(val inputValue: String) : CookPreferencesUiEvent()
    data class NumberOfVisitChanged(val inputValue: String) : CookPreferencesUiEvent()
    data class ReadyToRelocateChanged(val inputValue: String) : CookPreferencesUiEvent()
    data class GenderChange(val inputValue: String) : CookPreferencesUiEvent()
    data class MorningChange(val inputValue: String) : CookPreferencesUiEvent()
    data class AfternoonChange(val inputValue: String) : CookPreferencesUiEvent()
    data class EveningChange(val inputValue: String) : CookPreferencesUiEvent()
    data class CousinChange(val inputValue: String) : CookPreferencesUiEvent()
    data class LanguageChange(val inputValue: String) : CookPreferencesUiEvent()
    data class ExperienceChange(val inputValue: String) : CookPreferencesUiEvent()

    data object Submit : CookPreferencesUiEvent()

}

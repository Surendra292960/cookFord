package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state

sealed class EditCookProfileUiEvent {
    data class WorkAreaChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class UserNameChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class DobChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class AddressChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class ReligionChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class ExperienceChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class SalaryChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class NumberOfVisitChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class FoodTypeChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class PhoneChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class ProfileImageChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class AlternatePhoneChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class CityChanged(val inputValue: String) : EditCookProfileUiEvent()
    data class GenderChange(val inputValue: String) : EditCookProfileUiEvent()
    data class JobTypeChange(val inputValue: MutableSet<String>) : EditCookProfileUiEvent()
    data class CuisineChange(val inputValue: MutableSet<String>) : EditCookProfileUiEvent()
    data class LanguageChange(val inputValue: MutableSet<String>) : EditCookProfileUiEvent()
    data object Submit : EditCookProfileUiEvent()
}
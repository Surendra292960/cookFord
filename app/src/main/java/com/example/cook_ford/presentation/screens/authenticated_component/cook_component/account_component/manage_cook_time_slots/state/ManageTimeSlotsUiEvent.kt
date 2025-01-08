package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state

sealed class ManageTimeSlotsUiEvent {
    data class MorningChange(val inputValue: String) : ManageTimeSlotsUiEvent()
    data class AfternoonChange(val inputValue: String) : ManageTimeSlotsUiEvent()
    data class EveningChange(val inputValue: String) : ManageTimeSlotsUiEvent()
    data object Submit : ManageTimeSlotsUiEvent()
}

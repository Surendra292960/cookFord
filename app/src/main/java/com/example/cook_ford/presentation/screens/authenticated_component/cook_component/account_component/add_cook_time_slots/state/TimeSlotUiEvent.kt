package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_time_slots.state

sealed class TimeSlotUiEvent {
    data class MorningChange(val inputValue: String) : TimeSlotUiEvent()
    data class AfternoonChange(val inputValue: String) : TimeSlotUiEvent()
    data class EveningChange(val inputValue: String) : TimeSlotUiEvent()
    data object Submit : TimeSlotUiEvent()
}

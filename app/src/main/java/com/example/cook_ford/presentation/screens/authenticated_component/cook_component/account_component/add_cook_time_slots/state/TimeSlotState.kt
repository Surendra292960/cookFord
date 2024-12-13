package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_time_slots.state

import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants

data class TimeSlotState(
    val morning: List<String>?= emptyList(),
    val afternoon: List<String>?= emptyList(),
    val evening: List<String>?= emptyList(),
    val errorState: TimeSlotErrorState = TimeSlotErrorState(),
    var isLoading: Boolean = true,
    val errorMessage: String?= AppConstants.EMPTY_STRING,
    var isSuccessful: Boolean = false
)

data class TimeSlotErrorState(
    val morningErrorState: ErrorState = ErrorState(),
    val afternoonErrorState: ErrorState = ErrorState(),
    val eveningErrorState: ErrorState = ErrorState(),
)

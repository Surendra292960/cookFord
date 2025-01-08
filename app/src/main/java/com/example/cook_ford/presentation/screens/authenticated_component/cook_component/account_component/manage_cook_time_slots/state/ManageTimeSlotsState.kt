package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state
import com.example.cook_ford.data.remote.TimeSlotsResponse
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.utils.AppConstants

data class ManageTimeSlotsState(
    val morning: String = AppConstants.EMPTY_STRING,
    val afternoon: String = AppConstants.EMPTY_STRING,
    val evening: String = AppConstants.EMPTY_STRING,
    val errorState: ManageTimeSlotsErrorState = ManageTimeSlotsErrorState(),
    var isLoading: Boolean = true,
    val errorMessage: String?= AppConstants.EMPTY_STRING,
    val timeSlotsResponse: TimeSlotsResponse? = null,
    var isSuccessful: Boolean = false
)

data class ManageTimeSlotsErrorState(
    val morningErrorState: ErrorState = ErrorState(),
    val afternoonErrorState: ErrorState = ErrorState(),
    val eveningErrorState: ErrorState = ErrorState(),
)

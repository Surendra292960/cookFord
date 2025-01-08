package com.example.cook_ford.domain.use_cases.authenticated_use_case
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.TimeSlotsResponse
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.afterNoonEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.eveningEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.morningEmptyErrorState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class TimeSlotsUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {

    suspend operator fun invoke(): Flow<NetworkResult<TimeSlotsResponse>> {
        return repository.getTimeSlots()
    }

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (useCase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    operator fun invoke (manageTimeSlotsState: MutableStateFlow<ManageTimeSlotsState>): Boolean {
        val morning = manageTimeSlotsState.value.morning
        val afterNoon = manageTimeSlotsState.value.afternoon
        val evening = manageTimeSlotsState.value.evening


        // morning empty
        if (morning.isEmpty()) {
            manageTimeSlotsState.value = manageTimeSlotsState.value.copy(
                errorState = ManageTimeSlotsErrorState(
                    morningErrorState = morningEmptyErrorState
                )
            )
            return false
        }
        // afternoon empty
        if (afterNoon.isEmpty()) {
            manageTimeSlotsState.value = manageTimeSlotsState.value.copy(
                errorState = ManageTimeSlotsErrorState(
                    afternoonErrorState = afterNoonEmptyErrorState
                )
            )
            return false
        }
        // morning empty
        if (evening.isEmpty()) {
            manageTimeSlotsState.value = manageTimeSlotsState.value.copy(
                errorState = ManageTimeSlotsErrorState(
                    eveningErrorState = eveningEmptyErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            manageTimeSlotsState.value = manageTimeSlotsState.value.copy(errorState = ManageTimeSlotsErrorState())
            return true
        }
    }
}
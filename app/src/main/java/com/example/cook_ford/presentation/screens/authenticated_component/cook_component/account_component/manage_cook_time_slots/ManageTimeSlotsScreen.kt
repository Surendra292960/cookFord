package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsUiEvent


@Composable
fun ManageTimeSlotsScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val manageTimeSlotsViewModel: ManageTimeSlotsViewModel = hiltViewModel()
    val viewState by manageTimeSlotsViewModel.viewState.collectAsState()
    val manageTimeSlotsState by remember { manageTimeSlotsViewModel.manageTimeSlotsState }
    val snackBarHostState = remember { SnackbarHostState() }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val mContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White).padding(20.dp), horizontalAlignment = Alignment.Start
    ) {

        MangeTimeSlotForm(
            manageTimeSlotsState = manageTimeSlotsState,
            viewState = viewState,
            onMorningChange = { inputString ->
                manageTimeSlotsViewModel.onUiEvent(
                    manageTimeSlotsUiEvent = ManageTimeSlotsUiEvent.MorningChange(
                        inputValue = inputString
                    )
                )
            },
            onAfternoonChange = { inputString ->
                manageTimeSlotsViewModel.onUiEvent(
                    manageTimeSlotsUiEvent = ManageTimeSlotsUiEvent.AfternoonChange(
                        inputValue = inputString
                    )
                )
            },
            onEveningChange = { inputString ->
                manageTimeSlotsViewModel.onUiEvent(
                    manageTimeSlotsUiEvent = ManageTimeSlotsUiEvent.EveningChange(
                        inputValue = inputString
                    )
                )
            },
            onSubmit = {
                manageTimeSlotsViewModel.onUiEvent(manageTimeSlotsUiEvent = ManageTimeSlotsUiEvent.Submit)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    ManageTimeSlotsScreen(onNavigateBack={}, onNavigateToAuthenticatedRoute={})
}
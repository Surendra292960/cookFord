package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.MultiSelectionComponent
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.state.ManageTimeSlotsState
import com.example.cook_ford.utils.AppConstants


@Composable
fun MangeTimeSlotForm(
    manageTimeSlotsState: ManageTimeSlotsState,
    viewState: MainViewState,
    onMorningChange: (String) -> Unit,
    onAfternoonChange: (String) -> Unit,
    onEveningChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.Start) {

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Spacer(modifier = Modifier.height(10.dp))


            TitleText(
                modifier = Modifier,
                text = "Select your available time slots",
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray,
            )

            Spacer(modifier = Modifier.height(10.dp))

            MediumTitleText(
                modifier = Modifier,
                text = "Part time work",
                fontWeight = FontWeight.W900,
                textAlign = TextAlign.Center,
                textColor = Color.Black
            )

            //Morning
            MediumTitleText(
                modifier = Modifier,
                text = AppConstants.MORNING,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray,
                isError = manageTimeSlotsState.errorState.morningErrorState.hasError,
                errorText = stringResource(id = manageTimeSlotsState.errorState.morningErrorState.errorMessageStringResource),
            )
            manageTimeSlotsState.timeSlotsResponse?.morning?.let {
                morning->
                MultiSelectionComponent(
                    modifier = Modifier,
                    itemList = morning.map { it.time },
                    onSelectionChanged = {
                        onMorningChange.invoke(it)
                        Log.d("TAG", "ManageTimeSlotsScreen Morning: $it")
                    }
                )
            }


            //Afternoon
            MediumTitleText(
                modifier = Modifier,
                text = AppConstants.AFTER_NOON,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray,
                isError = manageTimeSlotsState.errorState.afternoonErrorState.hasError,
                errorText = stringResource(id = manageTimeSlotsState.errorState.afternoonErrorState.errorMessageStringResource),
            )

            manageTimeSlotsState.timeSlotsResponse?.afternoon?.let { afternoon ->
                MultiSelectionComponent(
                    modifier = Modifier,
                    itemList = afternoon.map { it.time },
                    onSelectionChanged = {
                        onAfternoonChange.invoke(it)
                        Log.d("TAG", "ManageTimeSlotsScreen Afternoon: $it")
                    }
                )
            }

            //Evening
            MediumTitleText(
                modifier = Modifier,
                text = AppConstants.EVENING,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray,
                isError = manageTimeSlotsState.errorState.eveningErrorState.hasError,
                errorText = stringResource(id = manageTimeSlotsState.errorState.eveningErrorState.errorMessageStringResource),
            )

            manageTimeSlotsState.timeSlotsResponse?.evening?.let { evening ->
                MultiSelectionComponent(
                    modifier = Modifier,
                    itemList = evening.map { it.time },
                    onSelectionChanged = {
                        onEveningChange.invoke(it)
                        Log.d("TAG", "ManageTimeSlotsScreen Evening: $it")
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        SubmitButton(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .background(Color.Transparent, shape = CircleShape),
            text = stringResource(id = R.string.submit_button_text),
            isLoading = viewState.isLoading,
            onClick = { onSubmit.invoke() }
        )
    }
}
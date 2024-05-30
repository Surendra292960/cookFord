package com.example.cook_ford.presentation.screens.profile.report

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.data.remote.profile_response.TimeSlots
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.component.widgets.topbar_nav.TopBarNavigation
import com.example.cook_ford.presentation.screens.profile.report.state.ReportUiEvent
import com.example.cook_ford.presentation.theme.Purple80
import com.example.cook_ford.presentation.theme.PurpleGrey40


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    ReportScreen(
        onNavigateBack = {},
        onNavigateToAuthenticatedHomeRoute = {}
    )
}

@Composable
fun ReportScreen(
    navController: NavController? = null,
    onNavigateBack:()->Unit,
    onNavigateToAuthenticatedHomeRoute: () -> Unit) {
    val reportViewModel: ReportViewModel = hiltViewModel()
    val reportState by reportViewModel.reportState.collectAsState()
    val viewState:MainViewState by reportViewModel.viewState.collectAsState()

    val timeSlots:List<TimeSlots> = emptyList()

    LaunchedEffect (key1 = true){
        reportViewModel.getTimeSlots()
    }

    fun chipChangeListener(item: TimeSlots, checked: Boolean) =
        timeSlots.find { it.slots == item.slots }?.let { task -> task.selected = checked }
    if (reportState.isSuccessful){
        Log.d("TAG", "Data isSuccessful : ${reportState.isSuccessful}")
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {

            TopBarNavigation(title = "Cook Report", onNavigateBack={onNavigateBack.invoke()})
            reportState?.profile?.let { ImageWithUserName(it) }

            /* TimeSlotsComponent(timeSlots = reportViewModel.getTimeSlots(), onSelectedChanged = { slots, selected->
                 chipChangeListener(slots, selected).let {
                     reportViewModel.getTimeSlots().toSet().forEach {
                         if (it.selected){
                             //Log.d("TAG", "ExperienceCard: ${it.slots}")
                         }
                     }
                 }
             })*/
            SuggestionChipLayout()

            ReportForm(
                reportState = reportState,
                viewState = viewState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                onReportChange = { inputString ->
                    reportViewModel.onUiEvent(
                        reportUiEvent = ReportUiEvent.ReportChanged(
                            inputString
                        )
                    )
                },
                onSubmit = {
                    reportViewModel.onUiEvent(reportUiEvent = ReportUiEvent.Submit)
                })
        }
    }
}


@Composable
fun ImageWithUserName(profileRes: ProfileResponse) {

    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {

            Column(modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                // ProfilePicture
                Box(modifier = Modifier
                    .size(80.dp)
                    .wrapContentHeight()
                    .clip(CircleShape)
                    .background(Color.White)) {
                    Image(painter = painterResource(id = R.drawable.ic_chef_round),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = profileRes.username.toString(),
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

/*
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TimeSlotsComponent(
    timeSlots: List<TimeSlots>,
    modifier: Modifier = Modifier,
    selectedCar: TimeSlots? = null,
    onSelectedChanged: (TimeSlots, Boolean) -> Unit) {
    FlowRow {
        timeSlots.forEach {
            SuggestionChipEachRow(label = it.slots.toString()*//*, selected = it.slots == chipState*//*) { chip ->
                onSelectedChanged(it, !it.selected)
            }
        }
    }
}


@Composable
fun SuggestionChipEachRow(
    label: String,
    selected: Boolean=false,
    onChipChange: (String) -> Unit
) {

    SuggestionChip(onClick = {
        if (!selected)
            onChipChange(label)
        else
            onChipChange("")
    }, label = {
        Text(text = label)
    }, modifier = Modifier.padding(horizontal = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = if (selected) Purple80 else Color.Transparent
        ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            enabled = true,
            borderWidth = 1.dp,
            borderColor = if (selected) Color.Transparent else PurpleGrey40
        )
    )
}*/



@Composable
fun SuggestionChipLayout() {
    val reportViewModel: ReportViewModel = hiltViewModel()

    val chipState by remember { mutableStateOf(TimeSlots()) }

    val selectedItems = reportViewModel.getSelectedItems().map { it.slots }
    Log.d("TAG", "SuggestionChipLayout: $selectedItems")

    reportViewModel.myItems.forEachIndexed { index , item ->
        SuggestionChipEachRow(index = index, label = item.slots.toString(), selected = item == chipState, reportViewModel, onChipChange={
            Log.d("TAG", "SuggestionChipLayout: $it")
        })
    }
}


@Composable
fun SuggestionChipEachRow(
    index :Int,
    label: String,
    selected: Boolean,
    reportViewModel: ReportViewModel,
    onChipChange: (String) -> Unit
) {
    Log.d("TAG", "SuggestionChipEachRow: $label $selected")
    SuggestionChip(onClick = {
        reportViewModel.toggleSelection(index)
        if (!selected)
            onChipChange(label)
        else
            onChipChange("")
    }, label = {
        Text(text = label)
    },// modifier = Modifier.padding(horizontal = 5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = if (selected) Purple80 else Color.Transparent
        ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            enabled = selected,
            borderWidth = 1.dp,
            borderColor = if (selected) Color.Transparent else PurpleGrey40
        )
    )

}
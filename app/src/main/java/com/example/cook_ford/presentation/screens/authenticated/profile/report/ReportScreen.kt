package com.example.cook_ford.presentation.screens.authenticated.profile.report

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.component.widgets.topbar_nav.TopBarNavigation
import com.example.cook_ford.presentation.screens.authenticated.profile.report.state.ReportUiEvent
import com.google.gson.Gson

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
    onNavigateBack:()->Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedHomeRoute: () -> Unit) {

    val reportViewModel: ReportViewModel = hiltViewModel()
    val reportState by reportViewModel.reportState
    val viewState:MainViewState by reportViewModel.viewState.collectAsState()

    Progressbar(reportState.isLoading)
    LaunchedEffect(key1 = true) {
        reportViewModel.setProfileData(profileResponse)
    }

    if (reportState.isSuccessful){
        Log.d("TAG", "Data isSuccessful : ${reportState.isSuccessful}")
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {

            TopBarNavigation(title = "Cook Report", onNavigateBack={onNavigateBack.invoke()})
            reportState?.profileResponse?.let { ImageWithUserName(it) }

            ReportForm(
                reportState = reportState,
                viewState = viewState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                onIssueChange = { inputString ->
                    reportViewModel.onUiEvent(
                        reportUiEvent = ReportUiEvent.IssueChanged(
                            inputString
                        )
                    )
                },
                onReportChange = { inputString ->
                    reportViewModel.onUiEvent(
                        reportUiEvent = ReportUiEvent.ReportChanged(
                            inputString
                        )
                    )
                },
                onSubmit = {
                    reportViewModel.onUiEvent(reportUiEvent = ReportUiEvent.Submit)
                }
            )
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


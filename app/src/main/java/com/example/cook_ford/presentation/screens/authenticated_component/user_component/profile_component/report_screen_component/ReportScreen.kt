package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.report_screen_component

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.rememberImeState
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.CookReportViewModel
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.report_screen_component.state.ReportUiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    val snackBarHostState = remember { SnackbarHostState() }
    val lifecycle = LocalLifecycleOwner.current.lifecycle


    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }


    Progressbar(reportState.isLoading)
    LaunchedEffect(key1 = true) {
        reportViewModel.setProfileData(profileResponse)
    }

    if (reportState.isSuccessful) {
        Log.d("TAG", "Data isSuccessful : ${reportState.isSuccessful}")
        Column(modifier = Modifier.background(Color.White).fillMaxSize().verticalScroll(scrollState)) {

            reportState.profileResponse?.let {
                ImageWithUserName(
                    it
                )
            }

            ReportForm(
                reportState = reportState,
                viewState = viewState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                onIssueChange = { inputString ->
                    Log.d("TAG", "ReportScreen: $inputString")
                    reportViewModel.onUiEvent(
                        reportUiEvent = ReportUiEvent.IssueChanged(
                            inputString
                        )
                    )
                },
                onReportChange = { inputString ->
                    Log.d("TAG", "ReportScreen: $inputString")
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

            com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.report_screen_component.ShowSnackbar(
                lifecycle,
                snackBarHostState
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
                    .border(1.dp, Color.LightGray, CircleShape)
                    .background(Color.White)) {
                    Image(painter = painterResource(id = R.drawable.male_chef),
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

                    profileRes.username?.let {
                        TitleText(
                            modifier = Modifier,
                            text = it,
                            textAlign = TextAlign.Start,
                            textColor = Color.DarkGray,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun ShowSnackbar(lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
    val reportViewModel: CookReportViewModel = hiltViewModel()
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                reportViewModel.onProcessSuccess.collectLatest { message: String ->
                    Log.d("TAG", "Report: Event success")
                    snackBarHostState.showSnackbar(message)
                }
            }
        }
    }
}

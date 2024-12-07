package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.rememberImeState
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.state.CookReviewUiEvent

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    ReviewScreen(
        onNavigateBack = {},
        profileResponse = ProfileResponse(),
        onNavigateToAuthenticatedHomeRoute = {}
    )
}

@Composable
fun ReviewScreen(
    onNavigateBack:()->Unit,
    profileResponse:ProfileResponse,
    onNavigateToAuthenticatedHomeRoute: () -> Unit) {
    val reviewViewModel: CookReviewViewModel = hiltViewModel()
    val viewState:MainViewState by reviewViewModel.viewState.collectAsState()

    val cookReviewState by remember { reviewViewModel.cookReviewState }

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    Progressbar(cookReviewState.isLoading)
    LaunchedEffect(key1 = true) {
        reviewViewModel.setProfileData(profileResponse)
    }


    Column(modifier = Modifier.background(Color.White)
        .fillMaxSize()
        .verticalScroll(scrollState)) {

        cookReviewState.profileResponse?.let { ImageWithUserName(it) }

        CookReviewForm(
            cookReviewState = cookReviewState,
            viewState = viewState,
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(start = 10.dp, end = 10.dp),
            onReviewChange = { inputString ->
                reviewViewModel.onUiEvent(
                    reviewUiEvent = CookReviewUiEvent.CookReviewChanged(
                        inputString
                    )
                )
            },
            onSubmit = {
                reviewViewModel.onUiEvent(reviewUiEvent = CookReviewUiEvent.Submit)
            })
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



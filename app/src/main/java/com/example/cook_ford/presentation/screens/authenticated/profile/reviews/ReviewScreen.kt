package com.example.cook_ford.presentation.screens.authenticated.profile.reviews

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.StarRatingBar
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.profile.reviews.state.ReviewUiEvent

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
    val reviewViewModel:ReviewViewModel = hiltViewModel()
    val viewState:MainViewState by reviewViewModel.viewState.collectAsState()

    val reviewState by remember { reviewViewModel.reviewState }
    Progressbar(reviewState.isLoading)
    LaunchedEffect(key1 = true) {
        reviewViewModel.setProfileData(profileResponse)
    }

    var rating1 by remember { mutableFloatStateOf(0.0f) }
    var rating2 by remember { mutableFloatStateOf(0.0f) }
    var rating3 by remember { mutableFloatStateOf(0.0f) }
    var rating4 by remember { mutableFloatStateOf(0.0f) }
    var rating5 by remember { mutableFloatStateOf(0.0f) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 20.dp)
        .verticalScroll(rememberScrollState())) {

        reviewState?.profileResponse?.let { ImageWithUserName(it) }

        Row(modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "FoodQuality")
            StarRatingBar(
                maxStars = 5,
                rating = rating1,
                onRatingChanged = {
                    rating1 = it
                }
            )
        }

        Row(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Hygiene")
            StarRatingBar(
                maxStars = 5,
                rating = rating2,
                onRatingChanged = {
                    rating2 = it
                }
            )
        }

        Row(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Service")
            StarRatingBar(
                maxStars = 5,
                rating = rating3,
                onRatingChanged = {
                    rating3 = it
                }
            )
        }
        Row(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Cleanliness")
            StarRatingBar(
                maxStars = 5,
                rating = rating4,
                onRatingChanged = {
                    rating4 = it
                }
            )
        }
        Row(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Punctuality")
            StarRatingBar(
                maxStars = 5,
                rating = rating5,
                onRatingChanged = {
                    rating5 = it
                }
            )
        }

        ReviewForm(
            reviewState = reviewState,
            viewState = viewState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            onReviewChange = { inputString ->
                reviewViewModel.onUiEvent(
                    reviewUiEvent = ReviewUiEvent.ReviewChanged(
                        inputString
                    )
                )
            },
            onSubmit = {
                reviewViewModel.onUiEvent(reviewUiEvent = ReviewUiEvent.Submit)
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



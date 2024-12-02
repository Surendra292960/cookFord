package com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.reviews_screen_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.StarRatingBar
import com.example.cook_ford.presentation.component.widgets.Textarea
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.reviews_screen_component.state.ReviewState
import com.example.cook_ford.utils.AppConstants

@Composable
fun ReviewForm(
    reviewState: ReviewState,
    viewState: MainViewState,
    modifier: Modifier,
    onReviewChange: (String) -> Unit,
    onSubmit: () -> Unit
) {


    var rating1 by remember { mutableFloatStateOf(0.0f) }
    var rating2 by remember { mutableFloatStateOf(0.0f) }
    var rating3 by remember { mutableFloatStateOf(0.0f) }
    var rating4 by remember { mutableFloatStateOf(0.0f) }
    var rating5 by remember { mutableFloatStateOf(0.0f) }
    Column(modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween) {

        Row(modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            MediumTitleText(
                modifier = Modifier,
                text = "FoodQuality",
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray
            )
            StarRatingBar(
                maxStars = 5,
                rating = rating1,
                onRatingChanged = {
                    rating1 = it
                }
            )
        }

        Row(modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            MediumTitleText(
                modifier = Modifier,
                text = "Hygiene",
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray
            )
            StarRatingBar(
                maxStars = 5,
                rating = rating2,
                onRatingChanged = {
                    rating2 = it
                }
            )
        }

        Row(modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            MediumTitleText(
                modifier = Modifier,
                text = "Service",
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray
            )
            StarRatingBar(
                maxStars = 5,
                rating = rating3,
                onRatingChanged = {
                    rating3 = it
                }
            )
        }
        Row(modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            MediumTitleText(
                modifier = Modifier,
                text = "Cleanliness",
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray
            )
            StarRatingBar(
                maxStars = 5,
                rating = rating4,
                onRatingChanged = {
                    rating4 = it
                }
            )
        }
        Row(modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            MediumTitleText(
                modifier = Modifier,
                text = "Punctuality",
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                textColor = Color.DarkGray
            )
            StarRatingBar(
                maxStars = 5,
                rating = rating5,
                onRatingChanged = {
                    rating5 = it
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Textarea(
            value = reviewState.review,
            onChange = onReviewChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                label = AppConstants.LABEL_REVIEW,
                placeholder = AppConstants.REVIEW_PLACEHOLDER
            ),
            isError = reviewState.errorState.reviewErrorState.hasError,
            errorText = stringResource(id = reviewState.errorState.reviewErrorState.errorMessageStringResource),
            maxChar = 800
            /*submit = { TODO() }*/
        )

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedSubmitButton(
            modifier = Modifier.padding(all = 10.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.submit_button_text),
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}


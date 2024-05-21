package com.example.cook_ford.presentation.screens.profile.profile_details
import RatingBar
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Text
import com.example.cook_ford.R
import com.example.cook_ford.presentation.common.customeComposableViews.Child
import com.example.cook_ford.presentation.common.customeComposableViews.MediumTitleText
import com.example.cook_ford.presentation.common.customeComposableViews.SubTitleText
import com.example.cook_ford.presentation.common.widgets.Progressbar
import com.example.cook_ford.presentation.screens.profile.profile_details.state.ProfileDetailState
import com.example.cook_ford.presentation.theme.AppTheme


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview(){
	ProfileDetailScreen (
		onGoBack = {}
	)
}

@Composable
fun ProfileDetailScreen(
	onGoBack: () -> Unit,
	profileDetailsViewModel: ProfileDetailsViewModel = hiltViewModel(),
	profileLazyListState: LazyListState = rememberLazyListState()) {


	val profileState by remember { profileDetailsViewModel.profileState }

	Progressbar(profileState.isLoading)
	Log.d("TAG", "ProfileDetailScreen isLoading: ${profileState.isLoading}")


	if (profileState.isSuccessful) {
		LazyColumn(
			state = profileLazyListState,
			verticalArrangement = Arrangement.SpaceBetween,
			contentPadding = PaddingValues(AppTheme.dimens.paddingSmall),
			content = {
				Log.d("TAG", "ProfileDetailScreen : ${profileState.profile?.size}")
				items(profileState.profile!!.size) { index->

					ProfileDetails(
						profileState = profileState,
						index = index,
						onItemClick = {}
					)
				}
			}
		)
	}
}

@Composable
fun ProfileDetails(index: Int, onItemClick: (String) -> Unit, profileState: ProfileDetailState){

	Column (modifier = Modifier
		.fillMaxSize()
		.padding(AppTheme.dimens.paddingSmall)) {

		Column (modifier = Modifier.fillMaxWidth()) {

			Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {

				Row(modifier = Modifier
					.fillMaxSize(),
					horizontalArrangement = Arrangement.Start,
					verticalAlignment = Alignment.CenterVertically) {

					// ProfilePicture() composable
					Box(modifier = Modifier
						.size(80.dp)
						.clip(CircleShape)
						.background(Color.White)){
						Column (modifier = Modifier.wrapContentSize(),
							verticalArrangement = Arrangement.SpaceBetween,
							horizontalAlignment = Alignment.CenterHorizontally){

							Image(
								painter = painterResource(id = R.drawable.profile_circle),
								contentDescription = null,
								contentScale = ContentScale.Crop,
								modifier = Modifier.size(80.dp))
						}
					}

					Spacer(modifier = Modifier.size(size = AppTheme.dimens.paddingNormal))

					// ProfileContent() composable
					Box(modifier = Modifier
						.fillMaxSize()
						.padding(end = AppTheme.dimens.paddingNormal),
						Alignment.Center) {
						Column(modifier = Modifier
							.fillMaxHeight()
							.padding(start = AppTheme.dimens.paddingNormal),
							horizontalAlignment = Alignment.Start) {

							//Cook Name
							Row(modifier = Modifier
								.fillMaxWidth()
								.padding(top = AppTheme.dimens.paddingExtraLarge)) {
								MediumTitleText(
									modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
									text = profileState.profile?.get(index)?.cook?.username.toString()
								)
								Icon(
									Icons.Default.Verified, "", modifier = Modifier
										.align(Alignment.CenterVertically)
										.padding(AppTheme.dimens.paddingSmall))
							}
							//Cook Rating
							Row(modifier = Modifier.fillMaxWidth()) {
								Icon(
									Icons.Default.Star, "", modifier = Modifier
										.align(Alignment.CenterVertically)
										.padding(end = AppTheme.dimens.paddingSmall))
								SubTitleText(
									modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
									text = profileState.profile?.get(index)?.rating.toString()
								)
							}
							//Cook Type
							AssistChip(
								onClick = { Log.d("Assist chip", "hello world") },
								label = { SubTitleText(modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall), "Domestic") },
								leadingIcon = {
									Icon(
										Icons.Default.Fastfood,
										contentDescription = "Localized description",
										Modifier.size(AssistChipDefaults.IconSize)
									)
								}
							)
							//Last updated profile
							SubTitleText(
								modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall, bottom = AppTheme.dimens.paddingSmall),
								text = "Last update at ".plus(profileState.profile?.get(index)?.cook?.updatedAt.toString())
							)
						}
					}
				}
			}
		}


		Row(
			modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
				.background(Color.LightGray)) {
			// Experience
			Child(
				modifier = Modifier.weight(1f),
				title = "Experience",
				text = ""
			)
			// Language
			Child(
				modifier = Modifier.weight(1f),
				title = "Language",
				text = ""
			)
			// From
			Child(
				modifier = Modifier.weight(1f),
				title = "From",
				text = ""
			)
		}

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
				.padding(top = AppTheme.dimens.paddingTooSmall)
				.background(Color.LightGray)) {
			// Experience
			Child(
				modifier = Modifier.weight(1f),
				title = "Experience",
				text = ""
			)
			// Language
			Child(
				modifier = Modifier.weight(1f),
				title = "Language",
				text = ""
			)
			// From
			Child(
				modifier = Modifier.weight(1f),
				title = "From",
				text = ""
			)
		}

		//Cuisines
		MediumTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Cuisines"
		)
		SubTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall, bottom = AppTheme.dimens.paddingSmall),
			text = "North Indian food"
		)

		//Languages
		MediumTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Languages"
		)
		SubTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall, bottom = AppTheme.dimens.paddingSmall),
			text = "North Indian food"
		)

		//Daily Visit
		MediumTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Visit"
		)
		SubTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall, bottom = AppTheme.dimens.paddingSmall),
			text = "Available for one or two visit daily."
		)

		//Available Time Slots
		MediumTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Available Time Slots"
		)

		//Expectation
		MediumTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Expectation"
		)

		LazyRow {

		}

		//Ratings
		MediumTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Ratings"
		)

		val pairs = listOf(Pair("Punctuality", 2), Pair("Hygiene", 10), Pair("Cooking Skills", 15), Pair("Kitchen Cleanliness", 15))
		var rating by remember { mutableStateOf(1f) } //default rating will be 1
		Column {
			pairs.forEach {
				Row {
					Text(it.first, Modifier.weight(1f), color = Color.Black, textAlign = TextAlign.Start)
					//Text(it.second.toString())

					RatingBar(
						maxStars = 5,
						rating = rating,
						onRatingChanged = { rating = 5f }
					)
				}
			}
		}
	}
}

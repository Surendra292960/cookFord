package com.example.cook_ford.presentation.screens.profile.profile_details

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cook_ford.R
import com.example.cook_ford.presentation.common.customeComposableViews.Child
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.presentation.theme.OrangeYellow1



@ExperimentalFoundationApi
@Composable
fun ProfileDetailScreen(navController: NavController? = null, onNavigateToHomeScreen: () -> Unit) {
val time_slots = listOf(
	TimeSlots("8am-9am"),
	TimeSlots("8am-9am"),
	TimeSlots("8am-9am"),
	TimeSlots("8am-9am"),
	TimeSlots("8am-9am"),
	TimeSlots("8am-9am"),
	TimeSlots("8am-9am"),
	TimeSlots("8am-9am"),
	TimeSlots("8am-9am")
)

	val posts_list = listOf(

		Posts(
			"https://bit.ly/3oAIk0M",
			"domain expansion"
		),
		Posts(
			"https://bit.ly/3AcqrYy",
			"gojo googles"
		),
		Posts(
			"https://bit.ly/3BgosU6",
			"gojo"
		),
		Posts(
			"https://bit.ly/3BdkZ97",
			"hanging around with friend"
		),
		Posts(
			"https://bit.ly/3Acr702",
			"careless"
		),
		Posts(
			"https://bit.ly/3mtCMT8",
			"young gojo"
		),
		Posts(
			"https://bit.ly/3oAIk0M",
			"domain expansion"
		)
	)

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White)
			.verticalScroll(rememberScrollState())) {
		TopBar()
		Spacer(modifier = Modifier.height(10.dp))
		Stats("Vinay Kumar", "10.1M", "100")
		Spacer(modifier = Modifier.height(10.dp))
		SocialMediaIcons()
		/*
		*  SocialMediaIcons(
                onClickIcon1 = onClickIcon1,
                onClickIcon2 = onClickIcon2,
                onClickIcon3 = onClickIcon3,
                onClickIcon4 = onClickIcon4,
                icon1Painter = icon1Painter,
                icon2Painter = icon2Painter,
                icon3Painter = icon3Painter,
                icon4Painter = icon4Painter,
                iconBackgroundColor = iconBackgroundColor,
                iconTintColor = iconTintColor,
                contentPadding = contentPadding
            )*/
		HorizontalDivider(modifier = Modifier.background(Color.LightGray))
		Spacer(modifier = Modifier.height(10.dp))
		ExperienceCard(time_slots = time_slots)
		PostsComponent(posts_list = posts_list)
		Spacer(modifier = Modifier.height(16.dp))
	}
}

@Composable
fun TopBar() {
	Column(
		modifier = Modifier
			.clickable(onClick = { })
			.fillMaxWidth()) {
		Box(
			modifier = Modifier
				.height(200.dp)
				.fillMaxWidth()
		) {
			Box(
				modifier = Modifier
					.height(140.dp)
					.fillMaxWidth()
			) {
				Image(
					painter = rememberAsyncImagePainter("https://bit.ly/3oAIk0M"),
					contentDescription = "",
					modifier = Modifier.fillMaxSize(),
					contentScale = ContentScale.Crop,
				)
				Box(
					modifier = Modifier
						.height(90.dp)
						.fillMaxWidth()
						.background(
							Brush.verticalGradient(
								listOf(
									Color.Transparent,
									Color.Black
								)
							)
						)
						.align(Alignment.BottomStart)
				)
			}

			ProfileImage(
				contentDescription = null,
				modifier = Modifier
					.size(120.dp)
					.align(Alignment.BottomCenter)
			)
		}
	}
}


@Composable
fun ProfileImage(contentDescription: String?, modifier: Modifier = Modifier, elevation: Dp = 0.dp) {
	Surface(
		color = Color.DarkGray,
		elevation = elevation,
		shape = RoundedCornerShape(40.dp),
		modifier = modifier,
		border = BorderStroke(
			3.dp,
			Brush.linearGradient(
				listOf(
					OrangeYellow1,
					OrangeYellow1
				)
			)
		)) {
		Image(
			painter = rememberAsyncImagePainter("https://bit.ly/3BgosU6"),
			contentDescription = contentDescription,
			modifier = Modifier
				.fillMaxSize()
				.padding(10.dp)
				.clip(shape = RoundedCornerShape(40.dp)),
			contentScale = ContentScale.Crop,
		)
	}
}

@Composable
fun Stats(username: String, followers: String, following: String) {
	val name_list = listOf(
		"6",
		"days ago."
	)

	val otherCount = 3
	Column(modifier = Modifier.fillMaxWidth()) {
		Text(
			text = username,
			style = MaterialTheme.typography.h5,
			color = Color.DarkGray,
			modifier = Modifier.align(Alignment.CenterHorizontally)
		)
		Spacer(Modifier.height(4.dp))
		Text(
			text = buildAnnotatedString {
				val boldStyle = SpanStyle(
					color = Color.Black,
					fontWeight = FontWeight.Bold
				)
				append(" Last updated profile ")
				name_list.forEachIndexed { index, name ->
					pushStyle(boldStyle)
					append(name)
					pop()
					if (index < name_list.size - 1) {
						append(", ")
					}
				}
			/*	if (otherCount > 2) {
					append(" and ")
					pushStyle(boldStyle)
					append("$otherCount others")
				}*/
			},
			letterSpacing = 0.5.sp,
			lineHeight = 20.sp,
			fontSize = 12.sp,
			color = Color.DarkGray,
			modifier = Modifier.align(Alignment.CenterHorizontally)
		)
		Spacer(Modifier.height(16.dp))
		Row(modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center) {
			Column(horizontalAlignment = Alignment.CenterHorizontally) {
				Row(modifier = Modifier,
					horizontalArrangement = Arrangement.Center,
					verticalAlignment = Alignment.CenterVertically) {
					Icon(
						Icons.Filled.Star,
						"",
						tint = Color.Red,
						modifier = Modifier
							.size(20.dp)
							.align(Alignment.CenterVertically)
							.padding(horizontal = 3.dp),
					)
					Text(
						text = "5.0",
						style = MaterialTheme.typography.subtitle2,
						color = Color.DarkGray
					)
				}
				Text(
					text = "Rating",
					style = MaterialTheme.typography.subtitle2,
					color = Color.Gray
				)
			}
			Spacer(modifier = Modifier.width(16.dp))
			Divider(
				modifier = Modifier
					.height(40.dp)
					.width(1.dp)
					.padding(top = 2.dp, bottom = 2.dp),
				color = Color.Gray
			)
			Spacer(modifier = Modifier.width(16.dp))
			Column(horizontalAlignment = Alignment.CenterHorizontally) {
				Text(
					text = followers,
					style = MaterialTheme.typography.subtitle2,
					color = Color.DarkGray
				)
				Text(
					text = "Followers",
					style = MaterialTheme.typography.subtitle2,
					color = Color.Gray
				)
			}
			Spacer(modifier = Modifier.width(16.dp))
			Divider(
				modifier = Modifier
					.height(40.dp)
					.width(1.dp)
					.padding(top = 2.dp, bottom = 2.dp),
				color = Color.Gray
			)
			Spacer(modifier = Modifier.width(16.dp))
			Column(
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text(
					text = following,
					style = MaterialTheme.typography.subtitle2,
					color = Color.DarkGray
				)
				Text(
					text = "Following",
					style = MaterialTheme.typography.subtitle2,
					color = Color.Gray
				)
			}
		}

		Spacer(modifier = Modifier.height(16.dp))

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Spacer(modifier = Modifier.width(10.dp))
			TextButton(
				colors = ButtonDefaults
					.textButtonColors(
						backgroundColor = OrangeYellow1,
						contentColor = Color.White
					),
				onClick = {},
				modifier = Modifier.weight(0.4f)
			) {
				Text(
					"Follow"
				)
			}
			Spacer(modifier = Modifier.width(10.dp))
			TextButton(
				colors = ButtonDefaults
					.textButtonColors(
						backgroundColor = Color.DarkGray,
						contentColor = Color.White
					),
				onClick = {},
				modifier = Modifier.weight(0.4f)
			) {
				Text(
					"Message"
				)
			}
			Spacer(modifier = Modifier.width(10.dp))
		}
	}
}

@Composable
fun ExperienceCard(time_slots: List<TimeSlots>) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp),
		verticalArrangement = Arrangement.SpaceEvenly) {

		Card(shape = RoundedCornerShape(5.dp)) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight()
					.background(Color.LightGray)
					.padding(5.dp),
				horizontalArrangement = Arrangement.Center,
			) {
				// Experience
				Child(
					modifier = Modifier.weight(1f),
					title = "Experience",
					text = "10 yrs"
				)
				// Language
				Child(
					modifier = Modifier.weight(1f),
					title = "Cook Type",
					text = "Veg/Non Veg"
				)
				// From
				Child(
					modifier = Modifier.weight(1f),
					title = "From",
					text = "Delhi"
				)
			}
			HorizontalDivider(modifier = Modifier
				.height(3.dp)
				.padding(start = 10.dp, end = 10.dp)
				.background(Color.White))
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight()
					.background(Color.LightGray)
					.padding(5.dp),
				horizontalArrangement = Arrangement.Center,
			) {
				// Experience
				Child(
					modifier = Modifier.weight(1f),
					title = "Gender",
					text = "Male"
				)
				// Language
				Child(
					modifier = Modifier.weight(1f),
					title = "Age",
					text = "45"
				)
				// From
				Child(
					modifier = Modifier.weight(1f),
					title = "Relegion",
					text = "Hindu"
				)
			}
		}

		//Cuisines
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Cuisines",
			style = MaterialTheme.typography.subtitle2,
			color = Color.Black
		)
		Text(
			modifier = Modifier.padding(
				top = AppTheme.dimens.paddingSmall,
				bottom = AppTheme.dimens.paddingSmall
			),
			text = "North Indian food",
			style = MaterialTheme.typography.subtitle2,
			color = Color.Gray
		)

		//Languages
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Languages",
			style = MaterialTheme.typography.subtitle2,
			color = Color.Black
		)
		Text(
			modifier = Modifier.padding(
				top = AppTheme.dimens.paddingSmall,
				bottom = AppTheme.dimens.paddingSmall
			),
			text = "Experience",
			style = MaterialTheme.typography.subtitle2,
			color = Color.Gray
		)

		//Daily Visit
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Visit",
			style = MaterialTheme.typography.subtitle2,
			color = Color.Black
		)
		Text(
			modifier = Modifier.padding(
				top = AppTheme.dimens.paddingSmall,
				bottom = AppTheme.dimens.paddingSmall
			),
			text = "Available for one or two visit daily.",
			style = MaterialTheme.typography.subtitle2,
			color = Color.Gray
		)

		//Available Time Slots
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Available Part-Time Time Slots",
			style = MaterialTheme.typography.subtitle2,
			color = Color.Black
		)
		TimeSlotsComponent(timeSlots = time_slots)
		//Expectation
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Expectation",
			style = MaterialTheme.typography.subtitle2,
			color = Color.Black
		)
		Card(modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
			shape = RoundedCornerShape(5.dp)) {
			Box(modifier = Modifier
				.fillMaxWidth()
				.background(Color.LightGray)) {
				Column(modifier = Modifier
					.fillMaxWidth()
					.padding(10.dp),
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.SpaceBetween) {
					androidx.compose.material3.Text(
						modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
						text = "Charges can go up or down based on the requirements.",
						style = MaterialTheme.typography.subtitle2,
						color = Color.Black,
						textAlign = TextAlign.Center
					)
				}
			}
		}
		Spacer(modifier = Modifier.height(16.dp))

		Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
			Text(
				modifier = Modifier
					.weight(0.6f)
					.padding(top = AppTheme.dimens.paddingSmall),
				text = "Part time (Daily meals for two in two visit) ",
				style = MaterialTheme.typography.subtitle2,
				color = Color.Gray
			)

			Text(
				modifier = Modifier
					.weight(0.3f)
					.padding(top = AppTheme.dimens.paddingSmall),
				text = "Rs. 10000/month",
				style = MaterialTheme.typography.subtitle2,
				color = Color.Black
			)
		}
	}
}

@Composable
fun TimeSlotsComponent(
	timeSlots: List<TimeSlots>,
	modifier: Modifier = Modifier,
	selectedCar: TimeSlots? = null,
	onSelectedChanged: (String) -> Unit = {}) {
	var selected by remember { mutableStateOf(false) }
	Row(modifier = Modifier.fillMaxSize()) {

		LazyRow (modifier=Modifier.fillMaxSize()){
			items(timeSlots.size){ index->
				FilterChipExample(timeSlots[index].slots)
			}
		}
	}
}

@Composable
fun FilterChipExample(slots: String) {
	var selected by remember { mutableStateOf(false) }

	FilterChip(
		modifier = Modifier.padding(horizontal = 5.dp),
		onClick = { selected = !selected },
		label = { Text(text =slots) },
		selected = selected,
		leadingIcon = if (selected) {
			{
				Icon(
					imageVector = Icons.Filled.Done,
					contentDescription = "Done icon",
					modifier = Modifier.size(FilterChipDefaults.IconSize)
				)
			}
		} else {
			null
		},
	)
}

@ExperimentalFoundationApi
@Composable
fun PostsComponent(posts_list: List<Posts>, modifier: Modifier = Modifier) {
	LazyRow(modifier = Modifier.scale(1.01f)) {
		items(posts_list.size) {
			Log.d("TAG", "PostsComponent: ${posts_list[it].url}")
			Card(modifier = Modifier
				.width(300.dp)
				.height(150.dp)
				.padding(horizontal = 5.dp)) {
				Image(
					painter = rememberAsyncImagePainter(posts_list[it].url),
					contentDescription = posts_list[it].name,
					contentScale = ContentScale.Crop,
					modifier = Modifier.fillMaxSize()
				)
			}
		}
	}
}



@Composable
fun SocialMediaIcons(
	onClickIcon1: () -> Unit = {},
	onClickIcon2: () -> Unit = {},
	onClickIcon3: () -> Unit = {},
	onClickIcon4: () -> Unit = {},
	icon1Painter: Painter = painterResource(id = R.drawable.ic_call),
	icon2Painter: Painter = painterResource(id = R.drawable.ic_review),
	icon3Painter: Painter = painterResource(id = R.drawable.ic_share),
	icon4Painter: Painter = painterResource(id = R.drawable.ic_report),
	iconBackgroundColor: Color = Color.DarkGray,
	iconTintColor: Color = Color.White,
	contentPadding: Dp = 16.dp) {
	Row(modifier = Modifier
		.fillMaxWidth()
		.wrapContentHeight()
		.padding(contentPadding),
		horizontalArrangement = Arrangement.SpaceEvenly
	) {
		IconButton(
			onClick = { onClickIcon1() },
			modifier = Modifier
				.clip(CircleShape)
				.background(iconBackgroundColor),
		) {
			Icon(
				painter = icon1Painter,
				contentDescription = null,
				tint = iconTintColor,
			)
		}
		IconButton(
			onClick = { onClickIcon2() },
			modifier = Modifier
				.clip(CircleShape)
				.background(iconBackgroundColor)
		) {
			Icon(
				painter = icon2Painter,
				contentDescription = null,
				tint = iconTintColor
			)
		}
		IconButton(
			onClick = { onClickIcon3() },
			modifier = Modifier
				.clip(CircleShape)
				.background(iconBackgroundColor)
		) {
			Icon(
				painter = icon3Painter,
				contentDescription = null,
				tint = iconTintColor
			)
		}
		IconButton(
			onClick = { onClickIcon4() },
			modifier = Modifier
				.clip(CircleShape)
				.background(iconBackgroundColor)
		) {
			Icon(
				painter = icon4Painter,
				contentDescription = null,
				tint = iconTintColor
			)
		}
	}
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ProfilePreview() {
	Cook_fordTheme {
		ProfileDetailScreen(onNavigateToHomeScreen = {})
	}
}

data class Posts(val url: String, val name: String)
data class TimeSlots(val slots: String)

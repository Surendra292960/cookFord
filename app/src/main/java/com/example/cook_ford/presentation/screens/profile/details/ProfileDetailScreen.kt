package com.example.cook_ford.presentation.screens.profile.details
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExposureZero
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.data.remote.profile_response.TimeSlots
import com.example.cook_ford.presentation.component.widgets.Child
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.StarRatingBar
import com.example.cook_ford.presentation.screens.MainActivity
import com.example.cook_ford.presentation.screens.profile.details.model.ProfileCardView
import com.example.cook_ford.presentation.screens.profile.details.state.note_satate.NoteUiEvent
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.example.cook_ford.utils.Utility.shareProfile


/*val time_slots = listOf(
	TimeSlots("8am-9am"),
	TimeSlots("9am-10am"),
	TimeSlots("10am-12am"),
	TimeSlots("1pam-2pm"),
	TimeSlots("3pm-4pm"),
	TimeSlots("5pm-6pm"),
	TimeSlots("7pm-8pm"),
	TimeSlots("9pm-10pm"),
	TimeSlots("11pm-12pm")
)*/

val time_slots = mutableListOf(TimeSlots())
/*
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
		"https://bit.ly/3mtCMT8",
		"young gojo"
	),
	Posts(
		"https://bit.ly/3oAIk0M",
		"domain expansion"
	)
)*/

fun chipChangeListener(item: TimeSlots, checked: Boolean) =
	time_slots.find { it.slots == item.slots }?.let { task -> task.selected = checked }

@ExperimentalFoundationApi
@Composable
fun ProfileDetailScreen(
	navController: NavController? = null,
	onNavigateBack: () -> Unit,
	onNavigateToReViewScreen: (String) -> Unit,
	onNavigateToReportScreen: (String) -> Unit,
	onNavigateToAuthenticatedHomeRoute: () -> Unit,
) {
	val profileDetailsViewModel: ProfileDetailsViewModel = hiltViewModel()
	val profileState by remember { profileDetailsViewModel.profileState }
	var showCallBottomSheet by remember { mutableStateOf(false) }
	var showNoteBottomSheet by remember { mutableStateOf(false) }
	time_slots.clear()

	Progressbar(profileState.isLoading)
	Log.d("TAG", "ProfileDetailScreen isLoading: ${profileState.isSuccessful}")

	if (profileState.isSuccessful) {
		LazyColumn(modifier = Modifier
			.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally) {
			profileState.profile?.size?.let { profile->
				items(profile){  index->
					profileState?.profile?.get(index)?.profile?.timeSlots?.let{
						it.forEach { slots->
							time_slots.add(TimeSlots(slots.startTime.trim().plus(" - "+slots.endTime.trim())))
						}
					}
					TopBar(onNavigateBack = { onNavigateBack.invoke() })
					Spacer(modifier = Modifier.height(10.dp))
					Stats(profileState.profile!![index], "10.1M", "100")
					Spacer(modifier = Modifier.height(10.dp))
					if (showCallBottomSheet) {
						BottomSheet("Call") {
							showCallBottomSheet = false
						}
					}
					if (showNoteBottomSheet) {
						BottomSheet("Note") {
							showNoteBottomSheet = false
						}
					}
					SocialMediaIcons(
						onClickIcon1 = {
							Log.d("TAG", "ProfileDetailScreen: onClickIcon1")
							showCallBottomSheet = true
						} ,
						onClickIcon2 = {
							Log.d("TAG", "ProfileDetailScreen: onClickIcon2")
							onNavigateToReViewScreen.invoke(profileDetailsViewModel.getProfileId().toString())
						} ,
						onClickIcon3 = {
							Log.d("TAG", "ProfileDetailScreen: onClickIcon3")
						} ,
						onClickIcon4 = {
							Log.d("TAG", "ProfileDetailScreen: onClickIcon4")
							showNoteBottomSheet = true
						} ,
						onClickIcon5 = {
							Log.d("TAG", "ProfileDetailScreen: onClickIcon5")
							onNavigateToReportScreen.invoke(profileDetailsViewModel.getProfileId().toString())
						}
					)

					HorizontalDivider(modifier = Modifier.background(Color.LightGray))
					Spacer(modifier = Modifier.height(10.dp))
					ExperienceCard(profileState?.profile!![index], time_slots = time_slots)
					profileState.profile!![index].profile?.topCuisineUrls?.let { CuisineImages(topCuisineUrls = it, modifier = Modifier.padding(top = 10.dp)) }
					Spacer(modifier = Modifier.height(10.dp))
					Ratings(modifier = Modifier
						.fillMaxWidth()
						.padding(start = 10.dp, end = 10.dp))
					Spacer(modifier = Modifier.height(10.dp))
					StatusCard()
				}
			}
		}
	}
}


@Composable
fun TopBar(onNavigateBack:()->Unit) {
	Column(
		modifier = Modifier
			.clickable(onClick = { })
			.fillMaxWidth()) {
		Box(modifier = Modifier
			.height(230.dp)
			.fillMaxWidth()) {
			Box(modifier = Modifier
				.height(180.dp)
				.fillMaxWidth()) {
				Image(
					painter = rememberAsyncImagePainter("https://bit.ly/3oAIk0M"),
					contentDescription = "",
					modifier = Modifier.fillMaxSize(),
					contentScale = ContentScale.Crop,)
				Box(modifier = Modifier
					.height(90.dp)
					.fillMaxWidth()
					.background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
					.align(Alignment.BottomStart)
				)
			}

			//TopBarNavigation(onNavigateBack = {onNavigateBack.invoke()}, title = "")
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
			))) {
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
fun Stats(profile: ProfileResponse, followers: String, following: String) {
	val name_list = listOf(
		"6",
		"days ago."
	)

	val otherCount = 3
	Column(modifier = Modifier.fillMaxWidth()) {
		profile?.username?.let {
			Text(
				text = it,
				style = MaterialTheme.typography.h5,
				color = Color.DarkGray,
				modifier = Modifier.align(Alignment.CenterHorizontally))
		}

		Spacer(Modifier.height(4.dp))
		Text(
			text = buildAnnotatedString {
				val boldStyle = SpanStyle(
					color = Color.DarkGray,
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
						tint = OrangeYellow1,
						modifier = Modifier
							.size(20.dp)
							.align(Alignment.CenterVertically)
							.padding(horizontal = 3.dp),
					)
				/*	Text(
						text = if (profile.profile.total_rating.toString().isNullOrEmpty()) AppConstants.ZERO else profile.profile.total_rating.toString(),
						style = MaterialTheme.typography.subtitle2,
						color = Color.DarkGray
					)*/
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
fun ExperienceCard(profile: ProfileResponse, time_slots: List<TimeSlots>) {
	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(10.dp),
		verticalArrangement = Arrangement.SpaceEvenly) {

		Card(shape = RoundedCornerShape(5.dp)) {
			Row(modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
				.background(Color.LightGray)
				.padding(5.dp),
				horizontalArrangement = Arrangement.Center,) {
				// Experience
				Child(
					modifier = Modifier.weight(1f),
					title = "Experience",
					text = profile?.profile?.experience.toString().plus(" Yrs")
				)
				// Language
				Child(modifier = Modifier.weight(1f),
					title = "Cook Type",
					text = profile?.profile?.food_Type.toString()
				)
				// From
				profile?.location?.type?.let {
					Child(modifier = Modifier.weight(1f),
						title = "From",
						text = it
					)
				}
			}
			HorizontalDivider(modifier = Modifier
				.height(3.dp)
				.padding(start = 10.dp, end = 10.dp)
				.background(Color.White))
			Row(modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
				.background(Color.LightGray)
				.padding(5.dp),
				horizontalArrangement = Arrangement.Center) {
				// Experience
				profile?.gender?.let {
					Child(modifier = Modifier.weight(1f),
						title = "Gender",
						text = it
					)
				}
				// Language
				Child(modifier = Modifier.weight(1f),
					title = "Age",
					text = profile?.profile?.age.toString()
				)
				// From
				Child(
					modifier = Modifier.weight(1f),
					title = "Religion",
					text = "Hindu"
				)
			}
		}

		//Cuisines
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Cuisines",
			style = MaterialTheme.typography.subtitle2,
			color = Color.DarkGray
		)
		profile?.profile?.cuisine?.let {
			Text(
				modifier = Modifier.padding(
					top = AppTheme.dimens.paddingSmall,
					bottom = AppTheme.dimens.paddingSmall
				),
				text = it,
				style = MaterialTheme.typography.subtitle2,
				color = Color.Gray
			)
		}

		//Languages
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Languages",
			style = MaterialTheme.typography.subtitle2,
			color = Color.DarkGray
		)
		profile?.profile?.language?.let {
			Text(
				modifier = Modifier.padding(
					top = AppTheme.dimens.paddingSmall,
					bottom = AppTheme.dimens.paddingSmall
				),
				text = it,
				style = MaterialTheme.typography.subtitle2,
				color = Color.Gray
			)
		}

		//Daily Visit
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Visit",
			style = MaterialTheme.typography.subtitle2,
			color = Color.DarkGray
		)
		Text(
			modifier = Modifier.padding(
				top = AppTheme.dimens.paddingSmall,
				bottom = AppTheme.dimens.paddingSmall
			),
			text = "Available for ".plus(profile?.profile?.no_of_visit).plus(" visit daily."),
			style = MaterialTheme.typography.subtitle2,
			color = Color.Gray
		)

		//Available Time Slots
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Available Part-Time Time Slots",
			style = MaterialTheme.typography.subtitle2,
			color = Color.DarkGray
		)

		TimeSlotsComponent(timeSlots = time_slots, onSelectedChanged = { slots, selected->
			chipChangeListener(slots, selected).let {
				time_slots.toSet().forEach {
					if (it.selected){
						Log.d("TAG", "ExperienceCard: ${it.slots}")
					}
				}
			}
		})

		//Expectation
		Text(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
			text = "Expectation",
			style = MaterialTheme.typography.subtitle2,
			color = Color.DarkGray
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
						color = Color.DarkGray,
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
				text = "Part time (Daily meals for two visit) ",
				style = MaterialTheme.typography.subtitle2,
				color = Color.Gray
			)

			Text(
				modifier = Modifier
					.weight(0.3f)
					.padding(top = AppTheme.dimens.paddingSmall),
				text = "Rs. 10000/month",
				style = MaterialTheme.typography.subtitle2,
				color = Color.DarkGray
			)
		}
	}
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TimeSlotsComponent(
	timeSlots: List<TimeSlots>,
	modifier: Modifier = Modifier,
	selectedCar: TimeSlots? = null,
	onSelectedChanged: (TimeSlots, Boolean) -> Unit) {

	FlowRow {
		timeSlots.forEach { timeSlots ->
			timeSlots?.slots?.let {
				FilterChipExample(
					timeSlots = timeSlots,
					onChipClick = {
						onSelectedChanged(it, !it.selected)
						Log.d("TAG", "TimeSlotsComponent: $it")
					}
				)
			}
		}
	}
}

@Composable
fun FilterChipExample(timeSlots: TimeSlots, selected: Boolean? = null, onChipClick: (TimeSlots) -> Unit) {
	Log.d("TAG", "TimeSlotsComponent timeSlots : ${timeSlots.slots}")
	LaunchedEffect(key1 = true) {
		time_slots?.forEach { slots->
			Log.d("TAG", "TimeSlotsComponent : ${slots?.slots}")
			if (slots.selected) {
				Log.d("TAG", "TimeSlotsComponent selected : ${slots.slots}")
			}
		}
	}
	FilterChip(
		modifier = Modifier.padding(horizontal = 2.dp),
		onClick = { onChipClick(timeSlots) },
		label = {
			timeSlots.slots?.let { Text(it) }
		},
		selected = selected ?: timeSlots.selected,
		leadingIcon = if (selected ?: timeSlots.selected) {
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
fun CuisineImages(topCuisineUrls: List<String>, modifier: Modifier = Modifier) {
	Column(modifier = modifier
		.fillMaxWidth()
		.padding(start = 10.dp, end = 10.dp),
		verticalArrangement = Arrangement.SpaceBetween) {
		Text(
			textAlign = TextAlign.Start,
			text = "Best Dishes",
			style = MaterialTheme.typography.subtitle2,
			color = Color.DarkGray
		)

		LazyRow(modifier = modifier.scale(1.01f)) {
			items(topCuisineUrls.size) {
				Log.d("TAG", "CuisineImages: $it")
				Card(modifier = Modifier
					.width(300.dp)
					.height(150.dp)
					.padding(horizontal = 5.dp)) {
					Image(
						painter = rememberAsyncImagePainter(it),
						contentDescription = "",
						contentScale = ContentScale.Crop,
					)
				}
			}
		}
	}
}


@Composable
fun SocialMediaIcons(
	onClickIcon1: () -> Unit,
	onClickIcon2: () -> Unit = {},
	onClickIcon3: () -> Unit = {},
	onClickIcon4: () -> Unit = {},
	onClickIcon5: () -> Unit = {},
	icon1Painter: Painter = painterResource(id = R.drawable.ic_call),
	icon2Painter: Painter = painterResource(id = R.drawable.ic_review),
	icon3Painter: Painter = painterResource(id = R.drawable.ic_share),
	icon4Painter: Painter = painterResource(id = R.drawable.ic_note),
	icon5Painter: Painter = painterResource(id = R.drawable.ic_report),
	iconBackgroundColor: Color = Color.DarkGray,
	iconTintColor: Color = Color.White,
	contentPadding: Dp = 16.dp) {
	var jetCaptureView: MutableState<ProfileCardView>? = null
	val context = LocalContext.current as MainActivity

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
			onClick = {  shareProfile(context)
				//jetCaptureView?.value?.capture(jetCaptureView?.value as ProfileCardView, jetCaptureView)
				},
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
				.background(iconBackgroundColor)) {
			Icon(
				painter = icon4Painter,
				contentDescription = null,
				tint = iconTintColor
			)
		}
		IconButton(
			onClick = { onClickIcon5() },
			modifier = Modifier
				.clip(CircleShape)
				.background(iconBackgroundColor)) {
			Icon(
				painter = icon5Painter,
				contentDescription = null,
				tint = iconTintColor
			)

			//ProfileUI(jetCaptureView)
		}
	}
}


@Composable
private fun ProfileUI(jetCaptureView: MutableState<ProfileCardView>?) {

	AndroidView(modifier = Modifier.wrapContentSize(),
		factory = {
			ProfileCardView(it).apply {
				post {
					jetCaptureView?.value = this
				}
			}
		}
	)
}

@Composable
fun Ratings(modifier: Modifier) {
	var rating1 by remember { mutableFloatStateOf(0.0f) }
	repeat(4){
		Row(modifier = modifier,
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween) {
			Text(text = "Review", color = Color.DarkGray)
			StarRatingBar(
				maxStars = 5,
				rating = rating1,
				onRatingChanged = {
					rating1 = it
				}
			)
		}
	}
}

@Composable
fun StatusCard() {
	Row(modifier = Modifier
		.background(Color.LightGray)
		.padding(bottom = 20.dp)) {
		Text(
			modifier = Modifier.padding(8.dp),
			text = "All the cooks on Cook Ford are independent and manage their own profile." +
					"We are helping them to find work without commission to the Agencies/Broker." +
					"\n" +
					"\n" +
					"Please report any inaccuracy in the profile to Cook Ford or if you know the " +
					"cook, help them to update or create their profile.",
			style = MaterialTheme.typography.subtitle2,
			color = Color.DarkGray
		)
	}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(sheetType:String, onDismiss: () -> Unit) {
	val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

	ModalBottomSheet(
		onDismissRequest = { onDismiss() },
		sheetState = modalBottomSheetState,
		dragHandle = { BottomSheetDefaults.DragHandle() }) {
		if (sheetType == "Call"){
			ByCallCreditSheet()

		}else{
			AddNote()
		}
	}
}

@Composable
fun ByCallCreditSheet(){
	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(20.dp)
		.navigationBarsPadding(),

		verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)) {

		Row(horizontalArrangement = Arrangement.spacedBy(10.dp,
			Alignment.Start),
			verticalAlignment = Alignment.CenterVertically){
			Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Color.Green)
			Text(
				text = "For reference, tell the cook you their profile on Cook Ford.",
				style = MaterialTheme.typography.subtitle2,
				color = Color.DarkGray
			)
		}
		Row(horizontalArrangement = Arrangement.spacedBy(10.dp,
			Alignment.Start),
			verticalAlignment = Alignment.CenterVertically){
			Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Color.Green)
			Text(
				text = "Discuss the requirements and charges clearly before hiring.",
				style = MaterialTheme.typography.subtitle2,
				color = Color.DarkGray
			)
		}
		Row(horizontalArrangement = Arrangement.spacedBy(10.dp,
			Alignment.Start),
			verticalAlignment = Alignment.CenterVertically) {
			Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Color.Green)
			Text(
				text = "Report issue directly from the cook`s profile",
				style = MaterialTheme.typography.subtitle2,
				color = Color.DarkGray
			)
		}

		Card(modifier = Modifier, shape = RoundedCornerShape(5.dp)){
			Row(modifier = Modifier
				.fillMaxWidth()
				.background(Color.LightGray)
				.padding(5.dp),
				horizontalArrangement = Arrangement.spacedBy(7.dp,
					Alignment.Start),
				verticalAlignment = Alignment.CenterVertically) {
				Icon(Icons.Filled.Call, contentDescription = null)
				Text(text = "This might not be a good time to call the cook. \n We recommend calling between 8am-9pm",
					style = MaterialTheme.typography.subtitle2,
					color = Color.DarkGray
				)
			}
		}

		Card(modifier = Modifier, shape = RoundedCornerShape(5.dp)){
			Row(modifier = Modifier
				.fillMaxWidth()
				.background(Color.LightGray)
				.padding(5.dp),
				horizontalArrangement = Arrangement.spacedBy(7.dp,
					Alignment.Start),
				verticalAlignment = Alignment.CenterVertically) {
				Icon(Icons.Filled.AddAlert, contentDescription = null, tint = Color.DarkGray)
				Text(text = "1 call credit will be used for contacting the cook",
					style = MaterialTheme.typography.subtitle2,
					color = Color.DarkGray
				)
			}
		}

		Spacer(modifier = Modifier.height(100.dp))

		Row(modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
			verticalAlignment = Alignment.CenterVertically) {

			TextButton(
				colors = ButtonDefaults
					.textButtonColors(
						backgroundColor = OrangeYellow1,
						contentColor = Color.White
					),
				onClick = {},
				modifier = Modifier.weight(0.4f)) {
				Icon(
					Icons.Filled.ExposureZero,
					contentDescription = null,
					tint = Color.DarkGray,
					modifier = Modifier.size(30.dp)
				)
				Text(
					modifier = Modifier,
					text = "Call credit available"
				)
			}

			TextButton(
				colors = ButtonDefaults
					.textButtonColors(
						backgroundColor = Color.DarkGray,
						contentColor = Color.White
					),
				onClick = {},
				modifier = Modifier.weight(0.4f)) {
				Icon(
					Icons.Filled.ExposureZero,
					contentDescription = null,
					tint = Color.White,
					modifier = Modifier.size(30.dp)
				)
				Text(
					text = "Call credit available",
				)
			}
		}
	}
}


@Composable
fun AddNote() {

	val profileDetailsViewModel: ProfileDetailsViewModel = hiltViewModel()
	//val viewState by remember { profileDetailsViewModel.viewState }
	val noteState by remember { profileDetailsViewModel.noteState }

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		NoteForm(
			noteState = noteState,
			//viewState = false,
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 10.dp, end = 10.dp),
			onNoteChange = { inputString ->
				profileDetailsViewModel.onUiEvent(
					noteUiEvent = NoteUiEvent.NoteChanged(
						inputString
					)
				)
			},
			onSubmit = {
				profileDetailsViewModel.onUiEvent(noteUiEvent = NoteUiEvent.Submit)
			})
	}
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ProfilePreview() {
	Cook_fordTheme {
		ProfileDetailScreen(
			onNavigateBack = {},
			onNavigateToReViewScreen = {},
			onNavigateToReportScreen = {},
			onNavigateToAuthenticatedHomeRoute = {})
	}
}


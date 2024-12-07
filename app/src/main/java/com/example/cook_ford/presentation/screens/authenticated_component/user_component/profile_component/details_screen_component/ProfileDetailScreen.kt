package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.details_screen_component
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material.icons.filled.ExposureZero
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.data.remote.profile_response.TimeSlots
import com.example.cook_ford.presentation.component.CuisineSlotComponent
import com.example.cook_ford.presentation.component.TimeSlotsComponent
import com.example.cook_ford.presentation.component.widgets.ButtonIcons
import com.example.cook_ford.presentation.component.widgets.Child
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.RatingStar
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.CookProfileDetailsViewModel
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.model.ProfileCardView
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.state.note_satate.CookNoteUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated_component.main_screen_component.MainActivity
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.FontName
import com.example.cook_ford.presentation.theme.LightGray_2
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.example.cook_ford.utils.AppConstants
import com.example.cook_ford.utils.Utility.shareProfile
import com.google.gson.Gson


@ExperimentalFoundationApi
@Composable
fun ProfileDetailScreen(
	navController: NavHostController,
	onNavigateBack: () -> Unit,
	profileResponse:ProfileResponse,
	onNavigateToReViewScreen: () -> Unit,
	onNavigateToReportScreen: () -> Unit,
	onNavigateToCallCreditScreen: () -> Unit,
	onNavigateToMessageScreen: () -> Unit,
	onNavigateToAuthenticatedHomeRoute: () -> Unit) {

	val profileDetailsViewModel: CookProfileDetailsViewModel = hiltViewModel()
	val profileState by remember { profileDetailsViewModel.profileState }
	Progressbar(profileState.isLoading)
	LaunchedEffect(key1 = true) {
		profileDetailsViewModel.setProfileData(profileResponse)
	}
	Log.d("TAG", "ProfileDetailScreen isLoading: ${profileState.isLoading}")

	if (profileState.isSuccessful) {
		LazyColumn(modifier = Modifier
			.background(Color.White)
			.fillMaxSize(),
		) {
			profileState.profileResponse?.size?.let { size->
				items(size){  index->
					com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.TopBar(
						onNavigateBack = { onNavigateBack.invoke() })
					Spacer(modifier = Modifier.height(10.dp))

					if (profileState.profileResponse!![index].userType.equals(AppConstants.PROVIDER, ignoreCase = true)) {
						com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.Status(
							profileState.profileResponse!![index],
							"10.1M",
							"100",
							clickOnChat = {
								navController.currentBackStackEntry?.savedStateHandle?.apply {
									set(
										"profileResponse",
										Gson().toJson(profileState.profileResponse!![index])
									)
								}
								onNavigateToMessageScreen.invoke()
							})
						Spacer(modifier = Modifier.height(10.dp))
						com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.ProviderSocialMediaIconsCard(
							profileDetailsViewModel,
							onNavigateToReViewScreen = {
								navController.currentBackStackEntry?.savedStateHandle?.apply {
									set(
										"profileResponse",
										Gson().toJson(profileState.profileResponse!![index])
									)
								}
								onNavigateToReViewScreen.invoke()
							},
							onNavigateToReportScreen = {
								navController.currentBackStackEntry?.savedStateHandle?.apply {
									set(
										"profileResponse",
										Gson().toJson(profileState.profileResponse!![index])
									)
								}
								onNavigateToReportScreen.invoke()
							},
							onNavigateToCallCreditScreen = {
								navController.currentBackStackEntry?.savedStateHandle?.apply {
									set(
										"profileResponse",
										Gson().toJson(profileState.profileResponse!![index])
									)
								}
								onNavigateToCallCreditScreen.invoke()
							},
						)
						HorizontalDivider(modifier = Modifier.background(Color.LightGray))
						Spacer(modifier = Modifier.height(10.dp))
						com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.ExperienceCard(
							profileState.profileResponse!![index],
							timeSlots = profileDetailsViewModel.getTimeSlots()
						)
						profileState.profileResponse?.get(index)?.profile?.topCuisineUrls?.let {
							com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.CuisineImages(
								topCuisineUrls = it,
								modifier = Modifier.padding(top = 10.dp)
							)
						}
						Spacer(modifier = Modifier.height(10.dp))

						Column(modifier = Modifier
							.padding(start = 10.dp, end = 10.dp)
							.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(5.dp), horizontalAlignment = Alignment.Start) {

							profileState.profileResponse!![index].profile?.feedback_rating?.forEach { rating->

								MediumTitleText(
									modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall,
										bottom = AppTheme.dimens.paddingSmall),
									text = "Ratings",
									textAlign = TextAlign.Start,
									textColor = Color.DarkGray,
									fontWeight = FontWeight.W700
								)

								com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.Ratings(
									text = "FoodQuality",
									feedbackRating = rating.food_quality?.toFloat()
								)
								com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.Ratings(
									text = "Hygiene",
									feedbackRating = rating.hygiene?.toFloat()
								)
								com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.Ratings(
									text = "Service",
									feedbackRating = rating.service?.toFloat()
								)
								com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.Ratings(
									text = "Cleanliness",
									feedbackRating = rating.cleanliness?.toFloat()
								)
								com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.Ratings(
									text = "Punctuality",
									feedbackRating = rating.punctuality?.toFloat()
								)
							}

							Spacer(modifier = Modifier.height(10.dp))

							profileState.profileResponse!![index].profile?.headline?.let {

								MediumTitleText(
									modifier = Modifier
										.padding(top = AppTheme.dimens.paddingSmall),
									text = "Headlines",
									textAlign = TextAlign.Start,
									textColor = Color.DarkGray,
									fontWeight = FontWeight.W700
								)

								MediumTitleText(
									modifier = Modifier
										.padding(bottom = AppTheme.dimens.paddingSmall),
									text = it,
									textAlign = TextAlign.Start,
									textColor = Color.Gray,
									fontWeight = FontWeight.W400
								)
							}

							profileState.profileResponse!![index].profile?.bio?.let {

								MediumTitleText(
									modifier = Modifier
										.padding(top = AppTheme.dimens.paddingSmall),
									text = "Bio Data",
									textAlign = TextAlign.Start,
									textColor = Color.DarkGray,
									fontWeight = FontWeight.W700
								)

								MediumTitleText(
									modifier = Modifier
										.padding(bottom = AppTheme.dimens.paddingSmall),
									text = it,
									textAlign = TextAlign.Start,
									textColor = Color.Gray,
									fontWeight = FontWeight.W400
								)
							}

							profileState.profileResponse!![index].profile?.about?.let {
								MediumTitleText(
									modifier = Modifier
										.padding(top = AppTheme.dimens.paddingSmall),
									text = "Bio",
									textAlign = TextAlign.Start,
									textColor = Color.DarkGray,
									fontWeight = FontWeight.W700
								)

								MediumTitleText(
									modifier = Modifier
										.padding(bottom = AppTheme.dimens.paddingSmall),
									text = it,
									textAlign = TextAlign.Start,
									textColor = Color.Gray,
									fontWeight = FontWeight.W400
								)
							}
						}

						Spacer(modifier = Modifier.height(10.dp))


						Spacer(modifier = Modifier.height(20.dp))
						com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.FooterStatus()
					}
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
			com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.ProfileImage(
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
fun Stats(profile: ProfileResponse, followers: String, following: String, clickOnChat:()->Unit) {
	val nameList = listOf(
		"6",
		"days ago."
	)
	Column(modifier = Modifier.fillMaxWidth()) {
		profile.username?.let {

			Text(
				text = it,
				style = MaterialTheme.typography.h5,
				color = Color.DarkGray,
				fontWeight = FontWeight.W500,
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
				nameList.forEachIndexed { index, name ->
					pushStyle(boldStyle)
					append(name)
					pop()
					if (index < nameList.size - 1) {
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
					profile.profile?.total_rating?.toString()?.let {
						Text(
							text = it,
							style = MaterialTheme.typography.subtitle2,
							color = Color.DarkGray
						)
					}
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
				MediumTitleText(
					modifier = Modifier,
					text = "Follow",
					fontWeight = FontWeight.W900,
					textAlign = TextAlign.Center,
					textColor = Color.White
				)
			}
			Spacer(modifier = Modifier.width(10.dp))
			TextButton(
				colors = ButtonDefaults
					.textButtonColors(
						backgroundColor = Color.DarkGray,
						contentColor = Color.White
					),
				onClick = clickOnChat,
				modifier = Modifier.weight(0.4f)
			) {
				MediumTitleText(
					modifier = Modifier,
					text = "Message",
					fontWeight = FontWeight.W500,
					textAlign = TextAlign.Center,
					textColor = Color.White
				)
			}
			Spacer(modifier = Modifier.width(10.dp))
		}
	}
}

@Composable
fun ProviderSocialMediaIconsCard(
	profileDetailsViewModel: CookProfileDetailsViewModel,
	onNavigateToReViewScreen: () -> Unit,
	onNavigateToCallCreditScreen: () -> Unit,
	onNavigateToReportScreen: () -> Unit
) {
	var showCallBottomSheet by remember { mutableStateOf(false) }
	var showNoteBottomSheet by remember { mutableStateOf(false) }
	if (showCallBottomSheet) {
		com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.BottomSheet(
			"Call",
			onNavigateToCallCreditScreen = onNavigateToCallCreditScreen
		) {
			showCallBottomSheet = false
		}
	}
	if (showNoteBottomSheet) {
		com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.BottomSheet(
			"Note",
			onNavigateToCallCreditScreen = {}) {
			showNoteBottomSheet = false
		}
	}
	com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.SocialMediaIcons(
		onClickIcon1 = {
			Log.d("TAG", "ProfileDetailScreen: onClickIcon1")
			showCallBottomSheet = true
		},
		onClickIcon2 = {
			Log.d("TAG", "ProfileDetailScreen: onClickIcon2")
			onNavigateToReViewScreen.invoke()
		},
		onClickIcon3 = {
			Log.d("TAG", "ProfileDetailScreen: onClickIcon3")
		},
		onClickIcon4 = {
			Log.d("TAG", "ProfileDetailScreen: onClickIcon4")
			showNoteBottomSheet = true
		},
		onClickIcon5 = {
			Log.d("TAG", "ProfileDetailScreen: onClickIcon5")
			onNavigateToReportScreen.invoke()
		}
	)
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
	contentPadding: Dp = AppTheme.dimens.paddingSmall
) {
	var jetCaptureView: MutableState<ProfileCardView>? = null
	val context = LocalContext.current as MainActivity

	Row(modifier = Modifier
		.fillMaxWidth()
		.wrapContentHeight()
		.padding(contentPadding),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceEvenly) {
		Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
			MediumTitleText(
				modifier = Modifier.padding(top = 5.dp),
				text = "Call",
				fontWeight = FontWeight.W400,
				textAlign = TextAlign.Center,
				textColor = Color.Gray
			)
		}

		Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
			MediumTitleText(
				modifier = Modifier.padding(top = 5.dp),
				text = "Review",
				fontWeight = FontWeight.W400,
				textAlign = TextAlign.Center,
				textColor = Color.Gray
			)
		}

		Column(horizontalAlignment = Alignment.CenterHorizontally) {
			IconButton(
				onClick = {
					shareProfile(context)
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
			MediumTitleText(
				modifier = Modifier.padding(top = 5.dp),
				text = "Share",
				fontWeight = FontWeight.W400,
				textAlign = TextAlign.Center,
				textColor = Color.Gray
			)
		}

		Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

			MediumTitleText(
				modifier = Modifier.padding(top = 5.dp),
				text = "Note",
				fontWeight = FontWeight.W400,
				textAlign = TextAlign.Center,
				textColor = Color.Gray
			)
		}

		Column(horizontalAlignment = Alignment.CenterHorizontally) {
			IconButton(
				onClick = { onClickIcon5() },
				modifier = Modifier
					.clip(CircleShape)
					.background(iconBackgroundColor)
			) {
				Icon(
					painter = icon5Painter,
					contentDescription = null,
					tint = iconTintColor
				)
			}
			MediumTitleText(
				modifier = Modifier.padding(top = 5.dp),
				text = "Report",
				fontWeight = FontWeight.W400,
				textAlign = TextAlign.Center,
				textColor = Color.Gray
			)
			//ProfileUI(jetCaptureView)
		}
	}
}

@Composable
fun ExperienceCard(profile: ProfileResponse, timeSlots: List<TimeSlots>) {
	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(all = AppTheme.dimens.paddingSmall), verticalArrangement = Arrangement.SpaceEvenly) {

		Card(shape = RoundedCornerShape(5.dp)) {
			Row(modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
				.background(LightGray_2)
				.padding(5.dp),
				horizontalArrangement = Arrangement.Center,) {
				// Experience
				Child(
					modifier = Modifier.weight(1f),
					title = "Experience",
					text = profile.profile?.experience.toString().plus(" Yrs")
				)
				// Language
				Child(modifier = Modifier.weight(1f),
					title = "Cook Type",
					text = profile.profile?.food_Type.toString()
				)
				// From
				profile.location?.type?.let {
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
				.background(LightGray_2)
				.padding(5.dp),
				horizontalArrangement = Arrangement.Center) {
				// Experience
				profile.gender?.let {
					Child(modifier = Modifier.weight(1f),
						title = "Gender",
						text = it
					)
				}
				// Language
				Child(modifier = Modifier.weight(1f),
					title = "Age",
					text = profile.profile?.age.toString()
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
		profile.profile?.cuisine?.let {

			MediumTitleText(
				modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
				text = "Cuisines",
				textAlign = TextAlign.Start,
				textColor = Color.DarkGray,
				fontWeight = FontWeight.W700
			)

			CuisineSlotComponent(slots = it, textColor = Color.Gray, backgroundColor = Color.Transparent, borderColor = Color.Transparent)
		}

		//Languages
		profile.profile?.language?.let {

			MediumTitleText(
				modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
				text = "Languages",
				textAlign = TextAlign.Start,
				textColor = Color.DarkGray,
				fontWeight = FontWeight.W700
			)

			CuisineSlotComponent(slots = it, textColor = Color.Gray, backgroundColor = Color.Transparent, borderColor = Color.Transparent)
		}

		//Daily Visit
		profile.profile?.no_of_visit?.let {

			MediumTitleText(
				modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
				text = "Visit",
				textAlign = TextAlign.Start,
				textColor = Color.DarkGray,
				fontWeight = FontWeight.W700
			)

			MediumTitleText(
				modifier = Modifier.padding(
					top = AppTheme.dimens.paddingSmall,
					bottom = AppTheme.dimens.paddingSmall),
				text = "Available for ".plus(it).plus(" visit daily."),
				textAlign = TextAlign.Start,
				textColor = Color.Gray,
				fontWeight = FontWeight.W400
			)
		}

		//Available Time Slots
		if (timeSlots.isNotEmpty()) {
			MediumTitleText(
				modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
				text = "Available Part-Time Time Slots",
				textAlign = TextAlign.Start,
				textColor = Color.DarkGray,
				fontWeight = FontWeight.W700
			)
			TimeSlotsComponent(timeSlots = timeSlots)
		}

		//Expectation
		MediumTitleText(
			modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall,
				bottom = AppTheme.dimens.paddingSmall),
			text = "Expectation",
			textAlign = TextAlign.Start,
			textColor = Color.DarkGray,
			fontWeight = FontWeight.W700
		)

		Card(modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
			shape = RoundedCornerShape(5.dp)) {
			Row (modifier = Modifier
				.background(LightGray_2)
				.padding(start = 5.dp, end = 5.dp)
				.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceEvenly,) {
				MediumTitleText(
					modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall,
						bottom = AppTheme.dimens.paddingSmall),
					text = "Charges can go up or down based on the requirements.",
					textAlign = TextAlign.Start,
					textColor = Color.DarkGray,
					fontWeight = FontWeight.Normal
				)
			}
		}
		Spacer(modifier = Modifier.height(16.dp))

		Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
			profile.profile?.no_of_visit?.let {
				MediumTitleText(
					modifier = Modifier
						.weight(0.6f)
						.padding(
							top = AppTheme.dimens.paddingSmall,
							bottom = AppTheme.dimens.paddingSmall
						),
					text = ("Part time (Daily meals for ").plus(it).plus(" visit)"),
					textAlign = TextAlign.Start,
					textColor = Color.Gray,
					fontWeight = FontWeight.W400
				)
			}

			profile.profile?.parttimeprice?.let { price->
				if (price!=0){
					MediumTitleText(
						modifier = Modifier
							.weight(0.3f)
							.padding(
								top = AppTheme.dimens.paddingSmall,
								bottom = AppTheme.dimens.paddingSmall
							),
						text = ("Rs. ").plus(price).plus("/month"),
						textAlign = TextAlign.End,
						textColor = Color.DarkGray,
						fontWeight = FontWeight.W700
					)
				}
			}
		}
	}
}


@ExperimentalFoundationApi
@Composable
fun CuisineImages(topCuisineUrls: List<String>, modifier: Modifier = Modifier) {
	val listState = rememberLazyListState()
	if (topCuisineUrls.isNotEmpty()){
		Column(modifier = modifier
			.fillMaxWidth()
			.padding(start = 10.dp, end = 10.dp),
			verticalArrangement = Arrangement.SpaceBetween) {

			MediumTitleText(
				modifier = Modifier
					.padding(top = AppTheme.dimens.paddingSmall,
						bottom = AppTheme.dimens.paddingSmall),
				text = "Best Dishes",
				textAlign = TextAlign.Start,
				textColor = Color.DarkGray,
				fontWeight = FontWeight.W700
			)

			LazyRow(state = listState, modifier = modifier.scale(1.01f)) {
				items(topCuisineUrls.size) { index->
					topCuisineUrls[index]?.let {
						Card(
							modifier = Modifier
								.width(300.dp)
								.height(150.dp)
								.padding(horizontal = 5.dp)
						) {
							Image(
								painter = rememberAsyncImagePainter(topCuisineUrls[index]),
								contentDescription = "",
								modifier = Modifier.fillMaxSize(),
								contentScale = ContentScale.Crop,
							)
						}
					}
				}
			}
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
fun Ratings(text: String, feedbackRating: Float?) {

	Row(modifier = Modifier
		.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween) {

		MediumTitleText(
			modifier = Modifier,
			text = text,
			textAlign = TextAlign.Start,
			textColor = Color.DarkGray,
			fontWeight = FontWeight.W700
		)

		if (feedbackRating != null) {
			RatingStar(
				rating = feedbackRating,
				maxRating = 5,
				onStarClick = {},
				isIndicator = false
			)
		}
	}
}

@Composable
fun FooterStatus() {
	Row(modifier = Modifier
		.background(LightGray_2)
		.padding(bottom = 20.dp)) {

		MediumTitleText(
			modifier = Modifier
				.padding(all = AppTheme.dimens.paddingSmall),
			text = "All the cooks on Cook Ford are independent and manage their own profile." +
					"We are helping them to find work without commission to the Agencies/Broker." +
					"\n" +
					"\n" +
					"Please report any inaccuracy in the profile to Cook Ford or if you know the " +
					"cook, help them to update or create their profile.",
			textAlign = TextAlign.Start,
			textColor = Color.DarkGray,
			fontWeight = FontWeight.W500
		)
	}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(sheetType:String, onNavigateToCallCreditScreen: () -> Unit,  onDismiss: () -> Unit) {
	val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

	ModalBottomSheet(
		onDismissRequest = { onDismiss() },
		sheetState = modalBottomSheetState,
		dragHandle = null) {
		if (sheetType == "Call"){
			com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.ByCallCreditSheet(
				onNavigateToCallCreditScreen = onNavigateToCallCreditScreen
			)

		}else{
			com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.AddNote()
		}
	}
}

@Composable
fun ByCallCreditSheet(onNavigateToCallCreditScreen: () -> Unit){
	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(top = 20.dp)
		.navigationBarsPadding(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterVertically)) {

		Spacer(modifier = Modifier.height(10.dp))

		Text(
			text = "Call before you hire . . .",
			style = MaterialTheme.typography.subtitle2,
			color = Color.DarkGray,
			fontSize = 20.sp,
			fontWeight = FontWeight.Bold,
			fontFamily = FontName
		)

		Row(modifier = Modifier
			.padding(start = 20.dp, end = 20.dp)
			.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(10.dp,
			Alignment.Start),
			verticalAlignment = Alignment.CenterVertically){
			Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = DeepGreen)
			Text(
				text = "For reference, tell the cook you their profile on Cook Ford.",
				style = MaterialTheme.typography.subtitle2,
				color = Color.DarkGray
			)
		}
		Row(modifier = Modifier
			.padding(start = 20.dp, end = 20.dp)
			.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(10.dp,
			Alignment.Start),
			verticalAlignment = Alignment.CenterVertically){
			Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = DeepGreen)
			Text(
				text = "Discuss the requirements and charges clearly before hiring.",
				style = MaterialTheme.typography.subtitle2,
				color = Color.DarkGray
			)
		}
		Row(modifier = Modifier
			.padding(start = 20.dp, end = 20.dp)
			.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(10.dp,
			Alignment.Start),
			verticalAlignment = Alignment.CenterVertically){
			Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = DeepGreen)
			Text(
				text = "Report issue directly from the cook`s profile",
				style = MaterialTheme.typography.subtitle2,
				color = Color.DarkGray
			)
		}

		Card(modifier = Modifier.padding(start = 20.dp, end = 20.dp), shape = RoundedCornerShape(5.dp)){
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

		Card(modifier = Modifier.padding(start = 20.dp, end = 20.dp), shape = RoundedCornerShape(5.dp)){
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

		Spacer(modifier = Modifier.height(30.dp))

		Row(modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 20.dp, start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
			OutlinedSmallSubmitButton(
				modifier = Modifier
					.padding(top = AppTheme.dimens.paddingLarge)
					.weight(1f),
				text = "Call Credit",
				textColor = Color.White,
				isLoading = false,
				backgroundColor = Color.LightGray,
				icon = ButtonIcons(leadingIcon = Icons.Default.ExposureZero, tintColor = DeepGreen, leadingIconSize = 30.dp),
				onClick = { /*onSubmit*/ }
			)

			Spacer(modifier = Modifier.width(20.dp))

			OutlinedSmallSubmitButton(
				modifier = Modifier
					.padding(top = AppTheme.dimens.paddingLarge)
					.weight(1f),
				text = "Buy Call Credit",
				textColor = Color.White,
				isLoading = false,
				backgroundColor = OrangeYellow1,
				onClick = onNavigateToCallCreditScreen
				/*navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set(
                        "profileResponse",
                        Gson().toJson(accountState.profileResponse)
                    )
                }
                onNavigateToCallCreditScreen.invoke()*/

			)
		}
	}
}


@Composable
fun AddNote() {
	val profileDetailsViewModel: CookProfileDetailsViewModel = hiltViewModel()
	//val viewState by remember { profileDetailsViewModel.viewState }
	val noteState by remember { profileDetailsViewModel.noteState }

	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(all = 20.dp)) {

		Spacer(modifier = Modifier.height(10.dp))

		com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.NoteForm(
			cookNoteState = noteState,
			viewState = false,
			modifier = Modifier
				.fillMaxWidth()
				.padding(start = 10.dp, end = 10.dp),
			onNoteChange = { inputString ->
				profileDetailsViewModel.onNoteUiEvent(
					noteUiEvent = CookNoteUiEvent.NoteChanged(
						inputString
					)
				)
			},
			onSubmit = {
				profileDetailsViewModel.onNoteUiEvent(noteUiEvent = CookNoteUiEvent.Submit)
			})
	}
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ProfilePreview() {
	Cook_fordTheme {
		ProfileDetailScreen(
			navController = rememberNavController(),
			onNavigateBack = {},
			profileResponse = ProfileResponse(),
			onNavigateToReViewScreen = {},
			onNavigateToReportScreen = {},
			onNavigateToAuthenticatedHomeRoute = {},
			onNavigateToCallCreditScreen = {},
			onNavigateToMessageScreen = {})
	}
}


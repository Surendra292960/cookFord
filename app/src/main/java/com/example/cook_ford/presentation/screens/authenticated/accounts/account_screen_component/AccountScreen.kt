package com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component

import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExposureZero
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component.state.AccountState
import com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component.state.ReviewState
import com.example.cook_ford.presentation.screens.authenticated.accounts.account_screen_component.state.ReviewUiEvent
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.LightGreen
import com.example.cook_ford.presentation.theme.LightGreen1
import com.example.cook_ford.presentation.theme.Orange
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.example.cook_ford.presentation.theme.FontName
import com.google.gson.Gson


@Composable
fun AccountScreen(
    navController: NavHostController,
    onNavigateToEditProfile: () -> Unit,
    onNavigateToCallCreditScreen: () -> Unit,
    onNavigateToAddCookScreen: () -> Unit,
    onNavigateToPostJobScreen: () -> Unit,
    onNavigateToContactUsScreen: () -> Unit,
    onNavigateToReviewUsScreen: () -> Unit) {

    var showReviewBottomSheet by remember { mutableStateOf(false) }
    val accountViewModel : AccountViewModel = hiltViewModel()
    //val viewState by remember { profileDetailsViewModel.viewState }
    val accountState by remember { accountViewModel.accountState }
    val reviewState by remember { accountViewModel.reviewState }

    Progressbar(showProgressbar = accountState.isLoading)
    if (accountState.isSuccessful){
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {

            AccountProfileImage(
                accountState = accountState,
                onNavigateToEditProfile = {
                navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                onNavigateToEditProfile.invoke()
            })
            HorizontalDivider(modifier = Modifier, color = Color.LightGray)
            CallCreditButtons(
                onNavigateToCallCreditScreen = {
                navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                onNavigateToCallCreditScreen.invoke()
                }
            )

            AccountProfileContent(
                textColor = Orange,
                leadingIcon = Icons.Filled.Circle,
                trailingIcon = Icons.Filled.ArrowForwardIos,
                title = "Spin the Wheel",
                subtitle = "Play and Win Call Credits and Documents",
                tintColor = Orange,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                navigationRoute = "Spin",
                onNavigateTo = { route->
                    when (route) {
                        "Spin" -> {
                            //onNavigateToPostJobScreen.invoke()
                        }
                        "AddCook" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToAddCookScreen.invoke()
                        }
                        "ContactUs" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToContactUsScreen.invoke()
                        }
                        "ReviewUs" -> {
                            showReviewBottomSheet = true
                            onNavigateToReviewUsScreen.invoke()
                        }
                        "PostJob" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToPostJobScreen.invoke()
                        }
                    }
                },
            )

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.NotificationsActive,
                trailingIcon = Icons.Filled.ArrowForwardIos,
                title = "Post Job",
                subtitle = "Get Notification when any cook shows interest",
                tintColor = Color.DarkGray,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                navigationRoute = "PostJob",
                onNavigateTo = { route->
                    when (route) {
                        "Spin" -> {
                            // onNavigateToPostJobScreen.invoke()
                        }
                        "AddCook" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToAddCookScreen.invoke()
                        }
                        "ContactUs" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToContactUsScreen.invoke()
                        }
                        "ReviewUs" -> {
                            showReviewBottomSheet = true
                            onNavigateToReviewUsScreen.invoke()
                        }
                        "PostJob" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToPostJobScreen.invoke()
                        }
                    }
                },
            )

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.PersonAdd,
                trailingIcon = Icons.Filled.ArrowForwardIos,
                title = "Add Your Cook",
                subtitle = "",
                tintColor = Color.DarkGray,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                navigationRoute = "AddCook",
                onNavigateTo = { route->
                    when (route) {
                        "Spin" -> {
                            //onNavigateToPostJobScreen.invoke()
                        }
                        "AddCook" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToAddCookScreen.invoke()
                        }
                        "ContactUs" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToContactUsScreen.invoke()
                        }
                        "ReviewUs" -> {
                            showReviewBottomSheet = true
                            onNavigateToReviewUsScreen.invoke()
                        }
                        "PostJob" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToPostJobScreen.invoke()
                        }
                    }
                },
            )

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.People,
                trailingIcon = Icons.Filled.ArrowForwardIos,
                title = "Tell Your friends and family",
                subtitle = "",
                tintColor = Color.DarkGray,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                navigationRoute = "",
                onNavigateTo = {},
            )

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.ContactPhone,
                trailingIcon = Icons.Filled.Email,
                title = "Contact Us",
                subtitle = "",
                tintColor = Color.DarkGray,
                trailingIconSize = 25.dp,
                leadingIconSize = 30.dp,
                navigationRoute = "ContactUs",
                onNavigateTo = { route->
                    when (route) {
                        "Spin" -> {
                            // onNavigateToPostJobScreen.invoke()
                        }
                        "AddCook" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToAddCookScreen.invoke()
                        }
                        "ContactUs" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToContactUsScreen.invoke()
                        }
                        "ReviewUs" -> {
                            showReviewBottomSheet = true
                            onNavigateToReviewUsScreen.invoke()
                        }
                        "PostJob" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToPostJobScreen.invoke()
                        }
                    }
                },
            )

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.Reviews,
                trailingIcon = Icons.Filled.ArrowForwardIos,
                title = "Review us",
                subtitle = "Good or bad. we are listening",
                tintColor = Color.DarkGray,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                navigationRoute = "ReviewUs",
                onNavigateTo = { route->
                    when (route) {
                        "Spin" -> {
                            //onNavigateToPostJobScreen.invoke()
                        }
                        "AddCook" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToAddCookScreen.invoke()
                        }
                        "ContactUs" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToContactUsScreen.invoke()
                        }
                        "ReviewUs" -> {
                            showReviewBottomSheet = true
                            onNavigateToReviewUsScreen.invoke()
                        }
                        "PostJob" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToPostJobScreen.invoke()
                        }
                    }
                },
            )
            HorizontalDivider(modifier = Modifier.padding(top = 5.dp), color = Color.LightGray)

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.Person,
                trailingIcon = Icons.Filled.ArrowForwardIos,
                title = "SignIn as Cook",
                subtitle = "Good or bad. we are listening",
                tintColor = Color.DarkGray,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                navigationRoute = "SignInAsCook",
                onNavigateTo = { route->
                    when (route) {
                        "Spin" -> {
                            //onNavigateToPostJobScreen.invoke()
                        }
                        "AddCook" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToAddCookScreen.invoke()
                        }
                        "ContactUs" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToContactUsScreen.invoke()
                        }
                        "ReviewUs" -> {
                            showReviewBottomSheet = true
                            onNavigateToReviewUsScreen.invoke()
                        }
                        "PostJob" -> {
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(accountState.profileResponse)) }
                            onNavigateToPostJobScreen.invoke()
                        }
                    }
                },
            )

            FooterStatus()

            if (showReviewBottomSheet) {
                BottomSheet("Review", reviewState = reviewState, accountViewModel = accountViewModel) {
                    showReviewBottomSheet = false
                    accountViewModel.reviewState.value = ReviewState()
                    Log.d("TAG", "AccountScreen dismiss : ")
                }
            }
        }
    }
}

@Composable
fun CallCreditButtons(onNavigateToCallCreditScreen: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Column(modifier = Modifier) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.ExposureZero,
                    contentDescription = null,
                    tint = OrangeYellow1,
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "call credit  \navailable",
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp,
                    color = Orange,
                    fontFamily = FontName,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Column(modifier = Modifier) {
            ExtendedFloatingActionButton(
                backgroundColor = LightGreen,
                onClick = { onNavigateToCallCreditScreen.invoke() },
                icon = { Icon(Icons.Filled.Cookie, contentDescription = "") },
                text = {
                    Text(
                        text = "By call credits  \n(View plans)",
                        fontFamily = FontName,
                        fontWeight = FontWeight.Medium
                    )
                },
            )
        }
    }
}


@Composable
fun AccountProfileImage(accountState:AccountState, onNavigateToEditProfile: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 8.dp)) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)) {
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    shape = CircleShape,
                    elevation = 2.dp,
                    border = BorderStroke(1.dp, Color.LightGray)) {

                    Image(
                        painter = painterResource(id = R.drawable.male_chef),
                        contentDescription = "Profile Photo",
                        modifier = Modifier,
                        contentScale = ContentScale.Crop,
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_verified),
                    tint = Color.Green,
                    contentDescription = "Check mark",
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }

            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Welcome Back",
                    color = Color.Gray,
                    fontSize = 20.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Start)
                )
                accountState.profileResponse?.let {
                    Text(
                        text = it.username.toString(),
                        color = Color.DarkGray,
                        fontSize = 20.sp,
                        fontFamily = FontName,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
            }
        }

        Icon(
            Icons.Filled.Edit,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .clickable { onNavigateToEditProfile.invoke() }
        )
    }
}

@Composable
fun AccountProfileContent(
    textColor: Color,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    title: String,
    subtitle: String,
    tintColor: Color,
    trailingIconSize: Dp,
    leadingIconSize: Dp,
    navigationRoute: String,
    onNavigateTo: (String) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .clickable { onNavigateTo.invoke(navigationRoute) },
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                leadingIcon,
                tint = tintColor,
                contentDescription = ""
            )

            Column(
                modifier = Modifier.padding(start = 15.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = textColor,
                    fontSize = 15.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Start)
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        color = textColor,
                        fontSize = 13.sp,
                        fontFamily = FontName,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
            }
        }

        Icon(
            trailingIcon,
            contentDescription = "",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(trailingIconSize)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun FooterStatus() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = "Terms of Use",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = "Privacy Policy",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = "License",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun AnimatedImage() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                //add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context).data(data = R.drawable.ic_check).apply(block = {
                size(Size.ORIGINAL)
            }).build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .fillMaxWidth(),
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    sheetType: String,
    reviewState: ReviewState,
    accountViewModel: AccountViewModel,
    onDismiss: () -> Unit) {
    Log.d("TAG", "BottomSheet: ${Gson().toJson(reviewState)}")
    val reviewBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (sheetType == "Review") {
        ModalBottomSheet(onDismissRequest = { onDismiss() }, sheetState = reviewBottomSheetState, dragHandle = null) {

            if (reviewState.isSuccessful) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()) {

                    Spacer(modifier = Modifier.height(20.dp))

                    AnimatedImage()

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Thank you",
                        color = Color.DarkGray,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontName,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.subtitle2
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Thanks for sharing your thoughts.\n We appreciate your feedback!",
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontFamily = FontName,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    // SignIn Submit Button
                    SubmitButton(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                        text = stringResource(id = R.string.done_button_text),
                        isLoading = false,
                        onClick = onDismiss
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()) {

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "We are listening",
                        color = Color.DarkGray,
                        fontSize = 24.sp,
                        fontFamily = FontName,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    )
                    Text(
                        text = "Tell us what did you like or what we can improve for you",
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontFamily = FontName,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 20.dp, end = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "How did we do?",
                            color = Color.DarkGray,
                            fontSize = 18.sp,
                            fontFamily = FontName,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.subtitle2,
                        )
                        Text(
                            text = if (reviewState.rating == 0.0f) {
                                "Not Rated"
                            } else if (reviewState.rating == 1.0f) {
                                "Not so good"
                            } else if (reviewState.rating == 2.0f) {
                                "Can be better"
                            } else if (reviewState.rating == 3.0f) {
                                "Good"
                            } else if (reviewState.rating == 4.0f) {
                                "Liked it"
                            } else {
                                "Loved it"
                            },
                            color = if (reviewState.rating == 0.0f) {
                                Color.Red
                            } else if (reviewState.rating == 1.0f) {
                                Color.Red
                            } else if (reviewState.rating == 2.0f) {
                                OrangeYellow1
                            } else if (reviewState.rating == 3.0f) {
                                LightGreen
                            } else if (reviewState.rating == 4.0f) {
                                LightGreen1
                            } else {
                                DeepGreen
                            },
                            fontSize = 16.sp,
                            fontFamily = FontName,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle2,
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))

                    ReviewForm(
                        reviewState = reviewState,
                        //viewState = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        onRatingChange = { inputString ->
                            accountViewModel.onReViewUiEvent(
                                reviewUiEvent = ReviewUiEvent.RatingChanged(
                                    inputString
                                )
                            )
                        },
                        onReviewChange = { inputString ->
                            accountViewModel.onReViewUiEvent(
                                reviewUiEvent = ReviewUiEvent.ReViewChanged(
                                    inputString
                                )
                            )
                        },
                        onSubmit = {
                            accountViewModel.onReViewUiEvent(reviewUiEvent = ReviewUiEvent.Submit)
                        })
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    AccountScreen(
        navController = rememberNavController(),
        onNavigateToCallCreditScreen = {},
        onNavigateToEditProfile = {},
        onNavigateToAddCookScreen = {},
        onNavigateToPostJobScreen = {},
        onNavigateToContactUsScreen = {},
        onNavigateToReviewUsScreen = {}
    )
}
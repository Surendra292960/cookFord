package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.account_screen_component
import android.R.id.message
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExposureZero
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.ButtonIcons
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.SmallTitleText
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.account_screen_component.state.ReviewState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.account_screen_component.state.ReviewUiEvent
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.FontName
import com.example.cook_ford.presentation.theme.LightGray
import com.example.cook_ford.presentation.theme.LightGray_2
import com.example.cook_ford.presentation.theme.LightGreen
import com.example.cook_ford.presentation.theme.LightGreen1
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson


data class AccountModelData( @DrawableRes val leadingIcon: Int,  @DrawableRes val trailingIcon: Int, val isBorder:Boolean = false, val title: String, val subTitle: String)

val accountModelData = mutableListOf(
    AccountModelData(
        leadingIcon = R.drawable.post_job_icon,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Post Job",
        subTitle = "Get notification when job posted"
    ),
    AccountModelData(
        leadingIcon = R.drawable.profile_icon,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Add Your Cook",
        subTitle = ""
    ),
    AccountModelData(
        leadingIcon = R.drawable.community_icon,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Tell Your Community",
        subTitle = ""
    ),
    AccountModelData(
        leadingIcon = R.drawable.contact_us_icon,
        trailingIcon = R.drawable.ic_email,
        isBorder = true,
        title = "Contact Us",
        subTitle = ""
    ),
    AccountModelData(
        leadingIcon = R.drawable.review_us_icon,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Review Us",
        subTitle = "We are always happy to hear from you"
    ),
    AccountModelData(
        leadingIcon = R.drawable.cook_ford_rounded_logo,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = false,
        title = "Sign In as Cook",
        subTitle = "you will be signed out from this account"
    )
)

@Composable
fun AccountsScreen(
   // state: ProfileUiState,
    navController: NavHostController,
    onNavigateToEditProfile: () -> Unit,
    onNavigateToCallCreditScreen: () -> Unit,
    onNavigateToAddCookScreen: () -> Unit,
    onNavigateToPostJobScreen: () -> Unit,
    onNavigateToContactUsScreen: () -> Unit,
    onNavigateToReviewUsScreen: () -> Unit,
    onNavigateToTellCommunity: (String) -> Unit,
    onNavigateToSignInAsCookScreen: () -> Unit,
    onNavigateToTermsOfUseScreen: () -> Unit,
    onNavigateToPrivacyPolicyScreen: () -> Unit,
    onNavigateToLicenseScreen: () -> Unit) {

    val changeProfileState = remember { mutableStateOf("Male") }
    var showReviewBottomSheet by remember { mutableStateOf(false) }
    val accountViewModel : AccountsViewModel = hiltViewModel()
    //val viewState by remember { profileDetailsViewModel.viewState }
    val accountState by remember { accountViewModel.accountState }
    val reviewState by remember { accountViewModel.reviewState }

    accountModelData.forEachIndexed{ index, value->
        if (accountViewModel.getUserType().equals(AppConstants.PROVIDER, ignoreCase = true)){
            if (accountModelData[index].title == "Sign In as Cook"){
                accountModelData.removeAt(index)
            }
        }
    }

    Box(modifier = Modifier.background(Color.White).fillMaxSize()) {
        if (accountState.isSuccessful) {
            Log.d("TAG", "AccountsScreen Data : ${Gson().toJson(accountState)}")
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                val profileLazyListState: LazyListState = rememberLazyListState()

                Box(modifier = Modifier,
                    contentAlignment = Alignment.Center) {
                    ProfileImage(modifier = Modifier, changeProfileState, onChange = {})
                    Image(
                        modifier = Modifier
                            .padding(AppTheme.dimens.paddingNormal)
                            .size(30.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                navController.currentBackStackEntry?.savedStateHandle?.apply {
                                    set(
                                        "profileResponse",
                                        Gson().toJson(accountState.profileResponse)
                                    )
                                }
                                onNavigateToEditProfile.invoke()
                            },
                        painter = painterResource(id = R.drawable.ic_edit_icon),
                        contentDescription = "Edit Profile"
                    )
                }

                ElevatedCard(
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingTooSmall),
                    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)) {

                    LazyColumn(modifier = Modifier.fillMaxWidth(), state = profileLazyListState, horizontalAlignment = Alignment.CenterHorizontally, contentPadding = PaddingValues(start = 10.dp, end = 10.dp),
                        content = {
                            item {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(AppTheme.dimens.paddingSmall), horizontalArrangement = Arrangement.SpaceEvenly) {
                                    OutlinedSmallSubmitButton(
                                        modifier = Modifier
                                            .padding(top = AppTheme.dimens.paddingLarge)
                                            .weight(1f),
                                        text = AppConstants.CALL_CREDIT,
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
                                        text = AppConstants.BUY_CALL_CREDIT,
                                        textColor = Color.White,
                                        isLoading = false,
                                        backgroundColor = OrangeYellow1,
                                        onClick = {
                                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                                set(
                                                    "profileResponse",
                                                    Gson().toJson(accountState.profileResponse)
                                                )
                                            }
                                            onNavigateToCallCreditScreen.invoke()
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                            }
                            items(accountModelData.size) { index ->
                                AccountItem(
                                    accountViewModel = accountViewModel,
                                    accountData = accountModelData,
                                    index = index,
                                    onItemClick = {},
                                    onNavigateToAddCookScreen = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        onNavigateToAddCookScreen.invoke()
                                    },
                                    onNavigateToPostJobScreen = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        onNavigateToPostJobScreen.invoke()
                                    },
                                    onNavigateToContactUsScreen = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        onNavigateToContactUsScreen.invoke()
                                    },
                                    onNavigateToReviewUsScreen = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        showReviewBottomSheet = true
                                        onNavigateToReviewUsScreen.invoke()
                                    },
                                    onNavigateToTellCommunity = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        onNavigateToTellCommunity.invoke(it)
                                    },
                                    onNavigateToSignInAsCookScreen = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        onNavigateToSignInAsCookScreen.invoke()
                                    },
                                    onNavigateToTermsOfUseScreen = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        onNavigateToTermsOfUseScreen.invoke()
                                    },
                                    onNavigateToPrivacyPolicyScreen = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        onNavigateToPrivacyPolicyScreen.invoke()
                                    },
                                    onNavigateToLicenseScreen = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(accountState.profileResponse)
                                            )
                                        }
                                        onNavigateToLicenseScreen.invoke()
                                    },
                                )
                            }
                            item {
                                HorizontalDivider(
                                    modifier = Modifier.padding(top = 5.dp),
                                    color = LightGray_2
                                )
                                FooterStatus()
                                if (showReviewBottomSheet) {
                                    BottomSheet(
                                        AppConstants.REVIEW,
                                        reviewState = reviewState,
                                        accountViewModel = accountViewModel
                                    ) {
                                        showReviewBottomSheet = false
                                        accountViewModel.reviewState.value = ReviewState()
                                        Log.d("TAG", "AccountScreen dismiss : ")
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }else{
            Progressbar(showProgressbar = accountState.isLoading)
        }
    }
}

@Composable
fun AccountItem(
    accountViewModel: AccountsViewModel,
    accountData: MutableList<AccountModelData>,
    index: Int,
    onItemClick: () -> Unit,
    onNavigateToAddCookScreen: () -> Unit,
    onNavigateToPostJobScreen: () -> Unit,
    onNavigateToContactUsScreen: () -> Unit,
    onNavigateToReviewUsScreen: () -> Unit,
    onNavigateToTellCommunity: (String) -> Unit,
    onNavigateToSignInAsCookScreen: () -> Unit,
    onNavigateToTermsOfUseScreen: () -> Unit,
    onNavigateToPrivacyPolicyScreen: () -> Unit,
    onNavigateToLicenseScreen: () -> Unit) {
    val context = LocalContext.current

    Column(modifier = Modifier
        .padding(AppTheme.dimens.paddingSmall)
        .clickable {
            when (accountData[index].title) {
                "Post Job" -> {
                    onNavigateToPostJobScreen()
                }

                "Add Your Cook" -> {
                    onNavigateToAddCookScreen()
                }

                "Tell Your Community" -> {
                    context.shareWithCommunity(
                        to = "cookford@gmail.com",
                        subject = ""
                    )
                    //onNavigateToTellCommunity("Community")
                }

                "Contact Us" -> {
                    context.composeEmail(
                        addresses = arrayOf("cookford@gmail.com"),
                        subject = ""
                    )
                    //onNavigateToContactUsScreen()
                }

                "Review Us" -> {
                    onNavigateToReviewUsScreen()
                }

                "Sign In as Cook" -> {
                    onNavigateToSignInAsCookScreen()
                }

                "Terms of Use" -> {
                    onNavigateToTermsOfUseScreen()
                }

                "Privacy Policy" -> {
                    onNavigateToPrivacyPolicyScreen()
                }

                "License" -> {
                    onNavigateToLicenseScreen()
                }

                else -> {
                    onItemClick()
                }
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
        Box(modifier = Modifier
            .border(
                if (accountData[index].isBorder) (-0).dp else (-1).dp,
                Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .background(LightGray, shape = RoundedCornerShape(16.dp))
            .padding(all = 10.dp)
            .fillMaxWidth()
        ) {
            Row(modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Box(modifier = Modifier
                    .size(40.dp)
                    .background(Color.LightGray, shape = CircleShape),
                    contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = accountData[index].leadingIcon),
                        contentDescription = "Icon",
                        contentScale = ContentScale.FillBounds,
                    )
                }

                Column(modifier = Modifier.padding(start = 15.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center) {

                    MediumTitleText(
                        modifier = Modifier.align(Alignment.Start),
                        text = accountData[index].title,
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W500
                    )

                    SmallTitleText(
                        modifier = Modifier,
                        text = accountData[index].subTitle,
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W400
                    )
                }
            }

            Box(modifier = Modifier
                .size(if (accountData[index].title == "Contact Us") 30.dp else 15.dp)
                .align(Alignment.CenterEnd),
                contentAlignment = Alignment.Center) {
                if (accountData[index].isBorder) {
                    Image(
                        painter = painterResource(id = accountData[index].trailingIcon),
                        contentDescription = "Icon"
                    )
                }
            }
        }
    }
}

@Composable
fun FooterStatus() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp, top = 5.dp),
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    sheetType: String,
    reviewState: ReviewState,
    accountViewModel: AccountsViewModel,
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
                            text = when {
                                reviewState.rating != 0.0f -> {
                                    when (reviewState.rating) {
                                        1.0f -> {
                                            "Not so good"
                                        }
                                        2.0f -> {
                                            "Can be better"
                                        }
                                        3.0f -> {
                                            "Good"
                                        }
                                        4.0f -> {
                                            "Liked it"
                                        }
                                        else -> {
                                            "Loved it"
                                        }
                                    }
                                }
                                else -> {
                                    "Not Rated"
                                }
                            },
                            color = when (reviewState.rating) {
                                0.0f -> {
                                    Color.Red
                                }
                                1.0f -> {
                                    Color.Red
                                }
                                2.0f -> {
                                    OrangeYellow1
                                }
                                3.0f -> {
                                    LightGreen
                                }
                                4.0f -> {
                                    LightGreen1
                                }
                                else -> {
                                    DeepGreen
                                }
                            },
                            fontSize = 16.sp,
                            fontFamily = FontName,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle2,
                        )
                    }

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

fun Context.composeEmail(addresses: Array<String>, subject: String) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.setData(Uri.parse("mailto:")) // only email apps should handle this
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(addresses[0]))
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, "")
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Context.shareWithCommunity(to: String, subject: String) {
    try {
        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        email.putExtra(Intent.EXTRA_SUBJECT, subject)
        email.putExtra(Intent.EXTRA_TEXT, message)
        //need this to prompts email client only
        email.setType("message/rfc822")
        this.startActivity(Intent.createChooser(email, "Choose an Email client :"))
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}

fun Context.dial(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (t: Throwable) {
        // TODO: Handle potential exceptions
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    AccountsScreen(
        //  state = state,
       onNavigateToEditProfile = {},
        onNavigateToCallCreditScreen = {},
        onNavigateToAddCookScreen = {},
        onNavigateToPostJobScreen = {},
        onNavigateToContactUsScreen = {},
        onNavigateToReviewUsScreen = {},
        onNavigateToTellCommunity = {},
        onNavigateToSignInAsCookScreen = {},
        onNavigateToTermsOfUseScreen = {},
        onNavigateToPrivacyPolicyScreen = {},
        onNavigateToLicenseScreen = {},
        navController = rememberNavController()
    )
}

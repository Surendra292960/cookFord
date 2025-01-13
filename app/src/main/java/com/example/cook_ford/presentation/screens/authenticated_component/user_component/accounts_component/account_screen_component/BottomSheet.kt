package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.account_screen_component
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.account_screen_component.state.ReviewState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.account_screen_component.state.ReviewUiEvent
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.FontName
import com.example.cook_ford.presentation.theme.LightGreen
import com.example.cook_ford.presentation.theme.LightGreen1
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.google.gson.Gson

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
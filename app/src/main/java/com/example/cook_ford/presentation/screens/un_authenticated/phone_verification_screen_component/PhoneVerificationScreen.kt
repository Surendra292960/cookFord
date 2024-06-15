package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultBackArrow
import com.example.cook_ford.presentation.component.widgets.OTPTextFields
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationUiEvent
import com.example.cook_ford.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    PhoneVerificationScreen(navController = rememberNavController(),
        onNavigateBack = {}, onNavigateToAuthenticatedRoute = {})
}

@Composable
fun PhoneVerificationScreen(navController: NavController, onNavigateBack:()->Unit, onNavigateToAuthenticatedRoute:()-> Unit) {
    val phoneVerificationViewModel:PhoneVerificationViewModel = hiltViewModel()
    val phoneVerificationState by remember { phoneVerificationViewModel.phoneVerificationState }
    val snackBarHostState = remember { SnackbarHostState() }
    val viewState: MainViewState by phoneVerificationViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val isSuccess = true
    //count down
    val timer = object : CountDownTimer(12000, 1000) {
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {

        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 25.dp, end = 25.dp)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(0.5f)) {
                DefaultBackArrow(onClick = {
                    onNavigateBack.invoke()
                })
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(
                    text = "",
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700)
                )
            }
        }

        if (!phoneVerificationState.isOTPSent) {

            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(350.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.phone_verification),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Add Your Phone Number",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Enter your phone number in order to send you your OTP security code.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    PhoneVerificationForm(
                        phoneVerificationState = phoneVerificationState,
                        viewState = viewState,
                        onPhoneChange = { inputString ->
                            Log.d("TAG", "PhoneVerificationScreen: $inputString")
                            phoneVerificationViewModel.onUiEvent(
                                phoneVerificationUiEvent = PhoneVerificationUiEvent.PhoneChanged(
                                    inputString
                                )
                            )
                        },

                        onSubmit = {
                            phoneVerificationViewModel.onUiEvent(phoneVerificationUiEvent = PhoneVerificationUiEvent.Submit)
                        },
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

        } else {

            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {

                Image(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(350.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.otp_verification),
                    contentDescription = null,
                )

                Text(
                    text = "Enter the Verification Code",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Enter the 4 digit code that we have sent to (+91 8801737-***)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement
                        .spacedBy(
                            space = 10.dp,
                            alignment = Alignment.CenterHorizontally
                        )
                ) {

                    OTPTextFields(length = 6, onFilled = {})
                }

                Spacer(modifier = Modifier.height(10.dp))

                SubmitButton(
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                    text = stringResource(id = R.string.verify_button_text),
                    isLoading = false,
                    onClick = {onNavigateToAuthenticatedRoute.invoke() }
                )

                Spacer(modifier = Modifier.height(20.dp))


                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("Didn`t receive the code? ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Green,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("Resend Code")
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

            }

        }
    }
}




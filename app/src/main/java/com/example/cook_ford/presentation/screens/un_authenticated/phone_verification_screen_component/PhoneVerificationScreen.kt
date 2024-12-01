package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.rememberImeState
import com.example.cook_ford.presentation.component.widgets.DefaultBackArrow
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.main_screen_component.MainActivity
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.OTPVerificationUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.ResultState
import com.example.cook_ford.presentation.theme.FontName
import com.google.gson.Gson


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    PhoneVerificationScreen(navController = rememberNavController(),
        onNavigateBack = {}, onNavigateToAuthenticatedRoute = {})
}


@Composable
fun PhoneVerificationScreen(
    navController: NavController,
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {

    val phoneVerificationViewModel:PhoneVerificationViewModel = hiltViewModel()
    var phoneVerificationState by remember { phoneVerificationViewModel.phoneVerificationState }
    val snackBarHostState = remember { SnackbarHostState() }
    val viewState: MainViewState by phoneVerificationViewModel.viewState.collectAsState()
    val context = LocalContext.current as MainActivity

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    Log.d("TAG", "PhoneVerificationScreen: Data ${Gson().toJson(phoneVerificationState)}")
    if (phoneVerificationState.nevigateToSignIn){
        LaunchedEffect(key1 = true) {
            Log.d("TAG", "PhoneVerificationScreen: nevigateToSignIn ${phoneVerificationState.nevigateToSignIn}")
            onNavigateToAuthenticatedRoute.invoke()
            phoneVerificationState = PhoneVerificationState()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.Start) {

        Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
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

        if (!phoneVerificationState.nevigateToOTPScreen) {

            Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState), horizontalAlignment = Alignment.Start) {

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Verify Phone",
                    fontFamily = FontName,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Image(modifier = Modifier
                        .wrapContentWidth()
                        .height(350.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.phone_verification),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Add Your Phone Number",
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.W900,
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Enter your phone number in order to send you your OTP security code.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
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

           LaunchedEffect(key1 = true){
               phoneVerificationViewModel.createUserWithPhone(
                   phoneVerificationState.phone,
                   context
               ).collect{
                   when(it){
                       is ResultState.Success->{
                           Log.d("TAG", "PhoneVerificationScreen: Success ${it.data}")
                       }
                       is ResultState.Failure->{
                           Log.d("TAG", "PhoneVerificationScreen: Failure ${it.msg}")
                       }
                       is ResultState.Loading->{
                           Log.d("TAG", "PhoneVerificationScreen: Loading $it")
                       }
                   }
               }
           }

            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
                horizontalAlignment = Alignment.Start) {

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Verify OTP",
                    fontFamily = FontName,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

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
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.W900,
                    color = Color.DarkGray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Enter the 4 digit code that we have sent to ${phoneVerificationState.phone}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OTPVerificationForm(
                    phoneVerificationState = phoneVerificationState,
                    viewState = viewState,
                    onOTPChange = { inputString ->
                        Log.d("TAG", "OTPVerificationForm: $inputString")
                        phoneVerificationViewModel.onOTPUiEvent(
                            otpVerificationUiEvent = OTPVerificationUiEvent.OTPChanged(
                                inputString
                            )
                        )
                    },

                    onSubmit = {
                        //onNavigateToAuthenticatedRoute.invoke()
                        phoneVerificationViewModel.onOTPUiEvent(otpVerificationUiEvent = OTPVerificationUiEvent.Submit)
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}



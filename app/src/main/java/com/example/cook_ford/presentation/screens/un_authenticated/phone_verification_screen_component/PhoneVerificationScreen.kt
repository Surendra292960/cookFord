package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultBackArrow
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.main_screen_component.MainActivity
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.OTPVerificationUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.ResultState
import com.google.android.gms.auth.api.phone.SmsRetriever


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    PhoneVerificationScreen(navController = rememberNavController(),
        onNavigateBack = {}, onNavigateToAuthenticatedRoute = {})
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun PhoneVerificationScreen(
    navController: NavController,
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {

    val phoneVerificationViewModel:PhoneVerificationViewModel = hiltViewModel()
    val phoneVerificationState by remember { phoneVerificationViewModel.phoneVerificationState }
    val snackBarHostState = remember { SnackbarHostState() }
    val viewState: MainViewState by phoneVerificationViewModel.viewState.collectAsState()
    val context = LocalContext.current as MainActivity




    /**
     * Right now we don't have support for Autofill in Compose.
     * See [com.appmason.jetplayground.ui.components.Autofill] for some temporary solutions.
     *
     * If we have support in the future and want user to autofill OTP from keyboard manually,
     * then we do not need to fetch OTP automatically using Google SMS Retriever API and in
     * that case, we can totally remove this [OtpReceiverEffect] and let Autofill handle it.
     * But Google SMS Retriever API is a great way anyways to fetch and populate OTP!
     */
   /* OtpReceiverEffect(
        context = context,
        onOtpReceived = { otp ->
            otpValue = otp
            if (otpValue.length == 6) {
                keyboardController?.hide()
                isOtpFilled = true
            }
        }
    )*/

 /*   LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }*/

    LaunchedEffect(key1 = true) {
        if (phoneVerificationState.nevigateToSignIn){
            //Log.d("TAG", "PhoneVerificationScreen: nevigateToSignIn ${phoneVerificationState.nevigateToSignIn}")
            onNavigateToAuthenticatedRoute.invoke()
        }
    }


    if (phoneVerificationState.phone.isNotEmpty()){
        val code = SmsRetriever.getClient(context).startSmsUserConsent(phoneVerificationState.phone)
        Log.d("TAG", "PhoneVerificationScreen Code : $code")
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

        if (phoneVerificationState.nevigateToOTPScreen) {

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

         /*  LaunchedEffect(key1 = true){
               phoneVerificationViewModel.createUserWithPhone(
                   phoneVerificationState.phone,
                   context
               ).collect{
                   when(it){
                       is ResultState.Success->{
                           Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
                           Log.d("TAG", "PhoneVerificationScreen: Success ${it}")
                       }
                       is ResultState.Failure->{
                           Log.d("TAG", "PhoneVerificationScreen: Failure ${it.msg}")
                       }
                       is ResultState.Loading->{
                           Log.d("TAG", "PhoneVerificationScreen: Loading $it")
                       }
                   }
               }
           }*/

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


               /* Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {


                }*/
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
                        onNavigateToAuthenticatedRoute.invoke()
                        // phoneVerificationViewModel.onOTPUiEvent(otpVerificationUiEvent = OTPVerificationUiEvent.Submit)
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

/*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun OtpReceiverEffect(
    context: Context,
    onOtpReceived: (String) -> Unit
) {
    val otpReceiver = remember { OTPReceiver() }

    *//**
     * This function should not be used to listen for Lifecycle.Event.ON_DESTROY because Compose
     * stops recomposing after receiving a Lifecycle.Event.ON_STOP and will never be aware of an
     * ON_DESTROY to launch onEvent.
     *
     * This function should also not be used to launch tasks in response to callback events by way
     * of storing callback data as a Lifecycle.State in a MutableState. Instead, see currentStateAsState
     * to obtain a State that may be used to launch jobs in response to state changes.
     *//*
    LifecycleResumeEffect(key1 = Unit) {
        // add ON_RESUME effect here
        Log.e("OTPReceiverEffect", "SMS retrieval has been started.")
        startSMSRetrieverClient(context)
        otpReceiver.init(object : OTPReceiver.OTPReceiveListener {
            override fun onOTPReceived(otp: String?) {
                Log.e("OTPReceiverEffect ", "OTP Received: $otp")
                otp?.let { onOtpReceived(it) }
                try {
                    Log.e("OTPReceiverEffect ", "Unregistering receiver")
                    context.unregisterReceiver(otpReceiver)
                } catch (e: IllegalArgumentException) {
                    Log.e("OTPReceiverEffect ", "Error in registering receiver: ${e.message}}")
                }
            }

            override fun onOTPTimeOut() {
                Log.e("OTPReceiverEffect ", "Timeout")
            }
        })
        try {
            Log.e("OTPReceiverEffect ", "Lifecycle.Event.ON_RESUME")
            Log.e("OTPReceiverEffect ", "Registering receiver")
            context.registerReceiver(otpReceiver, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION), Context.RECEIVER_EXPORTED)
        } catch (e: IllegalArgumentException) {
            Log.e("OTPReceiverEffect ", "Error in registering receiver: ${e.message}}")
        }
        onPauseOrDispose {
            // add clean up for work kicked off in the ON_RESUME effect here
            try {
                Log.e("OTPReceiverEffect ", "Lifecycle.Event.ON_PAUSE")
                Log.e("OTPReceiverEffect ", "Unregistering receiver")
                context.unregisterReceiver(otpReceiver)
            } catch (e: IllegalArgumentException) {
                Log.e("OTPReceiverEffect ", "Error in unregistering receiver: ${e.message}}")
            }
        }
    }
}*/



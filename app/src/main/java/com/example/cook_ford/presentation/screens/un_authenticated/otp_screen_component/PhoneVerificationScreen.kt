package com.example.cook_ford.presentation.screens.un_authenticated.otp_screen_component
import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultBackArrow
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OTPTexField
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.theme.AppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    PhoneVerificationScreen(navController = rememberNavController())
}

@Composable
fun PhoneVerificationScreen(navController: NavController) {
    //otp mutable list
    var otp1 by remember { mutableStateOf(TextFieldValue("")) }
    var otp2 by remember { mutableStateOf(TextFieldValue("")) }
    var otp3 by remember { mutableStateOf(TextFieldValue("")) }
    var otp4 by remember { mutableStateOf(TextFieldValue("")) }
    var otp5 by remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester1 = FocusRequester()
    val focusRequester2 = FocusRequester()
    val focusRequester3 = FocusRequester()
    val focusRequester4 = FocusRequester()
    val focusRequester5 = FocusRequester()
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
            .padding(start = 30.dp, end = 30.dp)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.Start) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(0.5f)) {
                DefaultBackArrow(onClick = {})
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

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            modifier = Modifier.fillMaxWidth().height(350.dp),
            painter = painterResource(id = R.drawable.chef_cooking),
            contentDescription = null,
        )

        if (isSuccess) {
            Column(
                modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {


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
                    fontSize = 15.sp,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InputTextField(
                        value = "",
                        onChange = {},
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        keyboardOptions = KeyboardOption(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number,
                            label = "Phone Number",
                            placeholder = "Enter your phone number"
                        ),
                        DefaultIcons(leadingIcon = Icons.Default.Phone),
                        isError = false,
                        errorText = "",
                        maxChar = 5,
                        texColor = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                SubmitButton(
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                    text = stringResource(id = R.string.verify_button_text),
                    isLoading = false,
                    onClick = { /*onSubmit*/ }
                )

                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("I accept the ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("Terms and Conditions")
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                )
            }
        } else {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {

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
                    fontSize = 15.sp,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OTPTexField(focusRequester = focusRequester1) { newText ->
                        otp1 = newText
                        if (otp1.text.isNotEmpty()) {
                            focusRequester2.requestFocus()
                        }
                    }
                    OTPTexField(focusRequester = focusRequester2) { newText ->
                        otp2 = newText
                        if (otp2.text.length == 1) {
                            focusRequester3.requestFocus()
                        } else {
                            focusRequester1.requestFocus()
                        }
                    }
                    OTPTexField(focusRequester = focusRequester3) { newText ->
                        otp3 = newText
                        if (otp3.text.length == 1) {
                            focusRequester4.requestFocus()
                        } else {
                            focusRequester2.requestFocus()
                        }
                    }
                    OTPTexField(focusRequester = focusRequester4) { newText ->
                        otp4 = newText
                        if (otp4.text.length == 1) {
                            focusRequester5.requestFocus()
                        } else {
                            focusRequester3.requestFocus()
                        }
                    }
                    OTPTexField(focusRequester = focusRequester5) { newText ->
                        otp5 = newText
                        if (otp5.text.isEmpty()) {
                            focusRequester4.requestFocus()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                SubmitButton(
                    modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                    text = stringResource(id = R.string.verify_button_text),
                    isLoading = false,
                    onClick = {/*onSubmit*/ }
                )

                Spacer(modifier = Modifier.height(10.dp))


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
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }
}




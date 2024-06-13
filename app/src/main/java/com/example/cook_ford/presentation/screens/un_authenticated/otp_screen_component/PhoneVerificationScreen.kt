package com.example.cook_ford.presentation.screens.un_authenticated.otp_screen_component
import android.os.CountDownTimer
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
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

    //count down
    val timer = object : CountDownTimer(12000, 1000) {
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {

        }
    }


    Column(modifier = Modifier.fillMaxSize()
        .padding(start = 30.dp, end = 30.dp)
        .verticalScroll(rememberScrollState()),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(0.5f)) {
                DefaultBackArrow( onClick = {})
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

        Text(text = "Add your Phone number",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.Start))


        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Enter your phone number in order to send you your OTP security code.",
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.Start))

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
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
                /*submit = { TODO() }*/
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        SubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.verify_button_text),
            isLoading = false,
            onClick = {/*onSubmit*/ }
        )

        Spacer(modifier = Modifier.height(10.dp))
        
        Text(text = "I accept the Terms and Conditions",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally))

    }




    /*Column(modifier = Modifier.fillMaxSize().padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(0.5f)) {
                DefaultBackArrow( onClick = {})
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(
                    text = "OTP Verification",
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700)
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "OTP Verification", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            text = buildAnnotatedString {
                append("We sent your code to +8801737-***\nThis code is expired in ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                    )
                ) {
                    append("120s")
                }
            },
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
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

        Spacer(modifier = Modifier.fillMaxHeight(0.3f))
        SubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.verify_button_text),
            isLoading = false,
            onClick = {*//*onSubmit*//* }
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.3f))
        Text(
            text = "Resend OTP Code",
            style = TextStyle(textDecoration = TextDecoration.Underline),
            color = Color.DarkGray,
            fontWeight = FontWeight(500),
            modifier = Modifier.clickable {

            })
    }*/
}


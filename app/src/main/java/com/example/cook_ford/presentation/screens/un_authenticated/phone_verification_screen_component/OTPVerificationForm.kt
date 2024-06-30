package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.OtpInputField
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationState
import com.example.cook_ford.presentation.theme.AppTheme

@Composable
fun OTPVerificationForm(
    phoneVerificationState: PhoneVerificationState,
    viewState: MainViewState,
    onOTPChange: (String) -> Unit,
    onSubmit: () -> Unit){

    var otpValue by remember { mutableStateOf(phoneVerificationState.otp) }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(10.dp))

        OtpInputField(
            modifier = Modifier
                .padding(top = 0.dp)
                .focusRequester(focusRequester),
            otpText = otpValue,
            shouldCursorBlink = false,
            onOtpModified = { value, otpFilled ->
                otpValue = value
                isOtpFilled = otpFilled
                if (otpFilled) {
                    onOTPChange.invoke(value)
                    keyboardController?.hide()
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Verify OTP Submit Button
        OutlinedSmallSubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.verify_button_text),
            textColor = Color.Gray,
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )

        Spacer(modifier = Modifier.height(20.dp))
        // Terms and Conditions
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
    }
}
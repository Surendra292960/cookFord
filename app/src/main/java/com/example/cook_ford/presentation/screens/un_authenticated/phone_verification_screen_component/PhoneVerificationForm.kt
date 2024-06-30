package com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OutlinedInputTextField
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.PhoneVerificationState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.AppConstants

@Composable
fun PhoneVerificationForm(
    phoneVerificationState: PhoneVerificationState,
    viewState: MainViewState,
    onPhoneChange: (String) -> Unit,
    onSubmit: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = phoneVerificationState.phone,
            onChange = onPhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Phone,
                label = AppConstants.PHONE,
                placeholder = AppConstants.PHONE_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = phoneVerificationState.errorState.phoneErrorState.hasError,
            errorText = stringResource(id = phoneVerificationState.errorState.phoneErrorState.errorMessageStringResource),
            maxChar = 12,
            texColor = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Verify Phone Submit Button
        OutlinedSmallSubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.submit_button_text),
            textColor = Color.Gray,
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )

        Spacer(modifier = Modifier.height(20.dp))
        // Terms and Conditions
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
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        )
    }
}

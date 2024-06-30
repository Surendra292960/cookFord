package com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.OutlinedInputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.component.widgets.TrailingIcon
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.SignInState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.AppConstants

@Composable
fun SignInForm(
    signInState: SignInState,
    viewState: MainViewState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedInputTextField(
            value = signInState.email,
            onChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                label = AppConstants.EMAIL,
                placeholder = AppConstants.EMAIL_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Email),
            isError = signInState.errorState.emailErrorState.hasError,
            errorText = stringResource(id = signInState.errorState.emailErrorState.errorMessageStringResource),
            maxChar = 30
            /*submit = { TODO() }*/
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedInputTextField(
            value = signInState.password,
            onChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                label = AppConstants.PASSWORD,
                placeholder = AppConstants.PASSWORD_PLACEHOLDER
            ),
            DefaultIcons(
                leadingIcon = Icons.Default.Lock,
                trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)
            ),
            isError = signInState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = signInState.errorState.passwordErrorState.errorMessageStringResource),
            maxChar = 25
            /*submit = { TODO() },*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Forgot Password
        Text(
            modifier = Modifier
                .padding(top = AppTheme.dimens.paddingSmall)
                .align(alignment = Alignment.End)
                .clickable {
                    onForgotPasswordClick.invoke()
                },
            text = stringResource(id = R.string.forgot_password),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium
        )

        // SignIn Submit Button
        OutlinedSubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.sign_in_button_text),
            textColor = Color.Gray,
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )
    }
}
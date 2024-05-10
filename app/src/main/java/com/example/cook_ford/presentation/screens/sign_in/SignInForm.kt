package com.example.cook_ford.presentation.screens.sign_in
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.common.widgets.DefaultIcons
import com.example.cook_ford.presentation.common.widgets.InputTextField
import com.example.cook_ford.presentation.common.widgets.KeyboardOption
import com.example.cook_ford.presentation.common.widgets.SubmitButton
import com.example.cook_ford.presentation.common.widgets.TrailingIcon
import com.example.cook_ford.presentation.common.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.sign_in.state.SignInState
import com.example.cook_ford.presentation.theme.AppTheme

@Composable
fun SignInForm(
    signInState: SignInState,
    viewState: MainViewState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onForgotPasswordClick: () -> Unit){

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(10.dp))

            InputTextField(
                value = signInState.email,
                onChange = onEmailChange,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email,label = "Email", placeholder = "Enter Email"),
                DefaultIcons(leadingIcon = Icons.Default.Email),
                isError = signInState.errorState.emailErrorState.hasError,
                errorText = stringResource(id = signInState.errorState.emailErrorState.errorMessageStringResource),
                maxChar = 30
                /*submit = { TODO() }*/)
            Spacer(modifier = Modifier.height(10.dp))
            InputTextField(
                value = signInState.password,
                onChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOption(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password,label = "Password", placeholder = "Enter Password"),
                DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
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

            Spacer(modifier = Modifier.height(10.dp))

            // SignIn Submit Button
            SubmitButton(
                modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                text = stringResource(id = R.string.sign_in_button_text),
                isLoading = viewState.isLoading,
                onClick = onSubmit
            )
        }
}
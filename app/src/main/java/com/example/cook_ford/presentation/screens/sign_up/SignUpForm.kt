package com.example.cook_ford.presentation.screens.sign_up
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.screens.sign_up.state.SignUpState
import com.example.cook_ford.presentation.common.widgets.DefaultIcons
import com.example.cook_ford.presentation.common.widgets.InputTextField
import com.example.cook_ford.presentation.common.widgets.KeyboardOption
import com.example.cook_ford.presentation.common.widgets.SubmitButton
import com.example.cook_ford.presentation.common.widgets.TrailingIcon

@Composable
fun SignUpForm(
    registrationState: SignUpState,
    onNameChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit){

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = registrationState.name,
            onChange = onNameChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = "Next", keyboardType = "Text",label = "Name", placeholder = "Enter Full Name"),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = registrationState.errorState.nameErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.nameErrorState.errorMessageStringResource),
            maxChar = 30
            /*submit = { TODO() }*/)

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = registrationState.username,
            onChange = onUserNameChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = "Next", keyboardType = "Email",label = "Email", placeholder = "Enter Email"),
            DefaultIcons(leadingIcon = Icons.Default.Email),
            isError = registrationState.errorState.userNameErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.userNameErrorState.errorMessageStringResource),
            maxChar = 30
            /*submit = { TODO() }*/)

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = registrationState.phone,
            onChange = onPhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = "Next", keyboardType = "Phone",label = "Phone Number", placeholder = "Enter Phone Number"),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = registrationState.errorState.phoneErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.phoneErrorState.errorMessageStringResource),
            maxChar = 12
            /*submit = { TODO() }*/)

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = registrationState.password,
            onChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = "Next", keyboardType = "Password",label = "Password", placeholder = "Enter Password"),
            DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
            isError = registrationState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.passwordErrorState.errorMessageStringResource),
            maxChar = 25
            /*submit = { TODO() },*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = registrationState.confirmPassword,
            onChange = onConfirmPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = "Done", keyboardType = "Password",label = "Confirm Password", placeholder = "Enter Confirm Password"),
            DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
            isError = registrationState.errorState.confirmPasswordErrorState.hasError,
            errorText = stringResource(id = registrationState.errorState.confirmPasswordErrorState.errorMessageStringResource),
            maxChar = 25
            /*submit = { TODO() },*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Registration Submit Button
        SubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingExtraLarge),
            text = stringResource(id = R.string.sign_up_button_text),
            onClick = onSubmit
        )

        /*    Button(onClick = {

                loginViewModel.onUiEvent(loginUiEvent = LoginUiEvent.Submit)
                *//*if (!viewModel.checkSignInCredentials(user, context)) user = User()
                    Log.d("TAG", "SignInForm: ${user.gender}")*//*},
                    enabled = user.isNotEmpty(),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth()) {
                    Text("SignIn")
                }*/
    }
}
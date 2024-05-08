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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    signUpState: SignUpState,
    onUserNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit){

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))

     /*   InputTextField(
            value = signUpState.name,
            onChange = onNameChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = "Name", placeholder = "Enter Full Name"),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = signUpState.errorState.nameErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.nameErrorState.errorMessageStringResource),
            maxChar = 30
            *//*submit = { TODO() }*//*)

        Spacer(modifier = Modifier.height(10.dp))*/

        InputTextField(
            value = signUpState.username,
            onChange = onUserNameChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email,label = "Email", placeholder = "Enter Email"),
            DefaultIcons(leadingIcon = Icons.Default.Email),
            isError = signUpState.errorState.userNameErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.userNameErrorState.errorMessageStringResource),
            maxChar = 30
            /*submit = { TODO() }*/)

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = signUpState.phone,
            onChange = onPhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Phone,label = "Phone Number", placeholder = "Enter Phone Number"),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = signUpState.errorState.phoneErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.phoneErrorState.errorMessageStringResource),
            maxChar = 12
            /*submit = { TODO() }*/)

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = signUpState.password,
            onChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password,label = "Password", placeholder = "Enter Password"),
            DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
            isError = signUpState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.passwordErrorState.errorMessageStringResource),
            maxChar = 25
            /*submit = { TODO() },*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = signUpState.confirmPassword,
            onChange = onConfirmPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password,label = "Confirm Password", placeholder = "Enter Confirm Password"),
            DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
            isError = signUpState.errorState.confirmPasswordErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.confirmPasswordErrorState.errorMessageStringResource),
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
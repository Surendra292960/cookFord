package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.cook_sign_up
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.cook_sign_up.state.CookSignUpState
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.presentation.component.widgets.TrailingIcon
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.theme.FontName
import com.example.cook_ford.utils.AppConstants

val genders = listOf("Male", "Female")

@Composable
fun CookSignUpForm(
    signUpState: CookSignUpState,
    viewState: MainViewState,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onSubmit: () -> Unit){

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = signUpState.username,
            onChange = onUserNameChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.NAME, placeholder = AppConstants.NAME_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = signUpState.errorState.usernameErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.usernameErrorState.errorMessageStringResource),
            maxChar = 30,
            textColor = Color.Gray
            /*submit = { TODO() }*/)

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = signUpState.email,
            onChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email,label = AppConstants.EMAIL, placeholder = AppConstants.EMAIL_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Email),
            isError = signUpState.errorState.emailErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.emailErrorState.errorMessageStringResource),
            maxChar = 30,
            textColor = Color.Gray
            /*submit = { TODO() }*/)

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = signUpState.password,
            onChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password,label = AppConstants.PASSWORD, placeholder = AppConstants.PASSWORD_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
            isError = signUpState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.passwordErrorState.errorMessageStringResource),
            maxChar = 25,
            textColor = Color.Gray
            /*submit = { TODO() },*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = signUpState.confirmPassword,
            onChange = onConfirmPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password,label = AppConstants.CONFIRM_PASSWORD, placeholder = AppConstants.CONFIRM_PASSWORD_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
            isError = signUpState.errorState.confirmPasswordErrorState.hasError,
            errorText = stringResource(id = signUpState.errorState.confirmPasswordErrorState.errorMessageStringResource),
            maxChar = 25,
            textColor = Color.Gray
            /*submit = { TODO() },*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Gender", fontFamily = FontName, color = Color.Gray, fontWeight = FontWeight.W700, fontSize = 16.sp)
            SegmentedControl(
                items = genders,
                defaultSelectedItemIndex = 0,) {
                onGenderChange.invoke(genders[it])
                Log.e("CustomToggle", "Selected item : ${genders[it]}")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Registration Submit Button
        OutlinedSubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.sign_up_button_text),
            textColor = Color.Gray,
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )
    }
}
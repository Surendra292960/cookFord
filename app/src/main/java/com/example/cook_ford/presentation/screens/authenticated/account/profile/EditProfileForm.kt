package com.example.cook_ford.presentation.screens.authenticated.account.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OutlinedInputTextField
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.account.profile.state.EditProfileState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.AppConstants

@Composable
fun EditProfileForm(
    editProfileState: EditProfileState,
    viewState: MainViewState,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onSignOutClick: () -> Unit){

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {

        //Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editProfileState.username,
            onChange = onUserNameChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                label = AppConstants.NAME,
                placeholder = AppConstants.NAME_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = editProfileState.errorState.usernameErrorState.hasError,
            errorText = stringResource(id = editProfileState.errorState.usernameErrorState.errorMessageStringResource),
            maxChar = 30
            /*submit = { TODO() }*/
        )
        //Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editProfileState.email,
            onChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                label = AppConstants.EMAIL,
                placeholder = AppConstants.EMAIL_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Email),
            isError = editProfileState.errorState.emailErrorState.hasError,
            errorText = stringResource(id = editProfileState.errorState.emailErrorState.errorMessageStringResource),
            maxChar = 30
            /*submit = { TODO() }*/
        )
        //Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editProfileState.phone,
            onChange = onPhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone,
                label = AppConstants.PHONE,
                placeholder = AppConstants.PHONE_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = editProfileState.errorState.phoneErrorState.hasError,
            errorText = stringResource(id = editProfileState.errorState.phoneErrorState.errorMessageStringResource),
            maxChar = 12
            /*submit = { TODO() }*/
        )
        Spacer(modifier = Modifier.height(10.dp))

        // EditProfile Submit Button
        SubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.submit_button_text),
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )

        Spacer(modifier = Modifier.height(10.dp))

        //EditProfile SignOut Button
        /*SubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.sign_out_button_text),
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )*/
    }
}
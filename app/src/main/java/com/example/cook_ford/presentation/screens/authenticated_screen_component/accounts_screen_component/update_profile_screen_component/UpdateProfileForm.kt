package com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.update_profile_screen_component
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.update_profile_screen_component.state.EditProfileState
import com.example.cook_ford.utils.AppConstants
import com.example.cook_ford.presentation.theme.FontName

@Composable
fun EditProfileForm(
    changeProfileState:MutableState<String>,
    editProfileState: EditProfileState,
    viewState: MainViewState,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onProfileImageChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onSignOutClick: () -> Unit){

    val genders = listOf("Male", "Female")

    Column(modifier = Modifier.fillMaxWidth(),
        //verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {

        // Profile Image
        ProfileImage(
            changeProfileState,
            onChange = onProfileImageChange
        )

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
            maxChar = 30,
            textColor = Color.Gray
        )
        Spacer(modifier = Modifier.height(10.dp))

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
            maxChar = 30,
            textColor = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))


        InputTextField(
            value = editProfileState.phone,
            onChange = onPhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Phone,
                label = AppConstants.PHONE,
                placeholder = AppConstants.PHONE_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = editProfileState.errorState.phoneErrorState.hasError,
            errorText = stringResource(id = editProfileState.errorState.phoneErrorState.errorMessageStringResource),
            maxChar = 12,
            textColor = Color.Gray
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

        // EditProfile Submit Button
        OutlinedSubmitButton(
            modifier = Modifier.padding(all = 10.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.submit_button_text),
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )
    }
}


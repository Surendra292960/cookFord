package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pin
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.CookPersonalInfoState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.AppConstants

@Composable
fun CookPersonalInfoForm(
    cookPersonalInfoState: CookPersonalInfoState,
    viewState: MainViewState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onStateChange: (String) -> Unit,
    onZipCodeChange: (String) -> Unit,
    onReligionChange: (String) -> Unit,
    onSubmit: () -> Unit){

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))

       Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
           InputTextField(
               value = cookPersonalInfoState.firstName,
               onChange = onFirstNameChange,
               modifier = Modifier.weight(1f).wrapContentSize(),
               keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.FIRST_NAME, placeholder = ""),
               DefaultIcons(leadingIcon = Icons.Default.Person),
               isError = cookPersonalInfoState.errorState.firstNameErrorState.hasError,
               errorText = stringResource(id = cookPersonalInfoState.errorState.firstNameErrorState.errorMessageStringResource),
               maxChar = 30,
               textColor = Color.Gray
           )

           Spacer(modifier = Modifier.width(20.dp))

           InputTextField(
               value = cookPersonalInfoState.lastName,
               onChange = onLastNameChange,
               modifier = Modifier.weight(1f).wrapContentSize(),
               keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.LAST_NAME, placeholder = ""),
               DefaultIcons(leadingIcon = Icons.Default.Person),
               isError = cookPersonalInfoState.errorState.lastNameErrorState.hasError,
               errorText = stringResource(id = cookPersonalInfoState.errorState.lastNameErrorState.errorMessageStringResource),
               maxChar = 30,
               textColor = Color.Gray
           )
       }

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = cookPersonalInfoState.religion,
            onChange = onReligionChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.RELIGION, placeholder = AppConstants.RELIGION_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = cookPersonalInfoState.errorState.religionErrorState.hasError,
            errorText = stringResource(id = cookPersonalInfoState.errorState.religionErrorState.errorMessageStringResource),
            maxChar = 30,
            textColor = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = cookPersonalInfoState.address,
            onChange = onAddressChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.ADDRESS, placeholder = AppConstants.ADDRESS_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.LocationOn),
            isError = cookPersonalInfoState.errorState.addressErrorState.hasError,
            errorText = stringResource(id = cookPersonalInfoState.errorState.addressErrorState.errorMessageStringResource),
            maxChar = 30,
            textColor = Color.Gray
            )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = cookPersonalInfoState.city,
            onChange = onCityChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.CITY, placeholder = AppConstants.CITY_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.LocationCity),
            isError = cookPersonalInfoState.errorState.cityErrorState.hasError,
            errorText = stringResource(id = cookPersonalInfoState.errorState.cityErrorState.errorMessageStringResource),
            maxChar = 30,
            textColor = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            InputTextField(
                value = cookPersonalInfoState.state,
                onChange = onStateChange,
                modifier = Modifier.weight(1f).wrapContentSize(),
                keyboardOptions = KeyboardOption(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                    label = AppConstants.STATE,
                    placeholder = AppConstants.STATE_PLACEHOLDER
                ),
                DefaultIcons(leadingIcon = Icons.Default.AddLocation),
                isError = cookPersonalInfoState.errorState.stateErrorState.hasError,
                errorText = stringResource(id = cookPersonalInfoState.errorState.stateErrorState.errorMessageStringResource),
                maxChar = 25,
                textColor = Color.Gray
            )

            Spacer(modifier = Modifier.width(20.dp))

            InputTextField(
                value = cookPersonalInfoState.zipCode,
                onChange = onZipCodeChange,
                modifier = Modifier.weight(1f).wrapContentSize(),
                keyboardOptions = KeyboardOption(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    label = AppConstants.ZIP_CODE,
                    placeholder = AppConstants.ZIP_CODE_PLACEHOLDER
                ),
                DefaultIcons(leadingIcon = Icons.Default.Pin),
                isError = cookPersonalInfoState.errorState.zipCodeErrorState.hasError,
                errorText = stringResource(id = cookPersonalInfoState.errorState.zipCodeErrorState.errorMessageStringResource),
                maxChar = 25,
                textColor = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Registration Submit Button
        OutlinedSubmitButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
            text = stringResource(id = R.string.submit_button_text),
            textColor = Color.Gray,
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )
    }
}
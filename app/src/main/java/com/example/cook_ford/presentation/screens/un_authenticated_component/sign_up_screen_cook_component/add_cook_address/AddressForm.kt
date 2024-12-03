package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.add_cook_address
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
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
import com.example.cook_ford.presentation.component.widgets.TrailingIcon
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.add_cook_address.state.AddressState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.utils.AppConstants

val genders = listOf("Male", "Female")

@Composable
fun AddressForm(
    addressState: AddressState,
    viewState: MainViewState,
    onAddressChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onStateChange: (String) -> Unit,
    onZipCodeChange: (String) -> Unit,
    onSubmit: () -> Unit){

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = addressState.address,
            onChange = onAddressChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.ADDRESS, placeholder = AppConstants.ADDRESS_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = addressState.errorState.addressErrorState.hasError,
            errorText = stringResource(id = addressState.errorState.addressErrorState.errorMessageStringResource),
            maxChar = 30,
            textColor = Color.Gray
            )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = addressState.city,
            onChange = onCityChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.CITY, placeholder = AppConstants.CITY_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Email),
            isError = addressState.errorState.stateErrorState.hasError,
            errorText = stringResource(id = addressState.errorState.stateErrorState.errorMessageStringResource),
            maxChar = 30,
            textColor = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = addressState.state,
            onChange = onStateChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text,label = AppConstants.STATE, placeholder = AppConstants.STATE_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
            isError = addressState.errorState.cityErrorState.hasError,
            errorText = stringResource(id = addressState.errorState.cityErrorState.errorMessageStringResource),
            maxChar = 25,
            textColor = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = addressState.zipCode,
            onChange = onZipCodeChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text,label = AppConstants.ZIP_CODE, placeholder = AppConstants.ZIP_CODE_PLACEHOLDER),
            DefaultIcons(leadingIcon = Icons.Default.Lock, trailingIcon = TrailingIcon(Icons.Default.VisibilityOff, Icons.Default.Visibility)),
            isError = addressState.errorState.zipCodeErrorState.hasError,
            errorText = stringResource(id = addressState.errorState.zipCodeErrorState.errorMessageStringResource),
            maxChar = 25,
            textColor = Color.Gray
        )

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
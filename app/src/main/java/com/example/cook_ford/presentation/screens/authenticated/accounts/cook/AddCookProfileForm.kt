package com.example.cook_ford.presentation.screens.authenticated.accounts.cook
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.DropDownMenu
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.accounts.cook.state.AddCookProfileState
import com.example.cook_ford.utils.AppConstants
import com.example.cook_ford.utils.FontName
import com.google.gson.Gson

@Composable
fun AddCookProfileForm(
    changeProfileState: MutableState<String>,
    addCookProfileState: AddCookProfileState,
    viewState: MainViewState,
    onUserNameChange: (String) -> Unit,
    onAlternatePhoneChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onProfileImageChange: (String) -> Unit,
    onJobTypeChange: (MutableSet<String>) -> Unit,
    onSubmit: () -> Unit){


    val jobType = listOf(
        "Part time\n(Daily/Occasional meals)",
        "Full day\n(Domestic)",
        "Live in\n(Domestic)",
        "Catering\n(Parties & Events)")
    val jobTypeLast = listOf("Restaurant chef\n(Commercial)")
    val genders = listOf("Male", "Female")
    val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        // Profile Image
        ProfileImage(changeProfileState, onChange = onProfileImageChange)

        Spacer(modifier = Modifier.height(10.dp))

        JobTypeSection(jobType = jobType, jobTypeLast = jobTypeLast, onChange = onJobTypeChange,
            isError = addCookProfileState.errorState.jobTypeErrorState.hasError,
            errorText = stringResource(id = addCookProfileState.errorState.jobTypeErrorState.errorMessageStringResource),)

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Select cook`s city", fontFamily = FontName, fontWeight = FontWeight.Bold)

            DropDownMenu(
                value = addCookProfileState.city,
                onChange = onCityChange,
                isError = addCookProfileState.errorState.cityErrorState.hasError,
                errorText = stringResource(id = addCookProfileState.errorState.cityErrorState.errorMessageStringResource),
                textColor = Color.Gray,
                cities = mCities,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = addCookProfileState.username,
            onChange = onUserNameChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                label = AppConstants.COOK_NAME,
                placeholder = AppConstants.COOK_NAME_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = addCookProfileState.errorState.usernameErrorState.hasError,
            errorText = stringResource(id = addCookProfileState.errorState.usernameErrorState.errorMessageStringResource),
            maxChar = 30,
            texColor = Color.Gray
            /*submit = { TODO() }*/
        )
        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = addCookProfileState.phone,
            onChange = onPhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone,
                label = AppConstants.COOK_PHONE,
                placeholder = AppConstants.COOK_PHONE_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = addCookProfileState.errorState.phoneErrorState.hasError,
            errorText = stringResource(id = addCookProfileState.errorState.phoneErrorState.errorMessageStringResource),
            maxChar = 12,
            texColor = Color.Gray
            /*submit = { TODO() }*/
        )
        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = addCookProfileState.alternatePhone,
            onChange = onAlternatePhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Phone,
                label = AppConstants.COOK_ALTERNATE_PHONE,
                placeholder = AppConstants.COOK_ALTERNATE_PHONE_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = addCookProfileState.errorState.alternatePhoneErrorState.hasError,
            errorText = stringResource(id = addCookProfileState.errorState.alternatePhoneErrorState.errorMessageStringResource),
            maxChar = 12,
            texColor = Color.Gray
            /*submit = { TODO() }*/
        )
        Spacer(modifier = Modifier.height(10.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Gender", fontFamily = FontName, fontWeight = FontWeight.Bold)
            SegmentedControl(
                items = genders,
                defaultSelectedItemIndex = 0,) {
                onGenderChange.invoke(genders[it])
                Log.e("CustomToggle", "Selected item : ${genders[it]}")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        StatusCard()
        Spacer(modifier = Modifier.height(20.dp))

        // EditProfile Submit Button
        OutlinedSubmitButton(
            modifier = Modifier.padding(all = 10.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.submit_button_text),
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )

        Spacer(modifier = Modifier.height(10.dp))

        //EditProfile SignOut Button
      /*  OutlinedSubmitButton(
            modifier = Modifier.padding(top = 5.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.sign_out_button_text),
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )*/
    }
}

@Composable
fun JobTypeSection(
    jobType: List<String>,
    jobTypeLast: List<String>,
    onChange: (MutableSet<String>) -> Unit,
    isError: Boolean,
    errorText: String) {

    val columnsCount = 2
    LazyVerticalGrid(
        columns = GridCells.Fixed(columnsCount),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(jobType.size) { index ->
            JobTypeItem(
                label = jobType[index],
                selected = index == 0,
                enabled = index == 0,
                onChange = onChange,
            )
        }

        items(jobTypeLast.size, span = { GridItemSpan(columnsCount) }) { index->
            JobTypeItem(
                label =jobTypeLast[index],
                selected = false,
                enabled =false,
                onChange = onChange,
            )
        }
    }
    if(isError) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = errorText, color = Color.Red)
    }
}

@Composable
fun JobTypeItem(
    label: String,
    selected: Boolean,
    enabled: Boolean,
    onChange: (MutableSet<String>) -> Unit) {
    val addCookProfileViewModel: AddCookProfileViewModel = hiltViewModel()
    val isSelected = remember { mutableStateOf(selected) }
    Log.d("TAG", "JobTypeItem: ${Gson().toJson(addCookProfileViewModel.selectedItem)}")
    if(isSelected.value) addCookProfileViewModel.selectedItem.add(label)
    onChange(addCookProfileViewModel.selectedItem)
    Box(modifier = Modifier
        .height(50.dp)
        .border(1.dp, Color.Gray)
        .background(if (isSelected.value) Color.LightGray else Color.White)
        .fillMaxWidth()
        .clickable {
            if (!isSelected.value) {
                isSelected.value = true
                Log.d("TAG", "JobTypeItem: if ")
                addCookProfileViewModel.selectedItem.add(label)
                onChange(addCookProfileViewModel.selectedItem)
            } else {
                isSelected.value = false
                addCookProfileViewModel.selectedItem.removeIf { it == label }
                onChange(addCookProfileViewModel.selectedItem)
                Log.d("TAG", "JobTypeItem: else ")
            }

        }) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(8.dp),
            textAlign = TextAlign.Center,
            color = if (isSelected.value) Color.White else Color.Gray
        )
    }
}


@Composable
fun StatusCard() {
    Card(modifier = Modifier,
        shape = RoundedCornerShape(10.dp),) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp,
            Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.LightGray)
                .padding(all = 10.dp)){
            Icon(Icons.Filled.VerifiedUser, contentDescription = null, tint = Color.Green)
            Text(
                text = "Before publishing the profile, we will contact the cook for verification and gather additional details during the call.",
                style = MaterialTheme.typography.subtitle2,
                fontFamily = FontName,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        }
    }
}


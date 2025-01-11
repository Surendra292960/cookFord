package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileState
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
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Explicit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
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
import com.example.cook_ford.presentation.component.MultiSelectionComponent
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.DropDownMenu
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.presentation.component.widgets.SmallTitleText
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.FontName
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson

@Composable
fun EditCookProfileForm(
    changeProfileState: MutableState<String>,
    editCookProfileState: EditCookProfileState,
    viewState: MainViewState,
    onWorkAreaChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onDobChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onReligionChange: (String) -> Unit,
    onExperienceChange: (String) -> Unit,
    onSalaryChange: (String) -> Unit,
    onNumberOfVisitChange: (String) -> Unit,
    onFoodTypeChange: (String) -> Unit,
    onAlternatePhoneChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onGenderChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onProfileImageChange: (String) -> Unit,
    onJobTypeChange: (MutableSet<String>) -> Unit,
    onLanguageChange: (MutableSet<String>) -> Unit,
    onCuisineChange: (MutableSet<String>) -> Unit,
    onSubmit: () -> Unit){


    val jobType = listOf(
        "Part time\n(Daily/Occasional meals)",
        "Full day\n(12hrs, Domestic)",
        "Live in\n(24hrs, Domestic)",
        "Party\n(One time)")
    val jobTypeLast = listOf("Commercial Chef\n(Hotel, Restaurant)")
    val genders = listOf("Male", "Female")
    val numberOfVisit = listOf("One", "Two")
    val foodType = listOf("Yes", "No")
    val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start) {

        // Profile Image
        ProfileImage(modifier = Modifier, changeProfileState, onChange = onProfileImageChange)

        Spacer(modifier = Modifier.height(10.dp))

        JobTypeSection(jobType = jobType, jobTypeLast = jobTypeLast, onChange = {
            onJobTypeChange.invoke(it)
        },
            isError = editCookProfileState.errorState.jobTypeErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.jobTypeErrorState.errorMessageStringResource),)

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            MediumTitleText(
                modifier = Modifier,
                text = AppConstants.SELECT_COOK_CITY,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Start,
                textColor = Color.DarkGray
            )

            DropDownMenu(
                value = editCookProfileState.city,
                onChange = onCityChange,
                isError = editCookProfileState.errorState.cityErrorState.hasError,
                errorText = stringResource(id = editCookProfileState.errorState.cityErrorState.errorMessageStringResource),
                textColor = Color.Gray,
                cities = mCities,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

           Column(modifier = Modifier) {
               MediumTitleText(
                   modifier = Modifier,
                   text = AppConstants.WORK_AREA,
                   fontWeight = FontWeight.W700,
                   textAlign = TextAlign.Start,
                   textColor = Color.DarkGray
               )

               Spacer(modifier = Modifier.height(5.dp))

               SmallTitleText(
                   modifier = Modifier,
                   text = AppConstants.WORK_AREA_SUBTEXT,
                   fontWeight = FontWeight.W200,
                   textAlign = TextAlign.Start,
                   textColor = Color.DarkGray
               )
           }

            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.Edit,
                contentDescription = "",
                tint = Color.Gray
            )

           /* SegmentedControl(
                items = numberOfVisit,
                defaultSelectedItemIndex = 0,) {
                onWorkAreaChange.invoke(numberOfVisit[it])
                Log.e("CustomToggle", "Selected item : ${numberOfVisit[it]}")
            }*/
        }

        Spacer(modifier = Modifier.height(15.dp))

        InputTextField(
            value = editCookProfileState.username,
            onChange = onUserNameChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                label = AppConstants.COOK_NAME,
                placeholder = AppConstants.COOK_NAME_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = editCookProfileState.errorState.usernameErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.usernameErrorState.errorMessageStringResource),
            maxChar = 30,
            textColor = Color.Gray
            /*submit = { TODO() }*/
        )
        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editCookProfileState.phone,
            onChange = onPhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone,
                label = AppConstants.COOK_PHONE,
                placeholder = AppConstants.COOK_PHONE_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = editCookProfileState.errorState.phoneErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.phoneErrorState.errorMessageStringResource),
            maxChar = 12,
            textColor = Color.Gray
            /*submit = { TODO() }*/
        )
        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editCookProfileState.alternatePhone,
            onChange = onAlternatePhoneChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone,
                label = AppConstants.COOK_ALTERNATE_PHONE,
                placeholder = AppConstants.COOK_ALTERNATE_PHONE_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Phone),
            isError = editCookProfileState.errorState.alternatePhoneErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.alternatePhoneErrorState.errorMessageStringResource),
            maxChar = 12,
            textColor = Color.Gray
            /*submit = { TODO() }*/
        )
        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editCookProfileState.dob,
            onChange = onDobChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                label = AppConstants.COOK_DOB,
                placeholder = AppConstants.COOK_DOB_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = editCookProfileState.errorState.dobErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.dobErrorState.errorMessageStringResource),
            maxChar = 12,
            textColor = Color.Gray
            /*submit = { TODO() }*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editCookProfileState.address,
            onChange = onAddressChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                label = AppConstants.COOK_ADDRESS,
                placeholder = AppConstants.COOK_ADDRESS_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Place),
            isError = editCookProfileState.errorState.addressErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.addressErrorState.errorMessageStringResource),
            maxChar = 100,
            textColor = Color.Gray
            /*submit = { TODO() }*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editCookProfileState.experience,
            onChange = onExperienceChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                label = AppConstants.COOK_EXPERIENCE,
                placeholder = AppConstants.COOK_EXPERIENCE_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Explicit),
            isError = editCookProfileState.errorState.experienceErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.experienceErrorState.errorMessageStringResource),
            maxChar = 2,
            textColor = Color.Gray
            /*submit = { TODO() }*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editCookProfileState.salary,
            onChange = onSalaryChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
                label = AppConstants.COOK_SALARY,
                placeholder = AppConstants.COOK_SALARY_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.CurrencyRupee),
            isError = editCookProfileState.errorState.salaryErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.salaryErrorState.errorMessageStringResource),
            maxChar = 5,
            textColor = Color.Gray
            /*submit = { TODO() }*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        InputTextField(
            value = editCookProfileState.religion,
            onChange = onReligionChange,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOption(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                label = AppConstants.COOK_RELIGION,
                placeholder = AppConstants.COOK_RELIGION_PLACEHOLDER
            ),
            DefaultIcons(leadingIcon = Icons.Default.Person),
            isError = editCookProfileState.errorState.religionErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.religionErrorState.errorMessageStringResource),
            maxChar = 15,
            textColor = Color.Gray
            /*submit = { TODO() }*/
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Cuisines
        MediumTitleText(
            modifier = Modifier,
            text = AppConstants.CUISINES,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            textColor = Color.DarkGray,
            isError = editCookProfileState.errorState.cuisinesErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.cuisinesErrorState.errorMessageStringResource),
        )

        editCookProfileState.cuisineResponse?.let { cuisines ->
            MultiSelectionComponent(
                modifier = Modifier,
                itemList = cuisines.cuisine.map { it.cuisinetype },
                onSelectionChanged = {
                    //onCuisineChange.invoke(it)
                    Log.d("TAG", "EditCookProfileScreen cuisines: $it")
                }
            )
        }
        //Languages
        MediumTitleText(
            modifier = Modifier,
            text = AppConstants.LANGUAGES,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            textColor = Color.DarkGray,
            isError = editCookProfileState.errorState.languagesErrorState.hasError,
            errorText = stringResource(id = editCookProfileState.errorState.languagesErrorState.errorMessageStringResource),
        )

        editCookProfileState.languagesResponse?.let { languages ->
            MultiSelectionComponent(
                modifier = Modifier,
                itemList = languages.language.map { it.langtype },
                onSelectionChanged = {
                    //onLanguageChange.invoke(it)
                    Log.d("TAG", "EditCookProfileScreen languages: $it")
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            MediumTitleText(
                modifier = Modifier,
                text = AppConstants.NO_OF_VISIT,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                textColor = Color.DarkGray,
            )

            SegmentedControl(
                items = numberOfVisit,
                defaultSelectedItemIndex = 0,) {
                onNumberOfVisitChange.invoke(numberOfVisit[it])
                Log.e("CustomToggle", "numberOfVisit Selected item : ${numberOfVisit[it]}")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            MediumTitleText(
                modifier = Modifier,
                text = AppConstants.FOOD_TYPE,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                textColor = Color.DarkGray
            )

            SegmentedControl(
                items = foodType,
                defaultSelectedItemIndex = 0) {
                onFoodTypeChange.invoke(foodType[it])
                Log.e("CustomToggle", "Selected item : ${foodType[it]}")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            MediumTitleText(
                modifier = Modifier,
                text = AppConstants.GENDER,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                textColor = Color.DarkGray
            )

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

        // Submit Button
        OutlinedSubmitButton(
            modifier = Modifier.padding(all = 10.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.submit_button_text),
            isLoading = viewState.isLoading,
            onClick = onSubmit
        )

        Spacer(modifier = Modifier.height(10.dp))
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
            .padding(top = 5.dp)
            .height(160.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(jobType.size) { index ->
            JobTypeItem(
                label = jobType[index],
                selected = index == 0,
                enabled = index == 0,
                onChange = {
                    onChange.invoke(it)
                },
            )
        }

        items(jobTypeLast.size, span = { GridItemSpan(columnsCount) }) { index->
            JobTypeItem(
                label =jobTypeLast[index],
                selected = false,
                enabled =false,
                onChange = {
                    onChange.invoke(it)
                },
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
    val addCookProfileViewModel: EditCookProfileViewModel = hiltViewModel()
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
            Icon(Icons.Filled.VerifiedUser, contentDescription = null, tint = DeepGreen)
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


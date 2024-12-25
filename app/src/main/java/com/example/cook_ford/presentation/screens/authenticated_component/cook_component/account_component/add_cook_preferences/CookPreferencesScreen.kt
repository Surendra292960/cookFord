package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_preferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.CookTypeSingleSection
import com.example.cook_ford.presentation.component.MultiSelectionComponent
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson


val jobType = listOf("Part time\n(Daily/Occasional meals)", "Full day\n(Domestic)", "Live in Cook\n(Domestic)", "Professional Chef\n(Hotel & Restaurant)", "Catering Chef\n(Parties & Events)")
val jobTypeLast = listOf("Restaurant chef\n(Commercial)")
val genders = listOf("Male", "Female")
val relocate = listOf("Yes", "No")
val visits = listOf("One Visit", "Two Visit", "Three Visit")
val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")
val morning = listOf("5am-6am", "6am-7am", "7am-8am", "8am-9am", "9am-10am", "10am-11am", "11am-12am")
val afternoon = listOf("12pm-1pm", "1pm-2pm", "2pm-3pm", "3pm-4pm", "4pm-5pm")
val evening = listOf("5pm-6pm", "6pm-7pm", "7pm-8pm", "8pm-9pm", "9pm-10pm", "10pm-11pm", "11pm-12pm")
val cuisine = listOf("North Indian", "South indian", "Punjabi", "Andhra", "Kerala", "Tamillian", "Karnataka", "Jain", "Rajasthani", "Gujarati", "Bengali", "Maharashtrian", "Chinese", "Mughalai", "Nepali", "Mangalorean", "Kashmiri", "Assamese", "Arabic", "Italic", "Maxican", "Parsi", "Goan", "Thai", "Afghani", "Turkish")
val languages = listOf("Hindi", "English", "Kannada", "Odia", "Tamil", "Punjabi", "Telugu", "Telugu", "Malayalam", "Bhojpuri", "Rajasthani", "Gujarati", "Marathi", "Nepali", "Assamese", "Bengali", "Konkani", "Tulu", "Urdu")

@Composable
fun CookPreferencesScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedRoute: () -> Unit){

    var cookType by remember { mutableIntStateOf(0) }

    Column(
        Modifier.background(Color.White).fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween) {

        Column( modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
            .weight(1f, false), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.Start) {

            //Cook Types
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(
                modifier = Modifier,
                text = AppConstants.COOK_TYPE,
                fontWeight = FontWeight.W900,
                textAlign = TextAlign.Center,
                textColor = Color.Gray
            )
            CookTypeSingleSection(
                cookType = jobType,
                onIndexChange = {
                    cookType = it
                    Log.d("TAG", "CookPreferencesScreen Index : $it")
                },
                onChange = { },
                isError = false,
                errorText = "Error",
            )

            //Number of visit
           if (cookType == 0){
               Spacer(modifier = Modifier.height(20.dp))
               TitleText(
                   modifier = Modifier,
                   text = AppConstants.NO_OF_VISIT_A_DAY,
                   fontWeight = FontWeight.W900,
                   textAlign = TextAlign.Center,
                   textColor = Color.Gray
               )
               SegmentedControl(
                   items = visits,
                   defaultSelectedItemIndex = 0) {
                   Log.e("CustomToggle", "Selected item : ${genders[it]}")
               }
           }

            //Relocate Chef
            if (cookType == 3){
                Spacer(modifier = Modifier.height(20.dp))
                TitleText(
                    modifier = Modifier,
                    text = AppConstants.CHEF_READY_TO_RELOCATE,
                    fontWeight = FontWeight.W900,
                    textAlign = TextAlign.Center,
                    textColor = Color.Gray
                )
                SegmentedControl(
                    items = relocate,
                    defaultSelectedItemIndex = 0) {
                    Log.e("CustomToggle", "Selected item : ${genders[it]}")
                }
            }

            //Gender
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(
                modifier = Modifier,
                text = AppConstants.GENDER,
                fontWeight = FontWeight.W900,
                textAlign = TextAlign.Center,
                textColor = Color.Gray
            )
            SegmentedControl(
                items = genders,
                defaultSelectedItemIndex = 0) {
                Log.e("CustomToggle", "Selected item : ${genders[it]}")
            }

            //Time Shift
            if (cookType==0 || cookType==4){

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.W900)) {
                            append("Who is available for")
                        }
                        withStyle(style = SpanStyle(color = Color.Gray, fontSize = 13.sp, fontWeight = FontWeight.W400, fontStyle = FontStyle.Italic)) {
                            append(" (any of the selected)")
                        }
                    },
                    modifier = Modifier
                )

                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    TitleText(
                        modifier = Modifier,
                        text = AppConstants.MORNING,
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.Center,
                        textColor = Color.Gray
                    )
                    // Spacer(modifier = Modifier.width(10.dp))
                    MultiSelectionComponent(
                        modifier = Modifier,
                        itemList = morning,
                        onSelectionChanged = {
                            Log.d("TAG", "CookPreferencesScreen: $it")
                        }
                    )
                }

                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    TitleText(
                        modifier = Modifier,
                        text = AppConstants.AFTER_NOON,
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.Center,
                        textColor = Color.Gray
                    )
                    //Spacer(modifier = Modifier.width(10.dp))
                    MultiSelectionComponent(
                        modifier = Modifier,
                        itemList = afternoon,
                        onSelectionChanged = {
                            Log.d("TAG", "CookPreferencesScreen: $it")
                        }
                    )
                }

                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    TitleText(
                        modifier = Modifier,
                        text = AppConstants.EVENING,
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.Center,
                        textColor = Color.Gray
                    )
                    // Spacer(modifier = Modifier.width(10.dp))
                    MultiSelectionComponent(
                        modifier = Modifier,
                        itemList = evening,
                        onSelectionChanged = {
                            Log.d("TAG", "CookPreferencesScreen: $it")
                        }
                    )
                }
            }

            //Cuisine
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.W900)) {
                        append("Who can prepare")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray, fontSize = 13.sp, fontWeight = FontWeight.W400, fontStyle = FontStyle.Italic)) {
                        append(" (any of the selected)")
                    }
                },
                modifier = Modifier
            )

            MultiSelectionComponent(
                modifier = Modifier,
                itemList = cuisine,
                onSelectionChanged = {
                    Log.d("TAG", "CookPreferencesScreen: $it")
                }
            )

            //Languages
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.W900)) {
                        append("Who can speak")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray, fontSize = 13.sp, fontWeight = FontWeight.W400, fontStyle = FontStyle.Italic)) {
                        append(" (any of the selected)")
                    }
                },
                modifier = Modifier
            )

            MultiSelectionComponent(
                modifier = Modifier,
                itemList = languages,
                onSelectionChanged = {
                    Log.d("TAG", "CookPreferencesScreen: $it")
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            TitleText(
                modifier = Modifier,
                text = AppConstants.EXPERIENCED_BETWEEN,
                fontWeight = FontWeight.W900,
                textAlign = TextAlign.Center,
                textColor = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))
            SliderAdvancedExample(selectedExperience = {
                Log.d("TAG","Slider Data : ${it.toInt()}")
            })

            Spacer(modifier = Modifier.height(20.dp))
        }

        HorizontalDivider(modifier = Modifier.height(1.dp))
        Row (modifier = Modifier.background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){

            OutlinedSmallSubmitButton(
                modifier = Modifier.padding(all = 10.dp),
                textColor = Color.Gray,
                text = stringResource(id = R.string.apply_button_text),
                isLoading = false,
                onClick = { /*onSubmit*/ }
            )
        }
    }
}

@Composable
fun SliderAdvancedExample(selectedExperience:(Float)->Unit) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically) {
        TitleText(
            modifier = Modifier,
            text = "0 yr",
            fontWeight = FontWeight.W900,
            textAlign = TextAlign.Center,
            textColor = Color.Gray
        )
        Slider(
            modifier = Modifier.weight(1f),
            value = sliderPosition,
            onValueChange = { selectedExperience.invoke(it)
                                sliderPosition = it
                            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 50,
            valueRange = 0f..50f
        )
        TitleText(
            modifier = Modifier,
            text = sliderPosition.toInt().toString().plus(" Yr"),
            fontWeight = FontWeight.W900,
            textAlign = TextAlign.Center,
            textColor = Color.Gray
        )
    }
}

@Composable
fun CookTypeSection(
    cookType: List<String>,
    cookTypeLast: List<String>,
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
        items(cookType.size) { index ->
            CookTypeItem(
                label = cookType[index],
                selected = index == 0,
                enabled = index == 0,
                onChange = onChange,
            )
        }

        items(cookTypeLast.size, span = { GridItemSpan(columnsCount) }) { index->
            CookTypeItem(
                label =cookTypeLast[index],
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
fun CookTypeItem(
    label: String,
    selected: Boolean,
    enabled: Boolean,
    onChange: (MutableSet<String>) -> Unit) {
    val cookPreferencesViewModel: CookPreferencesViewModel = hiltViewModel()
    val isSelected = remember { mutableStateOf(selected) }
    Log.d("TAG", "CookTypeItem: ${Gson().toJson(cookPreferencesViewModel.selectedItem)}")
    if(isSelected.value) cookPreferencesViewModel.selectedItem.add(label)
    onChange(cookPreferencesViewModel.selectedItem)
    Box(modifier = Modifier
        .height(50.dp)
        .border(1.dp, Color.Gray)
        .background(if (isSelected.value) Color.LightGray else Color.White)
        .fillMaxWidth()
        .clickable {
            if (!isSelected.value) {
                isSelected.value = true
                Log.d("TAG", "CookTypeItem: if ")
                cookPreferencesViewModel.selectedItem.add(label)
                onChange(cookPreferencesViewModel.selectedItem)
            } else {
                isSelected.value = false
                cookPreferencesViewModel.selectedItem.removeIf { it == label }
                onChange(cookPreferencesViewModel.selectedItem)
                Log.d("TAG", "CookTypeItem: else ")
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    CookPreferencesScreen(onNavigateBack={}, onNavigateToAuthenticatedRoute={})
}
package com.example.cook_ford.presentation.screens.authenticated.accounts.cook_preferences
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.report_screen_component.SingleSelectionComponent
import com.example.cook_ford.presentation.theme.FontName
import com.google.gson.Gson


val jobType = listOf("Part time\n(Daily/Occasional meals)", "Full day\n(Domestic)", "Live in\n(Domestic)", "Catering\n(Parties & Events)")
val jobTypeLast = listOf("Restaurant chef\n(Commercial)")
val genders = listOf("Male", "Female", "Any")
val visits = listOf("One Visit", "Two Visit", "Three Visit")
val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")
val morning = listOf("5am-6am", "6am-7am", "7am-8am", "8am-9am", "9am-10am", "10am-11am", "11am-12am")
val afternoon = listOf("12pm-1pm", "1pm-2pm", "2pm-3pm", "3pm-4pm", "4pm-5pm")
val evening = listOf("5pm-6pm", "6pm-7pm", "7pm-8pm", "8pm-9pm", "9pm-10pm", "10pm-11pm", "11pm-12pm")
val cuisine = listOf("North Indian", "South indian", "Punjabi", "Andhra", "Kerala", "Tamillian", "Karnataka", "Jain", "Rajasthani", "Gujarati", "Bengali", "Maharashtrian", "Chinese", "Mughalai", "Nepali", "Mangalorean", "Kashmiri", "Assamese", "Arabic", "Italic", "Maxican", "Parsi", "Goan", "Thai", "Afghani", "Turkish")
val languages = listOf("Hindi", "English", "Kanada", "Odia", "Tamil", "Punjabi", "Telugu", "Telugu", "Malayalam", "Bhojpuri", "Rajasthani", "Gujarati", "Marathi", "Nepali", "Assamese", "Bengali", "Konkani", "Tulu", "Urdu")

@Composable
fun CookPreferencesScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedRoute: () -> Unit){

    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween) {

        Column( modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
            .weight(1f, false), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.Start) {

            //Cook Types
            Spacer(modifier = Modifier.height(20.dp))
            Text(modifier = Modifier.align(Alignment.Start), text = "Cook Type", fontFamily = FontName, fontWeight = FontWeight.Bold)
            JobTypeSection(
                jobType = jobType, jobTypeLast = jobTypeLast, onChange = {  },
                isError = false,
                errorText = "Error",
            )

            //Number of visit
            Spacer(modifier = Modifier.height(20.dp))
            Text(modifier = Modifier.align(Alignment.Start), text = "Number of visit per day", fontFamily = FontName, fontWeight = FontWeight.Bold)
            SegmentedControl(
                items = visits,
                defaultSelectedItemIndex = 0) {
                Log.e("CustomToggle", "Selected item : ${genders[it]}")
            }

            //Gender
            Spacer(modifier = Modifier.height(20.dp))
            Text(modifier = Modifier.align(Alignment.Start), text = "Gender", fontFamily = FontName, fontWeight = FontWeight.Bold)
            SegmentedControl(
                items = genders,
                defaultSelectedItemIndex = 0) {
                Log.e("CustomToggle", "Selected item : ${genders[it]}")
            }

            //Time Shift
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Who is available for (any of the selected)", fontFamily = FontName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalDivider(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Text(modifier = Modifier, text = "Morning", fontFamily = FontName, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(10.dp))
                SingleSelectionComponent(
                    modifier = Modifier,
                    issueList = morning,
                    onIssueChange = {}
                )
            }

            HorizontalDivider(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier,
                    text = "Afternoon",
                    fontFamily = FontName,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                SingleSelectionComponent(
                    modifier = Modifier,
                    issueList = afternoon,
                    onIssueChange = {}
                )
            }

            HorizontalDivider(modifier = Modifier.height(1.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier,
                    text = "Evening",
                    fontFamily = FontName,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                SingleSelectionComponent(
                    modifier = Modifier,
                    issueList = evening,
                    onIssueChange = {}
                )
            }


            //Cuisine
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Who can prepare (any of the selected)", fontFamily = FontName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalDivider(modifier = Modifier.height(1.dp))
            SingleSelectionComponent(
                modifier = Modifier,
                issueList = cuisine,
                onIssueChange = {}
            )

            //Languages
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Who can speak (any of the selected)", fontFamily = FontName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalDivider(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.height(10.dp))
            SingleSelectionComponent(
                modifier = Modifier,
                issueList = languages,
                onIssueChange = {}
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Who is Experienced between", fontFamily = FontName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalDivider(modifier = Modifier.height(1.dp))
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

            OutlinedSubmitButton(
                modifier = Modifier.padding(all = 10.dp),
                textColor = Color.Gray,
                text = stringResource(id = R.string.submit_button_text),
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
        Text(text = "0 yr", fontFamily = FontName, fontWeight = FontWeight.Bold)
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
        Text(text = sliderPosition.toInt().toString().plus(" Yr"), fontFamily = FontName, fontWeight = FontWeight.Bold)
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
    val cookPreferencesViewModel:CookPreferencesViewModel = hiltViewModel()
    val isSelected = remember { mutableStateOf(selected) }
    Log.d("TAG", "JobTypeItem: ${Gson().toJson(cookPreferencesViewModel.selectedItem)}")
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
                Log.d("TAG", "JobTypeItem: if ")
                cookPreferencesViewModel.selectedItem.add(label)
                onChange(cookPreferencesViewModel.selectedItem)
            } else {
                isSelected.value = false
                cookPreferencesViewModel.selectedItem.removeIf { it == label }
                onChange(cookPreferencesViewModel.selectedItem)
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    CookPreferencesScreen(onNavigateBack={}, onNavigateToAuthenticatedRoute={})
}
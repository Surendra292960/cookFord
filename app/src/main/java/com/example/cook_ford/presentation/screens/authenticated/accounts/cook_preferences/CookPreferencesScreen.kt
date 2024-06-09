package com.example.cook_ford.presentation.screens.authenticated.accounts.cook_preferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.utils.FontName
import com.google.gson.Gson

@Composable
fun CookPreferencesScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit){

    val jobType = listOf(
        "Part time\n(Daily/Occasional meals)",
        "Full day\n(Domestic)",
        "Live in\n(Domestic)",
        "Catering\n(Parties & Events)")
    val jobTypeLast = listOf("Restaurant chef\n(Commercial)")
    val genders = listOf("Male", "Female", "Any")
    val visits = listOf("One Visit", "Two Visit", "Three Visit")
    val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(rememberScrollState()),
        //verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start) {


        Spacer(modifier = Modifier.height(20.dp))
        
        Text(modifier = Modifier.align(Alignment.Start), text = "Cook Type", fontFamily = FontName, fontWeight = FontWeight.Bold)
        JobTypeSection(
            jobType = jobType, jobTypeLast = jobTypeLast, onChange = {  },
            isError = false,
            errorText = "Error",
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(modifier = Modifier.align(Alignment.Start), text = "Number of visit per day", fontFamily = FontName, fontWeight = FontWeight.Bold)
        SegmentedControl(
            items = visits,
            defaultSelectedItemIndex = 0) {
            Log.e("CustomToggle", "Selected item : ${genders[it]}")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(modifier = Modifier.align(Alignment.Start), text = "Gender", fontFamily = FontName, fontWeight = FontWeight.Bold)
        SegmentedControl(
            items = genders,
            defaultSelectedItemIndex = 0) {
            Log.e("CustomToggle", "Selected item : ${genders[it]}")
        }


        Text(text = "Who is available for (any of the selected)", fontFamily = FontName, fontWeight = FontWeight.Bold)
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
package com.example.cook_ford.presentation.screens.authenticated.accounts.cook_preferences
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cook_ford.presentation.component.widgets.SegmentedControl
import com.example.cook_ford.presentation.screens.authenticated.accounts.cook.JobTypeSection
import com.example.cook_ford.utils.FontName

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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    CookPreferencesScreen(onNavigateBack={}, onNavigateToAuthenticatedRoute={})
}
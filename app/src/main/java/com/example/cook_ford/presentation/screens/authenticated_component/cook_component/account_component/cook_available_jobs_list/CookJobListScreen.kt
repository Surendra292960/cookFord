package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.cook_available_jobs_list

import androidx.compose.runtime.Composable
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.presentation.theme.Green

@Composable
fun CookJobListScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse? = null,
    onNavigateToAuthenticatedRoute: () -> Unit
) { Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(16.dp)
) {
    HeaderSection()
    Spacer(modifier = Modifier.height(16.dp))
    JobCard(
        workType = "Part time",
        foodType = "Veg & Non Veg",
        workTiming = "8am-9am, 8pm-9pm",
        cuisines = "North Indian, South Indian, Punjabi, Andhra, Kerala, Tamilian, Karnataka",
        noOfMembers = "1",
        days = "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday",
        location = "Kasturi Nagar, Bengaluru",
        customerName = "Gowtham",
        visitPerDay = "Two Visits",
        postedDaysAgo = "11 days ago",
        languages = "English"
    )
    Spacer(modifier = Modifier.height(16.dp))
    JobCard(
        workType = "Part time",
        foodType = "Veg & Non Veg",
        workTiming = "6am-7am, 6pm-7pm",
        cuisines = "Not mentioned",
        noOfMembers = "1",
        days = "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday",
        location = "Kasturi Nagar, Bengaluru",
        customerName = "Gowtham",
        visitPerDay = "Two Visits",
        postedDaysAgo = "11 days ago",
        languages = "English, Telugu, Hindi"
    )
}
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Job applied today: 1", fontWeight = FontWeight.Bold, color = Color(0xFF00C853))
            Text("Daily Limit: 5", fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("2", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color(0xFF00C853))
            Text("call credits available", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun JobCard(
    workType: String,
    foodType: String,
    workTiming: String,
    cuisines: String,
    noOfMembers: String,
    days: String,
    location: String,
    customerName: String,
    visitPerDay: String,
    postedDaysAgo: String,
    languages: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text("Work type", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(workType, fontSize = 14.sp)
                }
                Spacer(Modifier.width(16.dp))
                Column {
                    Text("Food type", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(foodType, fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Work timing: $workTiming", fontSize = 14.sp)
            Text("Cuisines: $cuisines", fontSize = 14.sp)
            Text("No. of Members: $noOfMembers", fontSize = 14.sp)
            Text("Days: $days", fontSize = 14.sp)
            Text("Location: $location", fontSize = 14.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Customer name: $customerName", fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Handle call */ }) {
                    Icon(Icons.Default.Phone, tint = Green, contentDescription = "Call")
                }
                IconButton(onClick = { /* Handle WhatsApp */ }) {
                    Icon(Icons.Default.Whatsapp, tint = Green, contentDescription = "WhatsApp")
                }
            }
            Text("Visit per day: $visitPerDay", fontSize = 14.sp)
            Text("Posted: $postedDaysAgo", fontSize = 14.sp, color = Color.Gray)
            Text("Languages: $languages", fontSize = 14.sp)
        }
    }
}
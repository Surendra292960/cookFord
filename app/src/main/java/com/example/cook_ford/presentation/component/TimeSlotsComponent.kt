package com.example.cook_ford.presentation.component
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cook_ford.data.remote.profile_response.TimeSlots
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.theme.LightGray_2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TimeSlotsComponent(timeSlots: List<TimeSlots>) {
	FlowRow {
		timeSlots.forEach { timeSlots ->
			timeSlots.slots?.let {
				FilterChip(
					shape = RoundedCornerShape(1.dp),
					modifier = Modifier.padding(horizontal = 2.dp).background(Color.White),
					onClick = { },
					label = {
						timeSlots.slots?.let { time->
							MediumTitleText(
								modifier = Modifier,
								text = time,
								fontWeight = FontWeight.W400,
								textAlign = TextAlign.Center,
								textColor = Color.DarkGray
							) }
					},
					selected = true,
					colors = FilterChipDefaults.filterChipColors(selectedContainerColor = LightGray_2)
				)
			}
		}
	}
}
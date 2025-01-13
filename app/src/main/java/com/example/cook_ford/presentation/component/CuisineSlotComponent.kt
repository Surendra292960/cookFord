package com.example.cook_ford.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CuisineSlotComponent(
    slots: List<String>,
    textColor: Color = Color.Gray,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Color.Transparent
) {

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        slots.forEachIndexed { index, slotsData ->
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .border(1.dp, borderColor)
                    .background(backgroundColor)
            ) {
                Text(
                    text = slotsData,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
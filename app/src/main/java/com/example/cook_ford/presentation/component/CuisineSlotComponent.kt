package com.example.cook_ford.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cook_ford.presentation.component.widgets.SmallSubTitleText
import com.example.cook_ford.presentation.theme.Green


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CuisineSlotComponent(
    slots: List<String>,
    maxVisibleChips:Int = 2,
    textColor: Color = Color.Gray,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Color.Transparent
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Show all chips if expanded, else show only up to maxVisibleChips
            val slotsToShow = if (expanded) slots else slots.take(n = maxVisibleChips)

            slotsToShow.forEach { slotsData ->
                Row(
                    modifier = Modifier
                        .padding(1.dp)
                        .border(1.dp, borderColor, shape = RoundedCornerShape(2.dp))
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

        // Toggle Button to show more or less
        // Show the button only if the list size is greater than maxVisibleChips
        if (slots.size > maxVisibleChips) {
            SmallSubTitleText(
                text = if (expanded) "Show Less" else "Show More",
                textAlign = TextAlign.Center,
                textColor = Color.Gray,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.border(0.5.dp, Green, shape = RoundedCornerShape(5.dp)).padding(5.dp).align(Alignment.End).clickable {
                    expanded = !expanded
                }
            )
        }
    }
}
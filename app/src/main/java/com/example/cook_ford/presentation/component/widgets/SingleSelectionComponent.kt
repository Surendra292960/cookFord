package com.example.cook_ford.presentation.component.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SingleSelectionComponent(modifier: Modifier, issueList: List<String>, onIssueChange: (String) -> Unit) {
    val selectedIndex = remember { mutableIntStateOf(-1) }

    FlowRow(horizontalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier.padding(top=5.dp, bottom = 10.dp)
            .fillMaxWidth()) {
        issueList.forEachIndexed { index, issue ->
            Surface(
                modifier = Modifier.padding(2.dp),
                //elevation = 8.dp,
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(1.dp, Color.Gray),
                color = if (selectedIndex.intValue == index) Color.LightGray else Color.Transparent
            ) {
                Row(modifier = Modifier.padding(horizontal = 4.dp)
                    .clickable {
                        selectedIndex.intValue = index
                        onIssueChange(issueList[selectedIndex.intValue])
                    }
                ) {
                    Text(
                        text = issue,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selectedIndex.intValue == index) {
                            Color.White
                        }else {
                            Color.Gray
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

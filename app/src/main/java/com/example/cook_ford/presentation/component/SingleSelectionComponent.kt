package com.example.cook_ford.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.regex.Matcher
import java.util.regex.Pattern

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SingleSelectionComponent(
    modifier: Modifier,
    issueList: List<String>,
    onIssueChange: (String) -> Unit
) {
    val selectedIndex = remember { mutableIntStateOf(-1) }

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 10.dp)
            .fillMaxWidth()) {
        issueList.forEachIndexed { index, issue ->
            Surface(
                modifier = Modifier
                    .padding(2.dp)
                    .background(Color.White),
                //elevation = 8.dp,
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(1.dp, Color.Gray),
                color = if (selectedIndex.intValue == index) Color.LightGray else Color.Transparent
            ) {
                Row(modifier = Modifier
                    .padding(horizontal = 4.dp)
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
                        } else {
                            Color.Gray
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun CookTypeSingleSection(
    cookType: List<String>,
    onChange: (String) -> Unit,
    onIndexChange: (Int) -> Unit,
    isError: Boolean,
    errorText: String) {

    val selectedIndex = remember { mutableIntStateOf(0) }

    val columnsCount = 2
    LazyVerticalGrid(
        columns = if (cookType.size % 2 == 0) GridCells.Fixed(columnsCount) else GridCells.Fixed(columnsCount),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .height(160.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)) {

        items(cookType.size) { index ->
            Box(modifier = Modifier
                .height(50.dp)
                .border(1.dp, Color.Gray)
                .background(if (selectedIndex.intValue == index) Color.LightGray else Color.White)
                .fillMaxWidth()
                .clickable {
                    selectedIndex.intValue = index
                    onChange(cookType[selectedIndex.intValue])
                    onIndexChange(index)
                }) {

                val m: Matcher = Pattern.compile("\\(([^)]+)\\)").matcher(cookType[index])

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = if (selectedIndex.intValue == index) Color.White else Color.Gray, fontWeight = FontWeight.W700)) {
                            append(cookType[index].substringBefore("("))
                        }
                        withStyle(style = SpanStyle( color = if (selectedIndex.intValue == index) Color.White else Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.W400)) {
                            while (m.find()) {
                                m.group(0)?.let { append(it) }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                )
               /* Text(
                    text = cookType[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    color = if (selectedIndex.intValue == index) Color.White else Color.Gray
                )*/
            }
        }
    }
    if (isError) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = errorText, color = Color.Red)
    }
}

@Composable
fun CookTypeSingleItemSelection(
    label: String,
    selected: Boolean,
    enabled: Boolean,
    onChange: (String) -> Unit) {

    val isSelected = remember { mutableStateOf(selected) }

    Box(modifier = Modifier
        .height(50.dp)
        .border(1.dp, Color.Gray)
        .background(if (isSelected.value) Color.LightGray else Color.White)
        .fillMaxWidth()
        .clickable {
            if (!isSelected.value) {
                isSelected.value = true
                onChange(label)
            } else {
                isSelected.value = false
                onChange(label)
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


package com.example.cook_ford.presentation.component

import android.util.Log
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
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.add_cook_preferences.CookPreferencesViewModel
import com.google.gson.Gson


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectionComponent(modifier: Modifier, itemList: List<String>, onSelectionChanged: (String) -> Unit) {

    FlowRow(horizontalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier.padding(top=5.dp, bottom = 10.dp).fillMaxWidth()) {
        itemList.forEachIndexed { _, item ->
            Chip(
                item = item,
                onSelectionChanged = { onSelectionChanged(it) },
            )
        }
    }
}

@Composable
fun Chip(item: String, onSelectionChanged: (String) -> Unit){
    var selected by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.padding(2.dp).background(Color.White),
        //elevation = 8.dp,
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(1.dp, Color.Gray),
        color = if (selected) Color.LightGray else Color.Transparent
    ) {
        Row(modifier = Modifier.padding(horizontal = 4.dp)
            .clickable {
                selected = !selected
                onSelectionChanged(item)
            }
        ) {
            Text(
                text = item,
                style = MaterialTheme.typography.bodyMedium,
                color = if (selected) {
                    Color.White
                }else {
                    Color.Gray
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}



@Composable
fun CookTypeMultiSection(
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
            CookTypeMultiItemSelection(
                label = cookType[index],
                selected = index == 0,
                enabled = index == 0,
                onChange = onChange,
            )
        }

        items(cookTypeLast.size, span = { GridItemSpan(columnsCount) }) { index->
            CookTypeMultiItemSelection(
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
fun CookTypeMultiItemSelection(
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

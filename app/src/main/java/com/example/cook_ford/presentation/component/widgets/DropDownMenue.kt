package com.example.cook_ford.presentation.component.widgets
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.cook_ford.utils.AppConstants
import com.example.cook_ford.presentation.theme.FontName

@Composable
fun DropDownMenu(
    value: String,
    onChange: (String) -> Unit,
    isError: Boolean = false,
    errorText: String = AppConstants.EMPTY_STRING,
    textColor: Color,
    cities: List<String>) {
    var mExpanded by remember { mutableStateOf(false) }


    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    //onChange(mSelectedText)
    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        Modifier
            .padding(top = 10.dp)
            .clickable { mExpanded = !mExpanded }) {

        // Create an Outlined Text Field
        // with icon and not expanded

        OutlinedTextField(
            value = value,
            onValueChange = {
              // onChange(it)
            },
            enabled = false,
            modifier = Modifier
                .width(150.dp)
                .background(Color.White)
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontName,
                fontWeight = FontWeight.Bold,
                color = textColor
            ),
            label = {Text("Select City",fontFamily = FontName, fontWeight = FontWeight.W400, color = textColor)},

            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            },
            isError = isError,
            supportingText = {
                if (isError) {
                    ErrorTextInputField(text = errorText)
                }
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            modifier = Modifier.wrapContentSize(Alignment.TopStart),
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },) {
            cities.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onChange(label)// label
                        mExpanded = false
                    }) {
                    Text(text = label, fontFamily = FontName, fontWeight = FontWeight.W400, color = textColor)
                }
            }
        }
    }
}
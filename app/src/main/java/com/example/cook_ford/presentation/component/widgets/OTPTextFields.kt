package com.example.cook_ford.presentation.component.widgets
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.presentation.theme.FontName

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * A composable function for creating an OTP input field.
 *
 * This OTP input field allows for the entry of a One Time Password (OTP) with a configurable number of characters.
 * It supports automatic population of OTP from different sources (e.g., server).
 *
 * @param modifier Modifier for styling and layout of the input field.
 * @param otpText The current text of the OTP input field.
 * @param otpLength The length of the OTP. Default is 6 characters.
 * @param shouldShowCursor Boolean flag to indicate if the cursor should be shown.
 * @param shouldCursorBlink Boolean flag to indicate if the cursor should blink.
 * @param onOtpModified Lambda function that is triggered when the OTP text changes.
 *        It provides the updated text and a flag indicating if the OTP is complete.
 * @throws IllegalArgumentException if the initial otpText length is greater than otpLength.
 *
 * Usage example:
 * OtpInputField(
 *     otpText = viewModel.otpText,
 *     otpLength = 6,
 *     onOtpTextChange = { otp, isComplete -> /* handle OTP change */ }
 * )
 */
@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpLength: Int = 6,
    shouldShowCursor: Boolean = false,
    shouldCursorBlink: Boolean = false,
    onOtpModified: (String, Boolean) -> Unit) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpLength) {
            throw IllegalArgumentException("OTP should be $otpLength digits")
        }
    }
    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpLength) {
                onOtpModified.invoke(it.text, it.text.length == otpLength)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpLength) { index ->
                    CharacterContainer(
                        index = index,
                        text = otpText,
                        shouldShowCursor = shouldShowCursor,
                        shouldCursorBlink = shouldCursorBlink,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

/**
 * An internal composable function used within [OtpInputField] to render individual character containers.
 *
 * Each character container displays a single character of the OTP and manages cursor visibility and blinking.
 *
 * @param index The position of this character in the OTP.
 * @param text The current text of the OTP input field.
 * @param shouldShowCursor Boolean flag to indicate if the cursor should be shown for this container.
 * @param shouldCursorBlink Boolean flag to indicate if the cursor should blink when shown.
 *
 * Note: This function cannot be used outside the context of [OtpInputField] as it is tailored to its specific use-case.
 */
@Composable
internal fun CharacterContainer(index: Int, text: String, shouldShowCursor: Boolean, shouldCursorBlink: Boolean) {
    val isFocused = text.length == index
    val character = when {
        index < text.length -> text[index].toString()
        else -> ""
    }

    // Cursor visibility state
    val cursorVisible = remember { mutableStateOf(shouldShowCursor) }

    // Blinking effect for the cursor
    LaunchedEffect(key1 = isFocused) {
        if (isFocused && shouldShowCursor && shouldCursorBlink) {
            while (true) {
                delay(800) // Adjust the blinking speed here
                cursorVisible.value = !cursorVisible.value
            }
        }
    }

    Box(contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier
                .width(36.dp)
                .border(
                    width = when {
                        isFocused -> 2.dp
                        else -> 1.dp
                    },
                    color = when {
                        isFocused -> Color.Black
                        else -> Color.Gray
                    },
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(2.dp),
            text = character,
            style = MaterialTheme.typography.headlineLarge,
            color = if (isFocused) Color.Gray else Color.Black,
            textAlign = TextAlign.Center
        )

        // Display cursor when focused
        AnimatedVisibility(visible = isFocused && cursorVisible.value) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(2.dp)
                    .height(24.dp) // Adjust height according to your design
                    .background(Color.Gray)
            )
        }
    }
}
@Composable
fun OTPTextFields(
    modifier: Modifier = Modifier,
    length: Int = 0,
    otp: String = "",
    onFilled: (code: String) -> Unit) {

    var code: List<Char> by remember { mutableStateOf(listOf()) }

    val focusRequesters: List<FocusRequester> = remember {
        val temp = mutableListOf<FocusRequester>()
        repeat(length) {
            temp.add(FocusRequester())
        }
        temp
    }

    Row(modifier = modifier) {
        (0 until length).forEach { index ->
            OutlinedTextField(
                modifier = Modifier
                    .width(50.dp)
                    .padding(vertical = 2.dp)
                    .focusRequester(focusRequesters[index]),

                singleLine = true,
                value = code.getOrNull(index)?.takeIf { it.isDigit() }?.toString() ?: "",
                onValueChange = { value: String ->
                    if (focusRequesters[index].freeFocus()) {
                        //For some reason this fixes the issue of focus request causing on value changed to call twice
                        val temp = code.toMutableList()
                        if (value == "") {
                            if (temp.size > index) {
                                temp.removeAt(index)
                                code = temp
                                focusRequesters.getOrNull(index - 1)?.requestFocus()
                                Log.d("OutlinedTextField", "Removed code: $code")
                            }
                        } else {
                            if (code.size > index) {
                                temp[index] = value.getOrNull(0) ?: ' '
                                Log.d("OutlinedTextField", "Entered code size greater: $code")
                            } else if (value.getOrNull(0)?.isDigit() == true) {
                                temp.add(value.getOrNull(0) ?: ' ')
                                code = temp
                                focusRequesters.getOrNull(index + 1)?.requestFocus() ?: onFilled(code.joinToString(separator = ""))
                                Log.d("OutlinedTextField", "Entered code: $code")
                            }
                        }
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.Gray,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 25.sp,
                    fontFamily = FontName,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
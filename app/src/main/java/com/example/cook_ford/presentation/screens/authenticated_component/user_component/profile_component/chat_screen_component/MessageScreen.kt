package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.chat_screen_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse

val messages = listOf(ChatMessage("Hello", false), ChatMessage("Hi", true))

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreViewScreen() {
    MessageScreen(
        onNavigateBack = {},
        profileResponse = ProfileResponse(),
        onNavigateToAuthenticatedHomeRoute = {},
        onSendMessage = {}
    )
}

@Composable
fun MessageScreen(
    modifier: Modifier = Modifier,
    onNavigateBack:()->Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedHomeRoute: () -> Unit,
    onSendMessage: (String) -> Unit, ) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                reverseLayout = true
            ) {
                items(messages.size) { message ->
                    ChatMessageItem(message = messages[message])
                }
            }

            ChatInputField(
                onSendMessage = onSendMessage,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ChatMessageItem(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isOutgoing) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (message.isOutgoing) Color.Blue else Color.Gray.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
        ) {
            Text(message.text, color = if (message.isOutgoing) Color.White else Color.Black)
        }
    }
}

@Composable
fun ChatInputField(
    onSendMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Type your message") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray.copy(alpha = 0.2f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = {
                onSendMessage(message)
                message = ""
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "Send",
                tint = Color.Blue
            )
        }
    }
}

data class ChatMessage(
    val text: String,
    val isOutgoing: Boolean
)
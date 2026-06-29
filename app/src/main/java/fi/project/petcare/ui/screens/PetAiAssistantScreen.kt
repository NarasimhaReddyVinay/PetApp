package fi.project.petcare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PetAiAssistantScreen() {
    var textFieldValue by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf(
        ChatMessage("Hello! I'm your PetCare AI Assistant. How can I help you today?", false),
    ) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("AI Pet Assistant", style = MaterialTheme.typography.headlineMedium)
        Text("Ask anything about pet care, health, or training.", style = MaterialTheme.typography.bodyMedium)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { message ->
                ChatBubble(message)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type your question...") },
                shape = RoundedCornerShape(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (textFieldValue.isNotBlank()) {
                        messages.add(ChatMessage(textFieldValue, true))
                        val userQuery = textFieldValue
                        textFieldValue = ""
                        // Mock AI response
                        messages.add(ChatMessage("That's a great question about $userQuery! Based on general pet care knowledge, you should consult a vet for specific medical advice, but usually...", false))
                    }
                },
                enabled = textFieldValue.isNotBlank()
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val alignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    val color = if (message.isUser) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
    val textColor = if (message.isUser) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = alignment) {
        Surface(
            color = color,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isUser) 16.dp else 0.dp,
                bottomEnd = if (message.isUser) 0.dp else 16.dp
            ),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = textColor,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

data class ChatMessage(val text: String, val isUser: Boolean)

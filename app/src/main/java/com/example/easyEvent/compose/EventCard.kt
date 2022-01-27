package com.example.easyEvent.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun EventCard(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        EventCardContent(name)
    }
}

@Composable
private fun EventCardContent(name: String) {
    Row(modifier = Modifier.padding(12.dp)) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        ) {
            // TODO: insert event image here
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text("Event $name", fontWeight = FontWeight.Bold)
        }
        IconButton(onClick = { /* TODO: redirect to details screen */ }) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = null
            )
        }
    }
}
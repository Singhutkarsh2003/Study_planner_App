package eu.tutorials.studyplannerapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import eu.tutorials.studyplannerapp.data.local.Task

@Composable
fun TaskItem(task: Task, onToggle: (Task) -> Unit, onDelete: (Task) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onToggle(task) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = task.title,
            modifier = Modifier.weight(1f),
            textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
        )
        IconButton(onClick = { onDelete(task) }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Task")
        }
    }
}
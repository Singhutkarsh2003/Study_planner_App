package eu.tutorials.studyplannerapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eu.tutorials.studyplannerapp.components.TaskItem
import eu.tutorials.studyplannerapp.viewModel.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    var newTask by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("New Task") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (newTask.isNotBlank()) {
                    viewModel.addTask(newTask)
                    newTask = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onToggle = { viewModel.toggleTask(it) },
                    onDelete = { viewModel.deleteTask(it) }
                )
            }
        }
    }
}
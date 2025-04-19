package eu.tutorials.studyplannerapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import eu.tutorials.studyplannerapp.data.local.AppDatabase
import eu.tutorials.studyplannerapp.data.local.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "task-db").build()
    private val taskDao = db.taskDao()

    val tasks: StateFlow<List<Task>> = taskDao.getAllTasks().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    fun addTask(title: String, category: String = "General") {
        viewModelScope.launch {
            taskDao.insert(Task(title = title, category = category))
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            taskDao.update(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }
}
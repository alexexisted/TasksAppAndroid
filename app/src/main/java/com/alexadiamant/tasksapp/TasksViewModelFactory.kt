package com.alexadiamant.tasksapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//creating a factory for a viewModel with non-empty constructor.
//constructor of TaskViewModel has TaskDao as argument, so we need to pass it to factory
class TasksViewModelFactory(private val dao: TaskDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}
package com.alexadiamant.tasksapp

import android.service.autofill.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.map
import kotlinx.coroutines.launch

//передаем объект TaskDao во вью модел
class TaskViewModel(val dao: TaskDao): ViewModel() {
    //variable for name of the task
    var newTaskName: String = ""

    val tasks = dao.getAll()
//    val tasksString = tasks.map{
//        tasks -> formatTasks(tasks)
//    }

    //method to add a new task in db
    fun addTask() {
        //method will call to insert() method from class TaskDao() which will work in background mode
        viewModelScope.launch {
            //now task is a new object of Task data class
            val task = Task()
            //now taskName column has the value of newTaskName
            task.taskName = newTaskName
            //use insert method to add name to db
            dao.insert(task)
        }
    }

//    fun formatTasks(tasks: List<Task>): String {
//        return tasks.fold(""){
//            str, item -> str + '\n' + formatTask(item)
//        }
//    }
//
//    fun formatTask(task: Task): String {
//        var str = "ID: ${task.taskId}"
//        str += '\n' + "Name: ${task.taskName}"
//        str += '\n' + "Complete: ${task.taskDone}" + '\n'
//        return str
//    }

}
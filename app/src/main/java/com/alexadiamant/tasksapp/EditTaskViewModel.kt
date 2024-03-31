package com.alexadiamant.tasksapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId: Long, val dao: TaskDao) : ViewModel() {
    //use the dao interface and task is LiveData<Task> now
    val task = dao.get(taskId)

    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun updateTask() {
        viewModelScope.launch {
            dao.update(task.value!!) //if task is null we will get exception
            _navigateToList.value = true //when true -> fragment EditTaskFragment goes to TaskFragment
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            dao.delete(task.value!!) //if task is null we will get exception
            _navigateToList.value = true //when true -> fragment EditTaskFragment goes to TaskFragment
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = false //call this method at the end of transition
    }
}
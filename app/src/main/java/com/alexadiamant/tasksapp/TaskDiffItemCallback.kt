package com.alexadiamant.tasksapp

import androidx.recyclerview.widget.DiffUtil

class TaskDiffItemCallback: DiffUtil.ItemCallback<Task>() {

    //используется для проверки того, ссылаются ли два переданных ему объекта на один элемент
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }

    //проверяет одинаково ли содержимое (context) двух объектов
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
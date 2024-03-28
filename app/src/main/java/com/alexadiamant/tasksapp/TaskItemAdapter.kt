package com.alexadiamant.tasksapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.Checkable
import androidx.cardview.widget.CardView

class TaskItemAdapter: RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {

    //setter to set a list of tasks in data
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //getting length of list
    override fun getItemCount() = data.size

    // создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
    // Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    //принимает объект ViewHolder и устанавливает необходимые данные для соответствующей строки во view-компоненте
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int){
        val item = data[position]
        holder.bind(item)
    }

    //определяте держатель представления
    class TaskItemViewHolder(val rootView: CardView): RecyclerView.ViewHolder(rootView){

        val taskName = rootView.findViewById<TextView>(R.id.task_name)
        val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)

        //создает новые держатели представления и заполняет его макет
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item, parent, false) as CardView
                return TaskItemViewHolder(view)
            }
        }

        //данные добавляются в макет держател представления
        fun bind(item: Task){
            taskName.text = item.taskName
            taskDone.isChecked = item.taskDone
        }

    }
}
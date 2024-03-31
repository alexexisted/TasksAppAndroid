package com.alexadiamant.tasksapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.Checkable
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import com.alexadiamant.tasksapp.databinding.TaskItemBinding

//adapter is getting the lambda function
class TaskItemAdapter(val clickListener: (taskId: Long) -> Unit): ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

//no need to notify 'bout all changes because we use ListAdapter, and it contains the copy of list to find changes
//    var data = listOf<Task>()
//        //we're setting new data in db using setter
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//            //set method is responsible for
//            //notifying recycler view about changes in db
//        }

    // создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
    // Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)

    //принимает объект ViewHolder и устанавливает необходимые данные для соответствующей строки во view-компоненте
    //взывается каждый раз когда представление с переработкой должно отобразить данные элемента
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int){
        val item = getItem(position) //getting the element from backup list from adapter
        holder.bind(item, clickListener) //lambda also in bind method
    }

    //определяте держатель представления
    class TaskItemViewHolder(val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root){
        //создает новые держатели представления и заполняет его макет
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.task_item, parent, false) as CardView
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)


                return TaskItemViewHolder(binding)
            }
        }

        //данные добавляются в макет держатель представления
        fun bind(item: Task, clickListener: (taskId: Long) -> Unit){
            binding.task = item
            //setting click listener to catch taps on elements
            //after click on element lambda-method is doing something
            binding.root.setOnClickListener{
                clickListener(item.taskId)
            }
        }
    }
}
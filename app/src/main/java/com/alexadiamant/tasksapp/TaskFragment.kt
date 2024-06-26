package com.alexadiamant.tasksapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alexadiamant.tasksapp.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        //return object if it isn't null, overwise return IllegalArgumentException
        val application = requireNotNull(this.activity).application
        //get the not null object and create db if it doesn't exist
        val dao = TaskDatabase.getInstance(application).taskDao

        //create an instance of factory with argument dao
        val viewModelFactory = TasksViewModelFactory(dao)
        // Создает ViewModelProvider, который будет
        // создавать модели ViewModel через данную фабрику и сохранять их в хранилище данного ViewModelStoreOwner.
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(TaskViewModel::class.java)
        //связываем представления
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter {taskId ->
            viewModel.onTaskClicked(taskId) //lambda function to navigate to other fragment which is given to viewModel
        }
        //tasksList is a component of recycler view
        binding.tasksList.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                //передаем новые данные в бекап(резервный) список адаптера
                adapter.submitList(it)
            }
        })

        //when navigateToTask() is getting new value(not null) we are navigating to another fragment with id = taskId
        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer{
            taskId -> taskId?.let{
                val action = TaskFragmentDirections.actionTaskFragmentToEditTaskFragment(taskId)
            this.findNavController().navigate(action)
            viewModel.onTaskNavigated()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
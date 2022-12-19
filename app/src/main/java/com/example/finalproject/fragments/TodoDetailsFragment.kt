package com.example.finalproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.finalproject.TodoViewModel
import com.example.finalproject.databinding.FragmentTodoDetailsBinding

class TodoDetailsFragment : Fragment() {

    private val model: TodoViewModel by activityViewModels()
    private lateinit var binding: FragmentTodoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTodoDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val todoID = it.getLong("todoID", -1)
            val todo = model.getTodoByID(todoID)
            todo?.let {
                binding.todoName.text = todo.title
                binding.todoDescription.text = todo.contents
                binding.dataTime.text = todo.imageName
            }
        }
    }

}
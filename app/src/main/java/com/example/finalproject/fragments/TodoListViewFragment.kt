package com.example.finalproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.TodoAdapter
import com.example.finalproject.TodoViewModel
import com.example.finalproject.databinding.FragmentListViewBinding
import com.example.finalproject.room.TodoEntity

class TodoListViewFragment : Fragment() {
    private lateinit var binding: FragmentListViewBinding
    private val model: TodoViewModel by activityViewModels()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton2.setOnClickListener {
            val navController = findNavController()
            if (navController.currentDestination?.id != R.id.todoAddition) {
                navController.navigate(R.id.action_to_todo_addition)
            }
        }
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        model.refresh()
        todoAdapter.todos = model.todoArray
        todoAdapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        val todoClickListener = TodoAdapter.TodoClickListener { p ->
            Log.i("fragmentListView","Clicked $p")
            openTodoDetailsActivity(p)
        }
        todoAdapter = TodoAdapter(model.todoArray, model.todoListviewThumbnails, todoClickListener)
        binding.listViewFragmentRecyclerView.adapter = todoAdapter
        binding.listViewFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun openTodoDetailsActivity(todoEntity: TodoEntity) {
        val arguments = Bundle()
        todoEntity.id?.let {
            arguments.putLong("todoID", it)
            findNavController().navigate(R.id.action_open_todo, arguments)
        }
    }
}
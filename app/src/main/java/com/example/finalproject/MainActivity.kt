package com.example.finalproject


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels

import com.example.finalproject.databinding.ActivityMainBinding
import com.example.finalproject.room.LocalTodoDB
import com.example.finalproject.room.TodoEntity

var mapWithTodoValues = hashMapOf<TodoEntity, Boolean>()
var selected: ArrayList<TodoEntity> = arrayListOf()

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.deleteButton -> {
            for ((key, value) in mapWithTodoValues) {
                if (value) {
                    key.id?.let { LocalTodoDB.getInstance(this).getTodoDAO().deleteTodoById(it)}
                    selected.add(key)
                }
            }
            for (value in selected) {
                mapWithTodoValues.remove(value)
            }
            selected = arrayListOf()
            System.out.println(mapWithTodoValues)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}
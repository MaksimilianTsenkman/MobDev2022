package com.example.finalproject

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.room.TodoEntity


class TodoAdapter(
    var todos: ArrayList<TodoEntity> = ArrayList(),
    var thumbnails: MutableMap<String, Bitmap?>,
    var listener: TodoClickListener,
):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    fun interface TodoClickListener {
        fun onClick(todo: TodoEntity)
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_todo_overview, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.itemView.apply {
            mapWithTodoValues[todo] = false
            val checkBox = this.findViewById<CheckBox>(R.id.checkbox)
            checkBox.setOnClickListener {
                mapWithTodoValues[todo] = checkBox.isChecked
            }
            this.findViewById<TextView>(R.id.todoTitle).text = todo.title
            setOnClickListener {
                listener.onClick(todo)
            }
        }
    }

    override fun getItemCount(): Int = todos.size

}
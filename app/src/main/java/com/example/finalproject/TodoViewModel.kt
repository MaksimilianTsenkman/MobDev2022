package com.example.finalproject

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import com.example.finalproject.room.LocalTodoDB
import com.example.finalproject.room.TodoEntity
import java.util.*
import kotlin.collections.ArrayList

class TodoViewModel(val app: Application) : AndroidViewModel(app) {

    var todoArray: ArrayList<TodoEntity> = ArrayList()

    val todoListviewThumbnails: MutableMap<String, Bitmap?> = HashMap()

    fun refresh() {
        todoArray = LocalTodoDB.getInstance(app).getTodoDAO().loadTodos().toCollection(ArrayList())
    }

    fun getTodoByID(id: Long): TodoEntity? {
        return todoArray.find {
            it.id == id
        }
    }
}
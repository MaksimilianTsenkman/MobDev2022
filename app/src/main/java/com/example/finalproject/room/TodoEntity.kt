package com.example.finalproject.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Todos")
data class TodoEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = Date().time,
    var title: String? = "Sample Title",
    var author: String = "Sample Author",
    var contents: String? = "Sample Contents",
    var imageName: String?,
    var createdTime: Date? = Date(),
){
    companion object { const val DATEFORMAT = "dd/MM/yyyy" }
}
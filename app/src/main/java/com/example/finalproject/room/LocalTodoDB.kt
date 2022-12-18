package com.example.finalproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.finalproject.roomD.DateTypeConverter

@TypeConverters(DateTypeConverter::class)
@Database(entities = [TodoEntity::class], version = 1)
abstract class LocalTodoDB: RoomDatabase() {
    abstract fun getTodoDAO(): TodoDAO

    companion object {
        private lateinit var TodoDB: LocalTodoDB

        @Synchronized fun getInstance(context: Context): LocalTodoDB {
            if(!this::TodoDB.isInitialized) {
                TodoDB = Room.databaseBuilder(
                    context, LocalTodoDB::class.java, "myTodos")
                    .fallbackToDestructiveMigration() // each time schema changes, data is lost!
                    .allowMainThreadQueries() // if possible, use background thread instead
                    .build()
            }
            return TodoDB
        }
    }
}
package com.example.finalproject.room
import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT title FROM Todos")
    fun loadTodoTitles(): Array<String>

    @Query("SELECT * FROM Todos")
    fun loadTodos(): Array<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertTodos(vararg Todos: TodoEntity)

    @Query("DELETE from Todos")
    fun deleteAllTodos()

    @Query("DELETE FROM Todos WHERE id = :id")
    fun deleteTodoById(id: Long)


}
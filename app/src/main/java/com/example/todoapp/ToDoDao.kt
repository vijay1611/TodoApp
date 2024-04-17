package com.example.todoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {
    @Insert
    fun insert(todo:ToDoModel)

    @Query("select * from todo_table")
    fun getToDoData():List<ToDoModel>
}
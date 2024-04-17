package com.example.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDoModel(var title:String="",var description:String="",@PrimaryKey(autoGenerate = true) val id: Int = 0)

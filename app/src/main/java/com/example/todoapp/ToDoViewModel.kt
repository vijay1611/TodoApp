package com.example.todoapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel:ViewModel() {
    val _todoList=MutableLiveData<List<ToDoModel>>()
    var todoList:LiveData<List<ToDoModel>> =_todoList

    fun getData(context: Context){
        _todoList.value=ToDoDatabase.getInstance(context).toDoDao().getToDoData()
    }
}
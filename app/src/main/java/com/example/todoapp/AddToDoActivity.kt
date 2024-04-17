package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.databinding.ActivityAddToDoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddToDoActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddToDoBinding
    lateinit  var dao:ToDoDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao=ToDoDatabase.getInstance(this).toDoDao()
        binding.btnSubmit.setOnClickListener {
            val title=binding.edtTitle.text.toString()
            val description=binding.edtDiscription.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                dao.insert(ToDoModel(title, description))
            }.invokeOnCompletion {
                runOnUiThread{
                    finish()
                }
            }

        }
    }
}
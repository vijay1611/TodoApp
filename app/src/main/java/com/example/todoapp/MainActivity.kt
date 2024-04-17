package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.jvm.internal.CompletedContinuation.context


class MainActivity() : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ToDoAdapter
    private var data= mutableListOf<ToDoModel>()
    lateinit  var dao:ToDoDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao=ToDoDatabase.getInstance(this).toDoDao()

        adapter=ToDoAdapter(data)
        binding.recycler.adapter=adapter
        val swipe=ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            @SuppressLint("ResourceAsColor")
            private var mBackground: ColorDrawable? = null
            private val backgroundColor = 0
            private var mContext=null
            SwipeToDeleteCallback(Context context) {
                mContext = context
                mBackground = ColorDrawable()
                backgroundColor = Color.parseColor("#b80f0a");
            }
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               // TODO("Not yet implemented")
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
                Snackbar.make(binding.recycler, "Deleted " , Snackbar.LENGTH_LONG)
                    .setAction(

                        "Undo"
                    ) {
                        adapter.addItem()


                    }.show()
            }

        })

        swipe.attachToRecyclerView(binding.recycler)
        binding.btnAdd.setOnClickListener {
            val intent= Intent(this, AddToDoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            data=dao.getToDoData().toMutableList()
            Log.e("***",data.toString())
        }.invokeOnCompletion {
            runOnUiThread{
                adapter=ToDoAdapter(data)
                binding.recycler.adapter=adapter
            }
        }
    }
}
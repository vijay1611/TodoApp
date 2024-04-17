package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding

class ToDoAdapter(val list: MutableList<ToDoModel>): RecyclerView.Adapter<ToDoAdapter.Holder>() {
    var lastData:Pair<Int,ToDoModel>?=null
    inner class Holder(val item: ItemTodoBinding): RecyclerView.ViewHolder(item.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemTodoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.item.itemTitle.text=list[position].title
        holder.item.itemDiscreption.text=list[position].description
    }

    fun removeItem(adapterPosition: Int) {
        lastData= Pair(adapterPosition,list[adapterPosition])
        list.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    fun addItem() {
        lastData?.let {
            list.add(it.first,it.second)
            notifyItemInserted(it.first)
        }
    }
}
package com.example.todolist.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.entity.ItemModel
import com.example.todolist.databinding.ItemRowBinding

class ItemAdapter(private val listener: CheckedBoxListener) :
    RecyclerView.Adapter<ItemViewHolder>() {

    var items: MutableList<ItemModel> = mutableListOf()
    private lateinit var context: Context
    private lateinit var dataBinding: ItemRowBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        dataBinding = ItemRowBinding.inflate(inflater, parent, false)
        return ItemViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position], position, listener)
    }
}
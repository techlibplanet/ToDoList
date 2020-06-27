package com.example.todolist.home

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.entity.ItemModel
import com.example.todolist.databinding.ItemRowBinding
import com.example.todolist.viewmodel.ItemViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class ItemViewHolder(private val dataBinding: ItemRowBinding) :
    RecyclerView.ViewHolder(dataBinding.root), KoinComponent {

    private val viewModel by inject<ItemViewModel>()

    fun bind(
        model: ItemModel,
        position: Int,
        listener: CheckedBoxListener
    ) {
        dataBinding.itemModel = model

        // To make the item greyed out if the value isChecked is true in database
        if (model.isChecked) {
            itemView.alpha = 0.8F
            itemView.setBackgroundColor(Color.GRAY)
            dataBinding.checkbox.isEnabled = false
        } else {
            itemView.alpha = 1.0F
            itemView.setBackgroundColor(Color.WHITE)
        }

        dataBinding.checkbox.setOnClickListener {
            if (model.isChecked) {
                model.isChecked = false
                //viewModel.update(false, model)
                listener.onCheckboxClicked(model, position)
            } else {
                model.isChecked = true
                //viewModel.update(true, model)
                listener.onCheckboxClicked(model, position)
            }
        }
    }
}

interface CheckedBoxListener {
    fun onCheckboxClicked(model: ItemModel, position: Int)
}
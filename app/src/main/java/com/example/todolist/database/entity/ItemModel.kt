package com.example.todolist.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_data")
class ItemModel(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val title: String = "",
    val description: String = "",
    var isChecked: Boolean = false
)
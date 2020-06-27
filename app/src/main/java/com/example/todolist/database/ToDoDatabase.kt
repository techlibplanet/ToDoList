package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.database.dao.ToDoItemDao
import com.example.todolist.database.entity.ItemModel

@Database(entities = [ItemModel::class], version = 1, exportSchema = true)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoItemDao(): ToDoItemDao
}
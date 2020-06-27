package com.example.todolist.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist.database.entity.ItemModel

@Dao
interface ToDoItemDao {

    @Query("SELECT * FROM item_data")
    fun getAll(): MutableList<ItemModel>

    @Insert
    fun insert(vararg unSplash: ItemModel)

    @Query("UPDATE item_data SET isChecked=:isChecked where uid=:uid")
    fun update(isChecked: Boolean, uid: Int)

}
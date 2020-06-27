package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.database.dao.ToDoItemDao
import com.example.todolist.database.entity.ItemModel
import java.util.*

class ItemViewModel(private val itemDao: ToDoItemDao) : ViewModel() {

    private var _saveItemError = MutableLiveData<String>()
    val saveItemError: LiveData<String>
        get() = _saveItemError

    private var _saveItemSuccess = MutableLiveData<String>()
    val saveItemSuccess: LiveData<String>
        get() = _saveItemSuccess

    fun filter(query: String): List<ItemModel> =
        getAllToDoItems().filter {
            it.title.toLowerCase(Locale.getDefault()).startsWith(query)
        }.apply {
            this
        }

    fun saveToDoItem(itemModel: ItemModel) {
        try {
            itemDao.insert(itemModel)
            _saveItemSuccess.value = ""
        } catch (e: Exception) {
            _saveItemError.value = "Error : $e"
        }
    }

    fun getAllToDoItems(): MutableList<ItemModel> {
        return itemDao.getAll()
    }

    fun update(isChecked: Boolean, model: ItemModel) {
        itemDao.update(isChecked = isChecked, uid = model.uid)
    }
}
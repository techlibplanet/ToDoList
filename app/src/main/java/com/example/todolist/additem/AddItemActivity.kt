package com.example.todolist.additem

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import com.example.todolist.R
import com.example.todolist.database.entity.ItemModel
import com.example.todolist.helper.toast
import com.example.todolist.home.MainActivity
import com.example.todolist.viewmodel.ItemViewModel
import org.koin.android.ext.android.inject

class AddItemActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel by inject<ItemViewModel>()
    private lateinit var itemModel: ItemModel
    private val CLICKABLES = intArrayOf(R.id.button_cancel, R.id.button_save)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        itemModel = ItemModel()

        for (id in CLICKABLES) findViewById<AppCompatButton>(id).setOnClickListener(this)

        initLiveData()

    }

    private fun initLiveData() {
        viewModel.apply {
            saveItemError.observe(this@AddItemActivity, Observer {
                toast(it)
            })

            saveItemSuccess.observe(this@AddItemActivity, Observer {
                startActivity(Intent(this@AddItemActivity, MainActivity::class.java))
                finishAffinity()
            })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_cancel -> {
                startMainActivity()
            }

            R.id.button_save -> {
                val title = findViewById<AppCompatEditText>(R.id.title_edit_text).text.toString()
                val desc = findViewById<AppCompatEditText>(R.id.desc_edit_text).text.toString()
                viewModel.saveToDoItem(ItemModel(title = title, description = desc))
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

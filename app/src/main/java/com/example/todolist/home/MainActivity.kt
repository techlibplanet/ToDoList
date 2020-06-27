package com.example.todolist.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.additem.AddItemActivity
import com.example.todolist.database.entity.ItemModel
import com.example.todolist.helper.toast
import com.example.todolist.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), CheckedBoxListener {

    private val viewModel by inject<ItemViewModel>()
    private val itemAdapter: ItemAdapter by lazy { ItemAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item_recycler_view.layoutManager = LinearLayoutManager(this)
        item_recycler_view.setHasFixedSize(true)
        item_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        item_recycler_view.adapter = itemAdapter

        val itemList = viewModel.getAllToDoItems()
        when {
            itemList.isNullOrEmpty() -> {
                toast("Please click on + button to add ToDoItem")
            }
            else -> {
                setItemAdapter(viewModel.getAllToDoItems())
            }
        }

        fab.setOnClickListener {
            startActivity(Intent(this, AddItemActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        val closeButton = searchView.findViewById<ImageView>(R.id.search_close_btn)
        closeButton.setOnClickListener {
            setItemAdapter(viewModel.filter("") as MutableList<ItemModel>)
            if (!searchView.isIconified) {
                searchView.isIconified = true
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                setItemAdapter(viewModel.filter(query) as MutableList<ItemModel>)
                return false
            }
        })
        return true
    }

    private fun setItemAdapter(itemList: MutableList<ItemModel>) {
        itemAdapter.items = itemList
        itemAdapter.notifyDataSetChanged()
    }

    override fun onCheckboxClicked(model: ItemModel, position: Int) {
        itemAdapter.items.removeAt(position)
        itemAdapter.items.add(model)
        itemAdapter.notifyDataSetChanged()
    }
}

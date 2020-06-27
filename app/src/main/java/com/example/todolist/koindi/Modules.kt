package com.example.todolist.koindi

import com.example.todolist.viewmodel.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module(override = true) {

    viewModel {
        ItemViewModel(itemDao = get())
    }
}
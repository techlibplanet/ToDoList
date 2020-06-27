package com.example.todolist

import android.app.Application
import com.example.todolist.koindi.myModule
import com.example.todolist.koindi.roomDatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@ToDoApplication)
            modules(listOf(myModule, roomDatabaseModule))
        }
    }
}
package com.example.todolist.koindi

import androidx.room.Room
import com.example.todolist.database.ToDoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), ToDoDatabase::class.java, "toDoDatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }


    factory {
        get<ToDoDatabase>().toDoItemDao()
    }

}

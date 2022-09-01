package com.example.eldoradotodolist

import android.app.Application
import com.example.eldoradotodolist.db.ProductDatabase
import com.example.eldoradotodolist.repository.ProductRepository

class TodoListApplication : Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { ProductDatabase.getDatabase(this) }
    val repository by lazy { ProductRepository(database.getProductDao()) }
}
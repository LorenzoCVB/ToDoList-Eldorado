package com.example.eldoradotodolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eldoradotodolist.model.ProductModel

@Database(entities = arrayOf(ProductModel::class), version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase(){

    abstract fun getProductDao():ProductDao

    companion object{
        private var INSTANCE:ProductDatabase?=null

        fun getDatabase(context: Context):ProductDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
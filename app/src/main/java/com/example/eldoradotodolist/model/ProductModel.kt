package com.example.eldoradotodolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class ProductModel(
    @ColumnInfo(name = "productName")
    val productName:String,
    @ColumnInfo(name = "productPrice")
    val productPrice:String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
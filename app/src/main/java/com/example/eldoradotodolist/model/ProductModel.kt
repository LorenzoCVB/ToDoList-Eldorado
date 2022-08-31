package com.example.eldoradotodolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class ProductModel(
    @ColumnInfo(name = "productName")
    val productName:String,
    @ColumnInfo(name = "productPrice")
    val productPrice:String,
    @ColumnInfo(name="product_date")
    val product_date:String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
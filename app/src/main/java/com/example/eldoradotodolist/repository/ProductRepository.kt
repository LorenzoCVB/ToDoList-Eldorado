package com.example.eldoradotodolist.repository

import androidx.lifecycle.LiveData
import com.example.eldoradotodolist.db.ProductDao
import com.example.eldoradotodolist.model.ProductModel

class ProductRepository(private val productDao: ProductDao) {

    val getAllProduct:LiveData<List<ProductModel>> = productDao.getAllProducts()

    suspend fun insert(productModel: ProductModel){
        productDao.insert(productModel)
    }

    suspend fun update(productModel: ProductModel){
        productDao.update(productModel)
    }

    suspend fun delete(productModel: ProductModel){
        productDao.delete(productModel)
    }

}
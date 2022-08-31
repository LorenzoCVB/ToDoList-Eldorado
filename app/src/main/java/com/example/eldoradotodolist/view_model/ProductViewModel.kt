package com.example.eldoradotodolist.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.eldoradotodolist.db.ProductDatabase
import com.example.eldoradotodolist.model.ProductModel
import com.example.eldoradotodolist.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {

    val getAllProductList:LiveData<List<ProductModel>>
    private val productRepository:ProductRepository

    init {
        val dao = ProductDatabase.getDatabase(application).getProductDao()
        productRepository = ProductRepository(dao)
        getAllProductList = productRepository.getAllProduct
    }

    fun productInsert(productModel: ProductModel) = viewModelScope.launch {
        productRepository.insert(productModel)
    }

    fun productUpdate(productModel: ProductModel) = viewModelScope.launch {
        productRepository.update(productModel)
    }

    fun deleteProduct(productModel: ProductModel) = viewModelScope.launch {
        productRepository.delete(productModel)
    }

}
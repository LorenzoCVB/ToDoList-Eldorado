package com.example.eldoradotodolist.view_model

import android.app.Application
import androidx.lifecycle.*
import com.example.eldoradotodolist.model.ProductModel
import com.example.eldoradotodolist.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository, application: Application) :
    AndroidViewModel(application) {

    val getAllProductList: LiveData<List<ProductModel>> = productRepository.getAllProduct

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

class ProductViewModelFactory(
    private val repository: ProductRepository,
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
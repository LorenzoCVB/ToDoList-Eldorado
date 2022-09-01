package com.example.eldoradotodolist.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldoradotodolist.R
import com.example.eldoradotodolist.TodoListApplication
import com.example.eldoradotodolist.adapter.ClickInterface
import com.example.eldoradotodolist.adapter.DeleteInterface
import com.example.eldoradotodolist.adapter.ProductAdapter
import com.example.eldoradotodolist.databinding.ActivityMainBinding
import com.example.eldoradotodolist.model.ProductModel
import com.example.eldoradotodolist.view_model.ProductViewModel
import com.example.eldoradotodolist.view_model.ProductViewModelFactory

class MainActivity : AppCompatActivity(), ClickInterface, DeleteInterface {

    private val productViewModel:  ProductViewModel by viewModels {
        ProductViewModelFactory(
            (this.applicationContext as TodoListApplication).repository,
            this.applicationContext as TodoListApplication
        )
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val loading = LoadingDialog(this)
        loading.startLoading()

        val handler = Handler()
        handler.postDelayed({ loading.isDismiss() }, 2000)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val productAdapter = ProductAdapter(this, this/*, this*/)
        binding.recyclerView.adapter = productAdapter


        productViewModel.getAllProductList.observe(this) { list ->
            list?.let {
                productAdapter.updateList(it)
                if(it.isEmpty()){
                    Toast.makeText(this, getString(R.string.EmptyList), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(productModel: ProductModel) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("type", "Edit")
        intent.putExtra("productId", productModel.id)
        intent.putExtra("productName", productModel.productName)
        intent.putExtra("productPrice", productModel.productPrice)
        startActivity(intent)
    }

    override fun onDelete(productModel: ProductModel) {
        productViewModel.deleteProduct(productModel)
        Toast.makeText(this, (getString(R.string.ProdDelSuc)), Toast.LENGTH_SHORT).show()
    }
}
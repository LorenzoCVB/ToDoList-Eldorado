package com.example.eldoradotodolist.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldoradotodolist.R
import com.example.eldoradotodolist.adapter.ClickInterface
import com.example.eldoradotodolist.adapter.DeleteInterface
import com.example.eldoradotodolist.adapter.ProductAdapter
import com.example.eldoradotodolist.model.ProductModel
import com.example.eldoradotodolist.view_model.ProductViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ClickInterface, DeleteInterface {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loading = LoadingDialog(this)
        loading.startLoading()

        val handler = Handler()
        handler.postDelayed({ loading.isDismiss() }, 2000)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val productAdapter = ProductAdapter(this, this/*, this*/)
        recyclerView.adapter = productAdapter


        productViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ProductViewModel::class.java]
        productViewModel.getAllProductList.observe(this) { list ->
            list?.let {
                productAdapter.updateList(it)
                if(it.isEmpty()){
                    Toast.makeText(this, getString(R.string.EmptyList), Toast.LENGTH_SHORT).show()
                }
            }
        }

        floatingActionButton.setOnClickListener {
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
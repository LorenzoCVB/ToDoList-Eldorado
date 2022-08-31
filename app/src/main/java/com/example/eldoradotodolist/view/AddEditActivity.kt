package com.example.eldoradotodolist.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.eldoradotodolist.R
import com.example.eldoradotodolist.model.ProductModel
import com.example.eldoradotodolist.view_model.ProductViewModel
import kotlinx.android.synthetic.main.activity_add_edit.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddEditActivity : AppCompatActivity() {


    private lateinit var inputText: EditText
    private lateinit var saveButton: Button

    private lateinit var productViewModel: ProductViewModel
    private var productId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        inputText = findViewById(R.id.editTitle)
        saveButton = findViewById(R.id.savebutton)


        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatted = current.format(formatter)


        productViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ProductViewModel::class.java]

        val type = intent.getStringExtra("type")
        if (type.equals("Edit")) {
            productId = intent.getIntExtra("productId", -1)
            val productName = intent.getStringExtra("productName")
            val productPrice = intent.getStringExtra("productPrice")

            editTitle.setText(productName)
            Description.setText(productPrice)


            savebutton.text = getString(R.string.update)
            savebutton.setOnClickListener {
                if (TextUtils.isEmpty(inputText.text.toString())) {
                    Toast.makeText(this, (getString(R.string.MustHaveTitle)), Toast.LENGTH_SHORT).show()
                } else {
                    val product = ProductModel(editTitle.text.toString(), Description.text.toString(), formatted)
                    product.id = productId
                    productViewModel.productUpdate(product)
                    Toast.makeText(this, (getString(R.string.ProdUpSuc)), Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        } else {
            savebutton.text = getString(R.string.add)
            savebutton.setOnClickListener {

                if (TextUtils.isEmpty(inputText.text.toString())) {
                    Toast.makeText(this, (getString(R.string.MustHaveTitle)), Toast.LENGTH_SHORT).show()
                } else {
                    productViewModel.productInsert(ProductModel(editTitle.text.toString(), Description.text.toString(), formatted))
                    Toast.makeText(this, (getString(R.string.ProdAddSuc)), Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
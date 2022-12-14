package com.example.eldoradotodolist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.eldoradotodolist.R
import com.example.eldoradotodolist.model.ProductModel
import kotlinx.android.synthetic.main.product_list.view.*

class ProductAdapter(private val clickInterface: ClickInterface, private val deleteInterface: DeleteInterface) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val productList = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.productName.text = productList[position].productName
        holder.productPrice.text = productList[position].productPrice
        holder.productDate.text = productList[position].product_date

        holder.itemView.setOnLongClickListener {
            deleteInterface.onDelete(productList[position])
            if (productList.size == 1) {
                Toast.makeText(holder.itemView.context, R.string.EmptyList, Toast.LENGTH_SHORT)
                    .show()
            }
            return@setOnLongClickListener true
        }

        holder.itemView.setOnClickListener {
            clickInterface.onClick(productList[position])
        }
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.textView
        val productPrice: TextView = itemView.textView2
        val productDate: TextView = itemView.id_date

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(myList: List<ProductModel>) {
        productList.clear()
        productList.addAll(myList)
        notifyDataSetChanged()
    }
}


interface ClickInterface {
    fun onClick(productModel: ProductModel)
}

interface DeleteInterface {
    fun onDelete(productModel: ProductModel)
}

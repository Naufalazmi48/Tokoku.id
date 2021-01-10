package com.example.tokokuid.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokokuid.cart.databinding.ItemCartBinding
import com.example.tokokuid.core.modelpresentation.Item

class CartAdapter : RecyclerView.Adapter<CartAdapter.ListViewHolder>() {

    private var listData = ArrayList<Item>()
    var onItemClick: ((Item) -> Unit)? = null
    var onItemDeleteClick: ((Item) -> Unit)? = null

    fun getData():List<Item> = listData
    fun setData(newListData: List<Item>?) {
        if (newListData.isNullOrEmpty()) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun deleteData(data:Item){
        listData.remove(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCartBinding.bind(itemView)
        fun bind(data: Item) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.url_picture_item)
                    .into(productPicture)
                nameProduct.text = data.name_item
                priceProduct.text = String.format("Rp.%d",data.price_item)
                weightProduct.text = String.format("%d gr",data.weight_item)
                cancelButton.setOnClickListener {
                    onItemDeleteClick?.invoke(listData[adapterPosition])
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}
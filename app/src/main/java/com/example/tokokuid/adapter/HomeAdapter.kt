package com.example.tokokuid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokokuid.R
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.databinding.ItemProductBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {

    private var listData = ArrayList<Item>()
    var onItemClick: ((Item) -> Unit)? = null

    fun setData(newListData: List<Item>?) {
        if (newListData.isNullOrEmpty()) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProductBinding.bind(itemView)
        fun bind(data: Item) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.url_picture_item)
                    .into(productPicture)
                nameProduct.text = data.name_item
                priceProduct.text = "Rp.${data.price_item}"
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
        LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}
package com.example.tokokuid.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tokokuid.cart.CartActivity
import com.example.tokokuid.core.DataDummy
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.databinding.FragmentHomeBinding
import com.example.tokokuid.adapter.HomeAdapter
import com.example.tokokuid.detail.DetailActivity

class HomeFragment : Fragment() {

    private var mAdapter = HomeAdapter() //Change to KOIN
    private val data = DataDummy.getClothes().reversed()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mAdapter.setData(data)
        newItem(data.first())
        mAdapter.onItemClick = {
            startActivity(Intent(context, DetailActivity::class.java).putExtra(DetailActivity.EXTRA_DATA,it))
        }
        with(binding.rvProduct) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapter
        }
        binding.cart.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }
        binding.searchtext.addTextChangedListener {
            if(it.isNullOrEmpty()){
                mAdapter.setData(data)
            }
        }
        binding.searchButton.setOnClickListener {
            if(!binding.searchtext.text.isNullOrEmpty()){
                val result = ArrayList<Item>()
                for (item in data){
                    val name = item.name_item
                    if((name != null) && name.contains(binding.searchtext.text.toString(),true)){
                        result.add(item)
                    }
                }
                if(!result.isNullOrEmpty()){
                    mAdapter.setData(result)
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun newItem(item: Item){
        binding.newNameProduct.text = item.name_item
        Glide.with(this)
            .load(item.url_picture_item)
            .into(binding.newProductPicture)
    }
}
package com.example.tokokuid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tokokuid.adapter.HomeAdapter
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.databinding.ActivityMainBinding
import com.example.tokokuid.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: HomeAdapter
    private val mainViewModel:MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mAdapter = HomeAdapter()
        mAdapter.setData(mainViewModel.data)
        newItem(mainViewModel.data.first())
        mAdapter.onItemClick = {
            startActivity(
                Intent(
                    this,
                    DetailActivity::class.java
                ).putExtra(DetailActivity.EXTRA_DATA, it)
            )
        }
        with(binding.rvProduct) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapter
        }
        binding.cart.setOnClickListener {
            val uri = Uri.parse("tokokuid://cart")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        binding.searchtext.addTextChangedListener {
            if (it.isNullOrEmpty()) {
                mainViewModel.clearData()
                mAdapter.setData(mainViewModel.data)
                showAnimation(false)
            }
        }
        binding.searchButton.setOnClickListener {
            if (!binding.searchtext.text.isNullOrEmpty()) {
                val result = ArrayList<Item>()
                for (item in mainViewModel.data) {
                    val name = item.name_item
                    if (name.contains(binding.searchtext.text.toString(), true)) {
                        result.add(item)
                    }
                }
                if (!result.isNullOrEmpty()) {
                    mainViewModel.data = result
                    mAdapter.setData(mainViewModel.data)
                    showAnimation(false)
                } else {
                    showAnimation(true)
                }
            }
        }
    }

    private fun newItem(item: Item) {
        binding.newNameProduct.text = item.name_item
        Glide.with(this)
            .load(item.url_picture_item)
            .into(binding.newProductPicture)
    }

    private fun showAnimation(boolean: Boolean){
        if(boolean){
            binding.animationNotFound.visibility = View.VISIBLE
            binding.animationNotFound.playAnimation()
            binding.rvProduct.visibility = View.INVISIBLE
            binding.textNotFound.visibility = View.VISIBLE
        }else{
            binding.animationNotFound.visibility = View.GONE
            binding.animationNotFound.clearAnimation()
            binding.rvProduct.visibility = View.VISIBLE
            binding.textNotFound.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        binding.animationNotFound.pauseAnimation()
    }

    override fun onResume() {
        super.onResume()
        binding.animationNotFound.resumeAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.animationNotFound.clearAnimation()
    }
}
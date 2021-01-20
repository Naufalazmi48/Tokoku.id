package com.example.tokokuid.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tokokuid.R
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private  var detailItem: Item? = null
    private val detailViewModel:DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailItem = intent.getParcelableExtra(
            EXTRA_DATA
        )
        showDetailItem(detailItem)
        binding.addCart.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

    private fun showDetailItem(item: Item?) {
        item?.let {
            binding.nameProduct.text = it.name_item
            binding.priceProduct.text = String.format("Rp.%d",it.price_item)
            binding.weightProduct.text = String.format("%d gr",it.weight_item)
            binding.descriptionProduct.text = it.description
            Glide.with(this)
                .load(it.url_picture_item)
                .into(binding.productPicture)
        }
    }

    override fun onClick(v: View) {
        if(v.id == R.id.add_cart){
            val item = detailItem
            if(item != null){
                detailViewModel.insertCart(item)
                Toast.makeText(this,"Barang berhasil dimasukkan ke keranjang",Toast.LENGTH_LONG).show()
            }
        }
    }
}
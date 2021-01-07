package com.example.tokokuid.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.tokokuid.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val detailItem = intent.getParcelableExtra<com.example.tokokuid.core.modelpresentation.Item>(
            EXTRA_DATA
        )
        showDetailItem(detailItem)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun showDetailItem(item: com.example.tokokuid.core.modelpresentation.Item?) {
        item?.let {
            binding.nameProduct.text = it.name_item
            binding.priceProduct.text = "Rp.${it.price_item}"
            binding.weightProduct.text = "${it.weight_item} gr"
            binding.descriptionProduct.text = it.description
            Glide.with(this)
                .load(it.url_picture_item)
                .into(binding.productPicture)
        }
    }
}
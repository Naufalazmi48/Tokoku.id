package com.example.tokokuid.cart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokokuid.R
import com.example.tokokuid.adapter.CartAdapter
import com.example.tokokuid.core.DataDummy
import com.example.tokokuid.core.modelpresentation.Courier
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.core.modelpresentation.Province
import com.example.tokokuid.core.modelpresentation.TypeSend
import com.example.tokokuid.databinding.ActivityCartBinding
import com.example.tokokuid.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class CartActivity : AppCompatActivity(), View.OnClickListener {

    private val cartViewModel: CartViewModel by viewModel()
    private lateinit var binding: ActivityCartBinding
    private lateinit var mAdapter: CartAdapter
    private var listTypeSend: ArrayList<TypeSend>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        mAdapter = CartAdapter()
        mAdapter.onItemClick = {
            startActivity(
                Intent(
                    this,
                    DetailActivity::class.java
                ).putExtra(DetailActivity.EXTRA_DATA, it)
            )
        }
        mAdapter.onItemDeleteClick = {
            showAlertDialog(it)
        }
        binding.rvCart.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvCart.adapter = mAdapter

        cartViewModel.itemInCart.observe(this, {
            if (!it.isNullOrEmpty()) {
                mAdapter.setData(it)
                binding.rvCart.visibility = View.VISIBLE
                binding.nullCart.visibility = View.GONE
                binding.buyNow.isEnabled = true
                binding.location.isEnabled = true
                binding.courierProduct.isEnabled = true
                binding.typeSendProduct.isEnabled = true
                countWeight(it)
            }
        })

    }

    private fun showAlertDialog(it: Item) {
        AlertDialog.Builder(this)
            .setTitle("Hapus")
            .setMessage("Anda yakin ingin menghapus barang ini dari keranjang?")
            .setPositiveButton("YA") { dialog, _ ->
                cartViewModel.deleteFromCart(it)
                dialog.dismiss()
                Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                mAdapter.deleteData(it)
                if (mAdapter.getData().isNullOrEmpty()) {
                    reset()
                } else {
                    countWeight(mAdapter.getData())
                }
            }
            .setNegativeButton("TIDAK") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.back -> finish()
            R.id.buy_now -> Toast.makeText(this, "Fitur ini belum tersedia", Toast.LENGTH_LONG)
                .show()
            R.id.location -> {
                val dummy = DataDummy.getProvince()
                selectorDialog(getString(R.string.pilih_provinsi), dummy).observe(this, {
                    binding.location.text = dummy[it].name_province
                })
            }
            R.id.courier_product -> {
                val dummy = DataDummy.getCourier()
                selectorDialog(getString(R.string.pilih_kurir), dummy).observe(this, {
                    resetTypeSend()
                    binding.courierProduct.text = dummy[it].courier
                    listTypeSend = dummy[it].typeSend
                })
            }
            R.id.type_send_product -> {
                val list = listTypeSend
                if (!list.isNullOrEmpty()) {
                    selectorDialog(getString(R.string.pilih_tipe), list).observe(this, {
                        binding.typeSendProduct.text = list[it].type
                        binding.priceSend.text = "Rp.${list[it].price}"
                    })
                }
            }
        }
    }

    private fun resetTypeSend() {
        listTypeSend?.clear()
        binding.typeSendProduct.text = getText(R.string.pilih)
        binding.priceSend.text = ""
    }

    private fun selectorDialog(title: String, list: ArrayList<*>): LiveData<Int> {
        val selectedPosition = MutableLiveData<Int>()
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setNegativeButton("BATAL") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("OKE") { dialog, _ ->
                selectedPosition.postValue((dialog as AlertDialog).listView.checkedItemPosition)
                dialog.dismiss()
            }
        when (list.firstOrNull()) {
            is Province -> {
                val cs: Array<CharSequence> =
                    (list as ArrayList<Province>).map { it -> it.name_province as CharSequence }
                        .toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
            is Courier -> {
                val cs: Array<CharSequence> =
                    (list as ArrayList<Courier>).map { it -> it.courier as CharSequence }
                        .toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
            is TypeSend -> {
                val cs: Array<CharSequence> =
                    (list as ArrayList<TypeSend>).map { it -> it.type as CharSequence }
                        .toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
        }
        dialog.show()
        return selectedPosition
    }

    private fun countWeight(value: List<Item>) {
        var weight = 0.0
        for (item in value) {
            weight += item.weight_item
        }
        if (weight / 1000 >= 1) {
            binding.weightProduct.text = String.format("%.2f Kg", weight / 1000)
        } else {
            binding.weightProduct.text = "$weight gr"
        }
    }

    private fun reset() {
        binding.weightProduct.text = ""
        binding.priceSend.text = ""
        binding.totalPrice.text = ""
        binding.typeSendProduct.text = getString(R.string.pilih)
        binding.location.text = getString(R.string.pilih)
        binding.courierProduct.text = getString(R.string.pilih)

        binding.buyNow.isEnabled = false
        binding.location.isEnabled = false
        binding.courierProduct.isEnabled = false
        binding.typeSendProduct.isEnabled = false

        binding.rvCart.visibility = View.INVISIBLE
        binding.nullCart.visibility = View.VISIBLE
    }
}
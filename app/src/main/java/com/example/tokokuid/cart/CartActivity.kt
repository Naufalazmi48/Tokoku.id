package com.example.tokokuid.cart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokokuid.R
import com.example.tokokuid.core.DataDummy
import com.example.tokokuid.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCartBinding
    private var listTypeSend: ArrayList<com.example.tokokuid.core.modelpresentation.TypeSend>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.rvCart.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.back.setOnClickListener(this)
        binding.buyNow.setOnClickListener(this)
        binding.location.setOnClickListener(this)
        binding.courierProduct.setOnClickListener(this)
        binding.typeSendProduct.setOnClickListener(this)
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
            is com.example.tokokuid.core.modelpresentation.Province -> {
                val cs: Array<CharSequence> =
                    (list as ArrayList<com.example.tokokuid.core.modelpresentation.Province>).map { it -> it.name_province as CharSequence }.toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
            is com.example.tokokuid.core.modelpresentation.Courier -> {
                val cs: Array<CharSequence> =
                    (list as  ArrayList<com.example.tokokuid.core.modelpresentation.Courier>).map { it -> it.courier as CharSequence }.toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
            is com.example.tokokuid.core.modelpresentation.TypeSend -> {
                val cs: Array<CharSequence> =
                    (list as ArrayList<com.example.tokokuid.core.modelpresentation.TypeSend>).map { it -> it.type as CharSequence }.toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
        }
        dialog.show()
        return selectedPosition
    }
}
package com.example.tokokuid

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokokuid.databinding.ActivityCartBinding
import com.example.tokokuid.model.Courier
import com.example.tokokuid.model.Province
import com.example.tokokuid.model.TypeSend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCartBinding
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
        var listTypeSend: ArrayList<TypeSend>? = null
        when (v.id) {
            R.id.back -> finish()
            R.id.buy_now -> Toast.makeText(this, "Fitur ini belum tersedia", Toast.LENGTH_LONG)
                .show()
            R.id.location -> {
                val dummy = DataDummy.getProvince()
                val selector = selectorDialog(getString(R.string.pilih_provinsi), dummy)
                binding.location.text = dummy[selector].name_province
            }
            R.id.courier_product -> {
                val dummy = DataDummy.getCourier()
                val selector = selectorDialog(getString(R.string.pilih_kurir), dummy)
                binding.courierProduct.text = dummy[selector].courier
                listTypeSend = dummy[selector].typeSend
            }
            R.id.type_send_product -> {
                if (listTypeSend != null) {
                    val selector = selectorDialog(getString(R.string.pilih_tipe), listTypeSend)
                    binding.typeSendProduct.text = listTypeSend[selector].type
                    binding.priceSend.text = "Rp.${listTypeSend[selector].price}"
                }
            }
        }
    }

    private fun selectorDialog(title: String, list: ArrayList<*>): Int {
        var selectedPosition = 0
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setNegativeButton("BATAL") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("OKE") { dialog, _ ->
                selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                dialog.dismiss()
            }
        when (list.firstOrNull()) {
            is Province -> {
                val listProvince = list as ArrayList<Province>
                val cs: Array<CharSequence> =
                    list.map { it -> it.name_province as CharSequence }.toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
            is Courier -> {
                val listCourier = list as ArrayList<Courier>
                val cs: Array<CharSequence> =
                    listCourier.map { it -> it.courier as CharSequence }.toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
            is TypeSend -> {
                val listTypeSend = list as ArrayList<TypeSend>
                val cs: Array<CharSequence> =
                    listTypeSend.map { it -> it.type as CharSequence }.toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
        }
        dialog.show()
        return selectedPosition
    }
}
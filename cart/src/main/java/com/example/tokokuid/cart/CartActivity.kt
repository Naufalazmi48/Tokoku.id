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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokokuid.cart.databinding.ActivityCartBinding
import com.example.tokokuid.core.CompanyDetail
import com.example.tokokuid.core.DataDummy
import com.example.tokokuid.core.data.Resource
import com.example.tokokuid.core.modelpresentation.Courier
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.core.modelpresentation.City
import com.example.tokokuid.core.modelpresentation.TypeSend
import com.example.tokokuid.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class CartActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCartBinding
    private lateinit var mAdapter: CartAdapter
    private lateinit var courier: Courier

    private val cartViewModel: CartViewModel by viewModel()
    private var listTypeSend = ArrayList<TypeSend>()

    private val cartObserver = Observer<List<Item>> {
        if (!it.isNullOrEmpty()) {
            mAdapter.setData(it)
            binding.rvCart.visibility = View.VISIBLE
            binding.nullCart.visibility = View.GONE
            binding.location.isEnabled = true
            countWeight(true)
        }
    }
    private val cityObserver = Observer<Resource<List<City>>> {
        when (it) {
            is Resource.Success -> {
                val list = it.data
                if (list != null) {
                    selectorDialog(getString(R.string.pilih_provinsi), SelectorCode.City, list)
                }
                binding.loading.visibility = View.GONE
            }
            is Resource.Loading -> binding.loading.visibility = View.VISIBLE
            is Resource.Error -> {
                Toast.makeText(
                    this,
                    "Terjadi Kendala, Silahkan ulangi",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("CartActivity", it.message.toString())
                binding.loading.visibility = View.GONE
            }
        }
    }

    private val selectorDialogObserver = Observer<Map<SelectorCode, Int>> {
        when {
            it.containsKey(SelectorCode.City) -> {
                val selected = it.getValue(SelectorCode.City)
                resetCourier(true)
                val data = cartViewModel.listCity.value?.data
                if (!data.isNullOrEmpty()) {
                    cartViewModel.citySelected =
                        City(data[selected].name_city, data[selected].code_city)
                    binding.location.text = cartViewModel.citySelected?.name_city
                    binding.courierProduct.isEnabled = true
                }
            }
            it.containsKey(SelectorCode.Courier) -> {
                val selected = it.getValue(SelectorCode.Courier)
                binding.loading.visibility = View.VISIBLE
                binding.courierProduct.text = cartViewModel.dummyCourier[selected].courier
                courier = cartViewModel.dummyCourier[selected]
                binding.loading.visibility = View.GONE
                binding.typeSendProduct.isEnabled = true
            }
            it.containsKey(SelectorCode.TypeSend) -> {
                val selected = it.getValue(SelectorCode.TypeSend)
                if (!listTypeSend.isNullOrEmpty()) {
                    binding.typeSendProduct.text = listTypeSend[selected].type
                    val show = "Rp.${listTypeSend[selected].price}"
                    binding.priceSend.text = show
                    countTotal(listTypeSend[selected].price)
                    binding.buyNow.isEnabled = true
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(cartModule)
        supportActionBar?.hide()
        mAdapter = CartAdapter()
        with(mAdapter) {
            this.onItemClick = {
                startActivity(
                    Intent(
                        this@CartActivity,
                        DetailActivity::class.java
                    ).putExtra(DetailActivity.EXTRA_DATA, it)
                )
            }
            this.onItemDeleteClick = {
                showAlertDialog(it)
            }

        }

        with(binding.rvCart) {
            this.layoutManager = LinearLayoutManager(
                this@CartActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            this.adapter = mAdapter
        }

        cartViewModel.itemInCart.observe(this, cartObserver)

        cartViewModel.listCity.observe(this, cityObserver)

        cartViewModel.selector.observe(this, selectorDialogObserver)

    }

    private fun showAlertDialog(it: Item) {
        AlertDialog.Builder(this)
            .setTitle("Hapus")
            .setMessage("Anda yakin ingin menghapus barang ini dari keranjang?")
            .setPositiveButton("YA") { dialog, _ ->
                cartViewModel.deleteFromCart(it)
                mAdapter.deleteData(it)
                resetCourier(true)
                Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                dialog.dismiss()
                if (mAdapter.getData().isNullOrEmpty()) {
                    resetAll()
                } else {
                    countWeight(true)
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
            R.id.location -> cartViewModel.getListCity()
            R.id.courier_product -> {
                selectorDialog(
                    getString(R.string.pilih_kurir),
                    SelectorCode.Courier,
                    cartViewModel.dummyCourier
                )
            }

//                    cartViewModel.getCost(
//                        CompanyDetail.CodeLocation,
//                        city.code_city,
//                        countWeight(false),
//                        dummy[it].codeCourier
//                    )
//                        .observe(this, { data ->
//                            when (data) {
//                                is Resource.Success -> {
//                                    resetCourier(false)
//                                    listTypeSend = data.data
//                                    binding.loading.visibility = View.GONE
//                                    binding.typeSendProduct.isEnabled = true
//                                }
//                                is Resource.Loading -> binding.loading.visibility = View.VISIBLE
//                                is Resource.Error -> {
//                                    Log.d("CartActivity", data.message.toString())
//                                    binding.loading.visibility = View.GONE
//                                }
//                            }
//                        })
//                })

            R.id.type_send_product -> {
                if (!listTypeSend.isNullOrEmpty()) {
                    selectorDialog(
                        getString(R.string.pilih_tipe),
                        SelectorCode.TypeSend,
                        listTypeSend
                    )
                }
            }
        }
    }

    private fun selectorDialog(title: String, typeSelector: SelectorCode, list: List<*>) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setNegativeButton("BATAL") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("OKE") { dialog, _ ->
                cartViewModel._selector.postValue(mapOf(typeSelector to (dialog as AlertDialog).listView.checkedItemPosition))
                dialog.dismiss()
            }
        when (list.firstOrNull()) {
            is City -> {
                val cs: Array<CharSequence> =
                    (list as List<City>).map { it.name_city }
                        .toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
            is Courier -> {
                val cs: Array<CharSequence> =
                    (list as List<Courier>).map { it.courier }
                        .toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
            is TypeSend -> {
                val cs: Array<CharSequence> =
                    (list as List<TypeSend>).map { it.type as CharSequence }
                        .toTypedArray()
                dialog.setSingleChoiceItems(cs, 0, null)
            }
        }
        dialog.show()
    }

    private fun countWeight(display: Boolean): Int {
        if (display) {
            var weight = 0.0
            for (item in mAdapter.getData()) {
                weight += item.weight_item
            }
            if (weight / 1000 >= 1) {
                binding.weightProduct.text = String.format("%.2f Kg", weight / 1000)
            } else {
                val show = "$weight gr"
                binding.weightProduct.text = show
            }
            return 0
        } else {
            var weight = 0
            for (item in mAdapter.getData()) {
                weight += item.weight_item
            }
            return weight
        }
    }

    private fun resetAll() {
        binding.priceSend.text = ""
        binding.totalPrice.text = ""
        binding.typeSendProduct.text = getString(R.string.pilih)
        binding.courierProduct.text = getString(R.string.pilih)
        binding.weightProduct.text = ""

        binding.location.text = getString(R.string.pilih)

        binding.buyNow.isEnabled = false
        binding.location.isEnabled = false
        binding.courierProduct.isEnabled = false
        binding.typeSendProduct.isEnabled = false

        binding.rvCart.visibility = View.INVISIBLE
        binding.nullCart.visibility = View.VISIBLE

    }

    private fun resetCourier(boolean: Boolean) {
        binding.priceSend.text = ""
        binding.totalPrice.text = ""
        binding.typeSendProduct.text = getString(R.string.pilih)
        if (boolean) {
            binding.courierProduct.text = getString(R.string.pilih)
        }
    }

    private fun countTotal(ongkir: Int?) {
        if (ongkir != null) {
            var total = 0
            mAdapter.getData().map {
                total += it.price_item
            }
            total += ongkir
            val show = "Rp.$total"
            binding.totalPrice.text = show
        }
    }
}
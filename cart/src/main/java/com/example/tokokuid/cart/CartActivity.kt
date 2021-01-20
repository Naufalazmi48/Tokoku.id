package com.example.tokokuid.cart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokokuid.cart.databinding.ActivityCartBinding
import com.example.tokokuid.core.CompanyDetail
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
    private val cartViewModel: CartViewModel by viewModel()

    private val cartObserver = Observer<List<Item>> {
        if (!it.isNullOrEmpty()) {
            mAdapter.setData(it)
            binding.rvCart.visibility = View.VISIBLE
            binding.nullCart.visibility = View.GONE
            binding.location.isEnabled = true
            countWeight(true)
            updateUI()
        }
    }
    private val cityObserver = Observer<Resource<List<City>>> {
        when (it) {
            is Resource.Success -> binding.loading.visibility = View.GONE
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
                val courier = cartViewModel.dummyCourier[selected]
                val city = cartViewModel.citySelected
                if (city != null) {
                    resetCourier(false)
                    cartViewModel.getCost(
                        CompanyDetail.CodeLocation,
                        city.code_city,
                        countWeight(false),
                        courier.codeCourier
                    )
                }
                binding.courierProduct.text = courier.courier
                cartViewModel.courierSelected = courier
            }
            it.containsKey(SelectorCode.TypeSend) -> {
                val selected = it.getValue(SelectorCode.TypeSend)
                val data = cartViewModel.listTypeSend.value?.data
                if (!data.isNullOrEmpty()) {
                    cartViewModel.typeSendSelected = data[selected]
                    binding.typeSendProduct.text = data[selected].type
                    val show = "Rp.${data[selected].price}"
                    binding.priceSend.text = show
                    countTotal(data[selected].price)
                    binding.buyNow.isEnabled = true
                }
            }
        }
    }
    private val typeSendObserver = Observer<Resource<List<TypeSend>>> { data ->
        when (data) {
            is Resource.Success -> {
                binding.loading.visibility = View.GONE
                binding.typeSendProduct.isEnabled = true
            }
            is Resource.Loading -> binding.loading.visibility = View.VISIBLE
            is Resource.Error -> {
                Log.d("CartActivity", data.message.toString())
                binding.loading.visibility = View.GONE
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

        cartViewModel.getListCity()
        cartViewModel.itemInCart.observe(this, cartObserver)

        cartViewModel.listCity.observe(this, cityObserver)

        cartViewModel.selector.observe(this, selectorDialogObserver)

        cartViewModel.listTypeSend.observe(this, typeSendObserver)
    }

    private fun updateUI() {
        val city = cartViewModel.citySelected
        val courier = cartViewModel.courierSelected
        val typeSend = cartViewModel.typeSendSelected

        if (city != null) {
            binding.location.text = city.name_city
            binding.courierProduct.isEnabled = true
        }

        if (courier != null) {
            binding.courierProduct.text = courier.courier
            binding.typeSendProduct.isEnabled = true
        }

        if(typeSend != null){
            binding.typeSendProduct.text = typeSend.type
            countTotal(typeSend.price)
            binding.buyNow.isEnabled = true
        }
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
        finishAfterTransition()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.back -> finishAfterTransition()
            R.id.buy_now -> Toast.makeText(this, "Fitur ini belum tersedia", Toast.LENGTH_LONG)
                .show()
            R.id.location -> {
                val data = cartViewModel.listCity.value?.data
                if (data != null) {
                    selectorDialog(getString(R.string.pilih_provinsi), SelectorCode.City, data)
                }
            }
            R.id.courier_product -> {
                selectorDialog(
                    getString(R.string.pilih_kurir),
                    SelectorCode.Courier,
                    cartViewModel.dummyCourier
                )
            }

            R.id.type_send_product -> {
                val data = cartViewModel.listTypeSend.value?.data
                if (!data.isNullOrEmpty()) {
                    selectorDialog(
                        getString(R.string.pilih_tipe),
                        SelectorCode.TypeSend,
                        data
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
                cartViewModel.selector.postValue(mapOf(typeSelector to (dialog as AlertDialog).listView.checkedItemPosition))
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
            cartViewModel.courierSelected = null
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
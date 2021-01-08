package com.example.tokokuid.core.data.source.local

import com.example.tokokuid.core.data.source.local.entity.CartEntity
import com.example.tokokuid.core.data.source.local.room.TokoDAO
import kotlinx.coroutines.flow.Flow


class LocalDataSource(private val tokoDAO: TokoDAO) {

    fun getAllInCart(): Flow<List<CartEntity>> = tokoDAO.getAllInCart()

    suspend fun insertCart(cart: CartEntity) = tokoDAO.insertCart(cart)

    suspend fun deleteFromCart(cart: CartEntity) = tokoDAO.deleteFromCart(cart.item_id)
}
package com.example.tokokuid.core.domain

import com.example.tokokuid.core.domain.model.CartDomain
import kotlinx.coroutines.flow.Flow

interface ITokoRepository {
    fun getAllInCart(): Flow<List<CartDomain>>
    suspend fun insertCart(cart: CartDomain)
    suspend fun deleteFromCart(cart:CartDomain)
}
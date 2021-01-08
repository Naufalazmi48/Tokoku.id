package com.example.tokokuid.core.domain.usecase

import com.example.tokokuid.core.domain.model.CartDomain
import kotlinx.coroutines.flow.Flow

interface TokoUseCase {
    fun getAllInCart(): Flow<List<CartDomain>>
    suspend fun insertCart(cart: CartDomain)
    suspend fun deleteFromCart(cart: CartDomain)
}
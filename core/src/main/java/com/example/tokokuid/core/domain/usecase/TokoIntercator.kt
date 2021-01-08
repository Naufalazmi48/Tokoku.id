package com.example.tokokuid.core.domain.usecase

import com.example.tokokuid.core.domain.ITokoRepository
import com.example.tokokuid.core.domain.model.CartDomain
import kotlinx.coroutines.flow.Flow

class TokoIntercator(private val tokoRepository: ITokoRepository):TokoUseCase {
    override fun getAllInCart(): Flow<List<CartDomain>> = tokoRepository.getAllInCart()

    override suspend fun insertCart(cart: CartDomain) = tokoRepository.insertCart(cart)

    override suspend fun deleteFromCart(cart: CartDomain) = tokoRepository.deleteFromCart(cart)

}
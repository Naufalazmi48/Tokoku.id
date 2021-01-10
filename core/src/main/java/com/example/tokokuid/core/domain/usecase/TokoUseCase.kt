package com.example.tokokuid.core.domain.usecase

import com.example.tokokuid.core.data.Resource
import com.example.tokokuid.core.domain.model.CartDomain
import com.example.tokokuid.core.domain.model.CityDomain
import com.example.tokokuid.core.domain.model.CostDomain
import kotlinx.coroutines.flow.Flow

interface TokoUseCase {
    fun getAllInCart(): Flow<List<CartDomain>>
    suspend fun insertCart(cart: CartDomain)
    suspend fun deleteFromCart(cart: CartDomain)
    fun getListCity(): Flow<Resource<List<CityDomain>>>
     fun getCost(
        originId: String,
        destinationId: String,
        weightItem: Int,
        courier: String
    ): Flow<Resource<List<CostDomain>>>
}
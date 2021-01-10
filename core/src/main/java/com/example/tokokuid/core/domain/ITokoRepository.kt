package com.example.tokokuid.core.domain

import com.example.tokokuid.core.data.Resource
import com.example.tokokuid.core.data.source.remote.network.ApiResponse
import com.example.tokokuid.core.domain.model.CartDomain
import com.example.tokokuid.core.domain.model.CityDomain
import com.example.tokokuid.core.domain.model.CostDomain
import kotlinx.coroutines.flow.Flow

interface ITokoRepository {
    fun getAllInCart(): Flow<List<CartDomain>>
    suspend fun insertCart(cart: CartDomain)
    suspend fun deleteFromCart(cart: CartDomain)
    fun getListCity(): Flow<Resource<List<CityDomain>>>
    suspend fun getCost(
        originId: String,
        destinationId: String,
        weightItem: Int,
        courier: String
    ): Flow<Resource<CostDomain>>
}
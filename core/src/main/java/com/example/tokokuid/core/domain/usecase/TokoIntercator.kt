package com.example.tokokuid.core.domain.usecase

import com.example.tokokuid.core.data.Resource
import com.example.tokokuid.core.domain.ITokoRepository
import com.example.tokokuid.core.domain.model.CartDomain
import com.example.tokokuid.core.domain.model.CityDomain
import com.example.tokokuid.core.domain.model.CostDomain
import com.example.tokokuid.core.modelpresentation.City
import kotlinx.coroutines.flow.Flow

class TokoIntercator(private val tokoRepository: ITokoRepository) : TokoUseCase {
    override fun getAllInCart(): Flow<List<CartDomain>> = tokoRepository.getAllInCart()

    override suspend fun insertCart(cart: CartDomain) = tokoRepository.insertCart(cart)

    override suspend fun deleteFromCart(cart: CartDomain) = tokoRepository.deleteFromCart(cart)
    override fun getListCity(): Flow<Resource<List<CityDomain>>> = tokoRepository.getListCity()
    override suspend fun getCost(
        originId: String,
        destinationId: String,
        weightItem: Int,
        courier: String
    ): Flow<Resource<CostDomain>> = tokoRepository.getCost(originId,destinationId,weightItem,courier)

}
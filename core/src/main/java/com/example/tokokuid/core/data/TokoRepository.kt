package com.example.tokokuid.core.data

import com.example.tokokuid.core.data.source.local.LocalDataSource
import com.example.tokokuid.core.domain.ITokoRepository
import com.example.tokokuid.core.domain.model.CartDomain
import com.example.tokokuid.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.coroutineContext

class TokoRepository(private val localDataSource: LocalDataSource) : ITokoRepository {

    override fun getAllInCart(): Flow<List<CartDomain>> {
        return localDataSource.getAllInCart().map {
            DataMapper.mapEntitiesToDomainCart(it)
        }
    }

    override suspend fun insertCart(cart: CartDomain) {
        val entities = DataMapper.mapDomainToEntitiesCart(cart)

        localDataSource.insertCart(entities)
    }

    override suspend fun deleteFromCart(cart: CartDomain) {
        val entities = DataMapper.mapDomainToEntitiesCart(cart)
        localDataSource.deleteFromCart(entities)
    }
}
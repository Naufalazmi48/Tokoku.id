package com.example.tokokuid.core.data

import com.example.tokokuid.core.data.source.local.LocalDataSource
import com.example.tokokuid.core.data.source.remote.RemoteDataSource
import com.example.tokokuid.core.data.source.remote.network.ApiResponse
import com.example.tokokuid.core.data.source.remote.response.CityResponse
import com.example.tokokuid.core.domain.ITokoRepository
import com.example.tokokuid.core.domain.model.CartDomain
import com.example.tokokuid.core.domain.model.CityDomain
import com.example.tokokuid.core.domain.model.CostDomain
import com.example.tokokuid.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokoRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ITokoRepository {

    override fun getAllInCart(): Flow<List<CartDomain>> {
        return localDataSource.getAllInCart().map {
            DataMapper.mapItemEntitiesToDomainCart(it)
        }
    }

    override suspend fun insertCart(cart: CartDomain) {
        val entities = DataMapper.mapItemDomainToEntitiesCart(cart)

        localDataSource.insertCart(entities)
    }

    override suspend fun deleteFromCart(cart: CartDomain) {
        val entities = DataMapper.mapItemDomainToEntitiesCart(cart)
        localDataSource.deleteFromCart(entities)
    }

    override fun getListCity(): Flow<Resource<List<CityDomain>>> =
        object : NetworkBoundResource<List<CityDomain>, CityResponse>() {
            override fun loadFromDB(): Flow<List<CityDomain>> {
                return localDataSource.getCity().map {
                    DataMapper.mapCityEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<CityDomain>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<CityResponse>> =
                remoteDataSource.getListCity()

            override suspend fun saveCallResult(data: CityResponse) {
                val cityList = DataMapper.mapCityResponseToEntities(data)
                localDataSource.insertCity(cityList)
            }

        }.asFlow()

    override  fun getCost(
        originId: String,
        destinationId: String,
        weightItem: Int,
        courier: String
    ): Flow<Resource<List<CostDomain>>> =
        remoteDataSource.getCost(originId, destinationId, weightItem, courier).map {
            when(it){
                is ApiResponse.Success -> Resource.Success(DataMapper.mapCostResponseToDomain(it.data))
                is ApiResponse.Error -> Resource.Error(it.errorMessage)
                is ApiResponse.Empty -> Resource.Loading()
            }

    }


}
package com.example.tokokuid.cart

import androidx.lifecycle.*
import com.example.tokokuid.core.data.Resource
import com.example.tokokuid.core.domain.usecase.TokoUseCase
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.core.modelpresentation.TypeSend
import com.example.tokokuid.core.utils.DataMapper
import kotlinx.coroutines.launch

class CartViewModel(private val useCase: TokoUseCase) : ViewModel() {

    val itemInCart
        get() = useCase.getAllInCart().asLiveData().map {
            DataMapper.mapItemDomainToPresentation(it)
        }

    fun deleteFromCart(item: Item) = viewModelScope.launch {
        useCase.deleteFromCart(DataMapper.mapItemPresentationToDomain(item))
    }

    val getListCity
        get() = useCase.getListCity().asLiveData().map {
            when (it) {
                is Resource.Success -> {
                    Resource.Success(DataMapper.mapCityDomainToPresentation(it.data))
                }
                is Resource.Loading -> Resource.Loading()
                is Resource.Error -> Resource.Error(it.message.toString())
            }
        }

    fun getCost(
        originId: String,
        destinationId: String,
        weightItem: Int,
        courier: String
    ):LiveData<Resource<List<TypeSend>?>> =
        useCase.getCost(originId, destinationId, weightItem, courier).asLiveData().map {
                when (it) {
                    is Resource.Success -> Resource.Success(DataMapper.mapCostDomainToPresentation(it.data))
                    is Resource.Loading -> Resource.Loading()
                    is Resource.Error -> Resource.Error(it.message.toString())
                }
            }
        }
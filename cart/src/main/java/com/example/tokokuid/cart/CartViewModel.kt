package com.example.tokokuid.cart

import android.util.Log
import androidx.lifecycle.*
import com.example.tokokuid.core.DataDummy
import com.example.tokokuid.core.data.Resource
import com.example.tokokuid.core.domain.usecase.TokoUseCase
import com.example.tokokuid.core.modelpresentation.City
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.core.modelpresentation.TypeSend
import com.example.tokokuid.core.utils.DataMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CartViewModel(private val useCase: TokoUseCase) : ViewModel() {

    val dummyCourier = DataDummy.getCourier()
    private val _listCity = MutableLiveData<Resource<List<City>>>()
    val _selector = MutableLiveData<Map<SelectorCode,Int>>()
    val selector:LiveData<Map<SelectorCode,Int>> = _selector
    var listCity:LiveData<Resource<List<City>>> = _listCity


    var citySelected:City? = null

    val itemInCart
        get() = useCase.getAllInCart().asLiveData().map {
            DataMapper.mapItemDomainToPresentation(it)
        }

    fun deleteFromCart(item: Item) = viewModelScope.launch {
        useCase.deleteFromCart(DataMapper.mapItemPresentationToDomain(item))
    }

    fun getListCity() {
        viewModelScope.launch {
            useCase.getListCity().collect {
                when (it) {
                    is Resource.Success -> {
                        _listCity.postValue(Resource.Success(DataMapper.mapCityDomainToPresentation(it.data)))
                    }
                    is Resource.Loading -> _listCity.postValue(Resource.Loading())
                    is Resource.Error -> _listCity.postValue(Resource.Error(it.message.toString()))
                }
            }

        }
    }

    fun getCost(
        originId: String,
        destinationId: String,
        weightItem: Int,
        courier: String
    ): LiveData<Resource<List<TypeSend>?>> =
        useCase.getCost(originId, destinationId, weightItem, courier).asLiveData().map {
            when (it) {
                is Resource.Success -> Resource.Success(DataMapper.mapCostDomainToPresentation(it.data))
                is Resource.Loading -> Resource.Loading()
                is Resource.Error -> Resource.Error(it.message.toString())
            }
        }
}
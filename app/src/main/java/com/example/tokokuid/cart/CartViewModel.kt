package com.example.tokokuid.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tokokuid.core.domain.usecase.TokoUseCase
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.core.utils.DataMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CartViewModel(private val useCase: TokoUseCase):ViewModel() {
    val itemInCart = useCase.getAllInCart().asLiveData().map {
        DataMapper.mapDomainToPresentation(it)
    }
    fun deleteFromCart(item:Item) = viewModelScope.launch {
        useCase.deleteFromCart(DataMapper.mapPresentationToDomain(item))
    }
}
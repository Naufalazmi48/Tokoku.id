package com.example.tokokuid.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokokuid.core.domain.usecase.TokoUseCase
import com.example.tokokuid.core.modelpresentation.Item
import com.example.tokokuid.core.utils.DataMapper
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: TokoUseCase): ViewModel() {
    fun insertCart(item: Item) = viewModelScope.launch {
        useCase.insertCart(DataMapper.mapPresentationToDomain(item))
    }
}
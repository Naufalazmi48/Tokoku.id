package com.example.tokokuid.di

import com.example.tokokuid.cart.CartViewModel
import com.example.tokokuid.core.domain.usecase.TokoIntercator
import com.example.tokokuid.core.domain.usecase.TokoUseCase
import com.example.tokokuid.detail.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TokoUseCase> { TokoIntercator(get()) }
}

val viewModelModule = module {
    viewModel { CartViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
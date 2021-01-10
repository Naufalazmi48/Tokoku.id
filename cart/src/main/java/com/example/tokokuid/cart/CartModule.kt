package com.example.tokokuid.cart

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartModule = module {
    viewModel { CartViewModel(get()) }
}
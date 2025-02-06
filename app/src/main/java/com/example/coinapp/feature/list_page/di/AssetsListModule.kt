package com.example.coinapp.feature.list_page.di

import com.example.coinapp.feature.list_page.view_model.AssetsListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val assetsListModule = module {
    viewModelOf(::AssetsListViewModel)
}

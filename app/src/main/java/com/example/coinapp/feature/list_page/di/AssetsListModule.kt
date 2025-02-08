package com.example.coinapp.feature.list_page.di

import com.example.coinapp.feature.list_page.domain.use_case.GetAllAssetsUseCase
import com.example.coinapp.feature.list_page.domain.use_case.GetAllAssetsUseCaseImpl
import com.example.coinapp.feature.list_page.view_model.AssetsListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val assetsListModule = module {
    // use case
    factory<GetAllAssetsUseCase> {
        GetAllAssetsUseCaseImpl(
            assetRepository = get()
        )
    }

    // view model
    viewModel {
        AssetsListViewModel(
            getAllAssetsUseCase = get()
        )
    }
}

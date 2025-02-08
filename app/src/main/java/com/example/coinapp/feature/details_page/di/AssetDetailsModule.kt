package com.example.coinapp.feature.details_page.di

import com.example.coinapp.feature.details_page.domain.use_case.GetAllExchangesUseCase
import com.example.coinapp.feature.details_page.domain.use_case.GetAllExchangesUseCaseImpl
import com.example.coinapp.feature.details_page.domain.use_case.GetOrderBookForSymbolUseCase
import com.example.coinapp.feature.details_page.domain.use_case.GetOrderBookForSymbolUseCaseImpl
import com.example.coinapp.feature.details_page.domain.use_case.GetSymbolsForAssetAndExchangeUseCase
import com.example.coinapp.feature.details_page.domain.use_case.GetSymbolsForAssetAndExchangeUseCaseImpl
import com.example.coinapp.feature.details_page.view_model.AssetDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val assetDetailsModule = module {
    // use case
    factory<GetAllExchangesUseCase> {
        GetAllExchangesUseCaseImpl(
            exchangeRepository = get()
        )
    }

    factory<GetSymbolsForAssetAndExchangeUseCase> {
        GetSymbolsForAssetAndExchangeUseCaseImpl(
            symbolRepository = get()
        )
    }

    factory<GetAllExchangesUseCase> {
        GetAllExchangesUseCaseImpl(
            exchangeRepository = get()
        )
    }

    factory<GetOrderBookForSymbolUseCase> {
        GetOrderBookForSymbolUseCaseImpl(
            orderBookRepository = get()
        )
    }

    // view model
    viewModel { (assetId: String?) ->
        AssetDetailsViewModel(
            selectedAssetId = assetId,
            getAllAssetsUseCase = get(),
            getAllExchangesUseCase = get(),
            getOrderBookForSymbolUseCase = get(),
            getSymbolsForAssetAndExchangeUseCase = get()
        )
    }
}

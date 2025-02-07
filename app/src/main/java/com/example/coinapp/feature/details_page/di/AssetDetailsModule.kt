package com.example.coinapp.feature.details_page.di

import com.example.coinapp.feature.details_page.view_model.AssetDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val assetDetailsModule = module {
    viewModel { (assetId: String?) ->
        AssetDetailsViewModel(
            selectedAssetId = assetId
        )
    }
}

package com.example.coinapp.feature.details_page.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.coinapp.core.ui.BasePage
import com.example.coinapp.feature.details_page.view_model.AssetDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AssetDetailsPage(
    navHostController: NavHostController,
    assetId: String?
) {
    val viewModel = koinViewModel<AssetDetailsViewModel> {
        parametersOf(assetId)
    }

    val state = viewModel.uiState.collectAsState().value

    BasePage(
        state
    ) { data ->
        AssetDetailsScreen(
            data = data,
            onSymbolClick = {},
            onExchangeClick = {},
            onNavigateBack = {},
            onBackToExchangeClick = {},
            onBackToSymbolClick = {},
        )
    }
}

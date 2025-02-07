package com.example.coinapp.core.navigation

sealed class Page(val route: String) {
    data object AssetList : Page("assetList")
    data object AssetDetails : Page("details/{assetId}") {
        const val EXTRA_ASSET_ID = "assetId"
        fun createRoute(assetId: String) = "details/$assetId"
    }
}

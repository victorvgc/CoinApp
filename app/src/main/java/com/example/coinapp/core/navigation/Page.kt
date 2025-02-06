package com.example.coinapp.core.navigation

sealed class Page(val route: String) {
    data object AssetList : Page("assetList")
}
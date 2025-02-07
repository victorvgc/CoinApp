package com.example.coinapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coinapp.feature.details_page.ui.AssetDetailsPage
import com.example.coinapp.feature.list_page.ui.AssetsListPage

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Page.AssetList.route
    ) {
        composable(Page.AssetList.route) {
            AssetsListPage(
                navHostController = navHostController
            )
        }
        composable(Page.AssetDetails.route) { navBackStackEntry ->
            AssetDetailsPage(
                navHostController = navHostController,
                assetId = navBackStackEntry.arguments?.getString(Page.AssetDetails.EXTRA_ASSET_ID)
            )
        }
    }
}

package com.example.coinapp.feature.list_page.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.coinapp.core.ui.BasePage
import com.example.coinapp.feature.list_page.view_model.AssetsListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AssetsListPage(
    navHostController: NavHostController
) {
    val viewModel = koinViewModel<AssetsListViewModel>()

    val state = viewModel.uiState.collectAsState().value

    BasePage(
        state = state,
    ) { data ->
        AssetsListScreen(
            list = data.assetsList,
            onItemClick = {
                viewModel.onUiAction(AssetsListViewModel.UiAction.OnAssetClick(it))
            }
        )
    }
}

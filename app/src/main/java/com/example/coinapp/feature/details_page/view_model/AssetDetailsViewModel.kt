package com.example.coinapp.feature.details_page.view_model

import androidx.lifecycle.viewModelScope
import com.example.coinapp.core.view_model.BaseViewModel
import com.example.coinapp.feature.details_page.ui.model.AssetDetailsData
import com.example.coinapp.feature.details_page.ui.model.UiExchangeItem
import com.example.coinapp.feature.details_page.ui.model.UiSymbolItem
import kotlinx.coroutines.launch

class AssetDetailsViewModel(
    private val selectedAssetId: String?
) : BaseViewModel<AssetDetailsData, AssetDetailsViewModel.UiAction>() {
    sealed interface UiAction {
        data class OnSelectExchange(val exchange: UiExchangeItem) : UiAction
        data class OnSelectSymbol(val symbol: UiSymbolItem) : UiAction
        data object OnBackFromOrderBook : UiAction
        data object OnBackFromSymbolsList : UiAction
        data object OnBackFromDetails : UiAction
    }

    override fun onUiAction(action: UiAction) {

    }

    init {
        viewModelScope.launch {
            getAssetDetails()
        }
    }

    private suspend fun getAssetDetails() {

    }
}

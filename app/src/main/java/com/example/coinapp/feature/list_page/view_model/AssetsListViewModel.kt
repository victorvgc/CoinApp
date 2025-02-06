package com.example.coinapp.feature.list_page.view_model

import androidx.lifecycle.viewModelScope
import com.example.coinapp.core.view_model.BaseViewModel
import com.example.coinapp.feature.list_page.ui.model.AssetsListData
import com.example.coinapp.feature.list_page.ui.model.UiAssetItem
import kotlinx.coroutines.launch

class AssetsListViewModel : BaseViewModel<AssetsListData, AssetsListViewModel.UiAction>() {
    sealed interface UiAction {
        data class OnAssetClick(val uiAssetItem: UiAssetItem) : UiAction
    }

    override fun onUiAction(action: UiAction) {

    }

    init {
        viewModelScope.launch {
            getAssetsList()
        }
    }

    private suspend fun getAssetsList() {

    }
}

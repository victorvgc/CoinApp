package com.example.coinapp.feature.details_page.view_model

import androidx.lifecycle.viewModelScope
import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.AppOrderBookData
import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.Failure
import com.example.coinapp.core.ui.model.UiError
import com.example.coinapp.core.view_model.BaseViewModel
import com.example.coinapp.feature.details_page.domain.use_case.GetAllExchangesUseCase
import com.example.coinapp.feature.details_page.domain.use_case.GetOrderBookForSymbolUseCase
import com.example.coinapp.feature.details_page.domain.use_case.GetSymbolsForAssetAndExchangeUseCase
import com.example.coinapp.feature.details_page.ui.model.AssetDetailsData
import com.example.coinapp.feature.details_page.ui.model.UiExchangeItem
import com.example.coinapp.feature.details_page.ui.model.UiOrderBookData
import com.example.coinapp.feature.details_page.ui.model.UiSymbolItem
import com.example.coinapp.feature.list_page.domain.use_case.GetAllAssetsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class AssetDetailsViewModel(
    private val selectedAssetId: String?,
    private val getAllAssetsUseCase: GetAllAssetsUseCase,
    private val getAllExchangesUseCase: GetAllExchangesUseCase,
    private val getSymbolsForAssetAndExchangeUseCase: GetSymbolsForAssetAndExchangeUseCase,
    private val getOrderBookForSymbolUseCase: GetOrderBookForSymbolUseCase,
) : BaseViewModel<AssetDetailsData, AssetDetailsViewModel.UiAction>() {
    sealed interface UiAction {
        data class OnSelectExchange(val exchange: UiExchangeItem) : UiAction
        data class OnSelectSymbol(val exchange: UiExchangeItem, val symbol: UiSymbolItem) : UiAction
        data class OnBackFromOrderBook(val exchange: UiExchangeItem) : UiAction
        data object OnBackFromSymbolsList : UiAction
        data object OnBackFromDetails : UiAction
    }

    private var selectedAsset: AppAsset? = null
    private var exchangeList = emptyList<AppExchange>()
    private var selectedExchange: AppExchange? = null
    private var symbolList = emptyList<AppSymbol>()
    private var selectedSymbol: AppSymbol? = null

    override fun onUiAction(action: UiAction) {
        when (action) {
            UiAction.OnBackFromDetails -> {}
            is UiAction.OnBackFromOrderBook -> {
                val currentAsset = selectedAsset ?: return

                updateUiWithSuccess(
                    AssetDetailsData.SelectedExchangeData(
                        assetId = currentAsset.id,
                        assetPrice = currentAsset.price,
                        assetName = currentAsset.name,
                        assetDailyVolume = currentAsset.dailyVolume,
                        assetIconId = currentAsset.iconId,
                        selectedExchange = action.exchange,
                        symbolsList = symbolList.map {
                            UiSymbolItem(
                                id = it.id,
                                name = it.id.replace("_", " "),
                                hasCurrentOrderBook = symbolHasCurrentOrderBook(it)
                            )
                        }
                    )
                )
            }

            UiAction.OnBackFromSymbolsList -> {
                val currentAsset = selectedAsset ?: return

                updateUiWithSuccess(
                    AssetDetailsData.InitialData(
                        assetId = currentAsset.id,
                        assetPrice = currentAsset.price,
                        assetName = currentAsset.name,
                        assetDailyVolume = currentAsset.dailyVolume,
                        assetIconId = currentAsset.iconId,
                        exchangeList = exchangeList.map {
                            UiExchangeItem(
                                id = it.id,
                                name = it.name
                            )
                        }
                    )
                )
            }

            is UiAction.OnSelectExchange -> {
                updateUiWithLoading()
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        val currentAsset = selectedAsset ?: return@withContext
                        val currentExchange =
                            exchangeList.find { it.id == action.exchange.id } ?: return@withContext

                        selectedExchange = currentExchange

                        getAllSymbolsForAssetAndExchangeThenRun(
                            asset = currentAsset,
                            exchange = currentExchange,
                        ) { symbolList ->
                            updateUiWithSuccess(
                                AssetDetailsData.SelectedExchangeData(
                                    assetId = currentAsset.id,
                                    assetPrice = currentAsset.price,
                                    assetName = currentAsset.name,
                                    assetDailyVolume = currentAsset.dailyVolume,
                                    assetIconId = currentAsset.iconId,
                                    selectedExchange = action.exchange,
                                    symbolsList = symbolList.map {
                                        UiSymbolItem(
                                            id = it.id,
                                            name = it.id.replace("_", " "),
                                            hasCurrentOrderBook = symbolHasCurrentOrderBook(it)
                                        )
                                    }
                                )
                            )
                        }
                    }
                }
            }

            is UiAction.OnSelectSymbol -> {
                updateUiWithLoading()
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        val currentAsset = selectedAsset ?: return@withContext
                        val currentSymbol =
                            symbolList.find { it.id == action.symbol.id } ?: return@withContext

                        selectedSymbol = currentSymbol
                        getOrderBookForSymbolsThenRun(
                            symbol = currentSymbol
                        ) { orderBookData ->
                            updateUiWithSuccess(
                                AssetDetailsData.SelectedExchangeSymbolData(
                                    assetId = currentAsset.id,
                                    assetPrice = currentAsset.price,
                                    assetName = currentAsset.name,
                                    assetDailyVolume = currentAsset.dailyVolume,
                                    assetIconId = currentAsset.iconId,
                                    selectedExchange = action.exchange,
                                    selectedSymbol = action.symbol,
                                    orderBookData = UiOrderBookData(
                                        symbolId = orderBookData.symbolId,
                                        asks = orderBookData.asks.map {
                                            UiOrderBookData.UiEntry(
                                                price = it.price,
                                                size = it.size
                                            )
                                        },
                                        bids = orderBookData.bids.map {
                                            UiOrderBookData.UiEntry(
                                                price = it.price,
                                                size = it.size
                                            )
                                        },
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getAssetDetails()
            }
        }
    }

    private suspend fun getAssetDetails() {
        getSelectedAssetThenRun { asset ->
            getAllExchangesThenRun { exchangeList ->
                updateUiWithSuccess(
                    AssetDetailsData.InitialData(
                        assetId = asset.id,
                        assetPrice = asset.price,
                        assetName = asset.name,
                        assetDailyVolume = asset.dailyVolume,
                        assetIconId = asset.iconId,
                        exchangeList = exchangeList.map {
                            UiExchangeItem(
                                id = it.id,
                                name = it.name
                            )
                        }
                    )
                )
            }
        }
    }

    private suspend fun getSelectedAssetThenRun(block: suspend (AppAsset) -> Unit) {
        getAllAssetsUseCase()
            .onSuccess { data ->
                val currentAsset = data.find { it.id == selectedAssetId }

                if (currentAsset != null) {
                    selectedAsset = currentAsset
                    viewModelScope.launch {
                        block(currentAsset)
                    }
                } else
                    doOnError(
                        Failure(
                            Exception("Asset not found")
                        )
                    )
            }
            .onError(::doOnError)
    }

    private suspend fun getAllExchangesThenRun(
        block: (List<AppExchange>) -> Unit
    ) {
        getAllExchangesUseCase()
            .onSuccess {
                exchangeList = it
                block(it)
            }
            .onError(::doOnError)
    }

    private suspend fun getAllSymbolsForAssetAndExchangeThenRun(
        asset: AppAsset,
        exchange: AppExchange,
        block: (List<AppSymbol>) -> Unit
    ) {
        getSymbolsForAssetAndExchangeUseCase(
            asset = asset,
            exchange = exchange
        ).onSuccess {
            symbolList = it
            block(it)
        }.onError(::doOnError)
    }

    private suspend fun getOrderBookForSymbolsThenRun(
        symbol: AppSymbol,
        block: (AppOrderBookData) -> Unit
    ) {
        getOrderBookForSymbolUseCase(
            symbol = symbol
        ).onSuccess {
            block(it)
        }.onError(::doOnError)
    }

    private fun doOnError(failure: Failure) {
        updateUiWithError(
            UiError(
                icon = UiError.ErrorIcon.FAILURE,
                message = failure.throwable.message.orEmpty(),
                onRetry = {
                    viewModelScope.launch {
                        getAssetDetails()
                    }
                }
            )
        )
    }

    private fun symbolHasCurrentOrderBook(symbol: AppSymbol): Boolean {
        val latestOrderBookData = symbol.orderBookEndDate ?: return false

        return latestOrderBookData.isAfter(LocalDate.now().minusMonths(1))
    }
}

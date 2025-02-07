package com.example.coinapp.feature.details_page.ui.model

import java.math.BigDecimal

sealed class AssetDetailsData(
    open val assetId: String,
    open val assetIconId: String,
    open val assetName: String,
    open val assetPrice: BigDecimal,
    open val assetDailyVolume: BigDecimal,
) {
    data class InitialData(
        override val assetId: String,
        override val assetIconId: String,
        override val assetName: String,
        override val assetPrice: BigDecimal,
        override val assetDailyVolume: BigDecimal,
        val exchangeList: List<UiExchangeItem>
    ) : AssetDetailsData(
        assetId = assetId,
        assetName = assetName,
        assetIconId = assetIconId,
        assetPrice = assetPrice,
        assetDailyVolume = assetDailyVolume
    )

    data class SelectedExchangeData(
        override val assetId: String,
        override val assetIconId: String,
        override val assetName: String,
        override val assetPrice: BigDecimal,
        override val assetDailyVolume: BigDecimal,
        val selectedExchange: UiExchangeItem,
        val symbolsList: List<UiSymbolItem>
    ) : AssetDetailsData(
        assetId = assetId,
        assetName = assetName,
        assetIconId = assetIconId,
        assetPrice = assetPrice,
        assetDailyVolume = assetDailyVolume
    )

    data class SelectedExchangeSymbolData(
        override val assetId: String,
        override val assetIconId: String,
        override val assetName: String,
        override val assetPrice: BigDecimal,
        override val assetDailyVolume: BigDecimal,
        val selectedExchange: UiExchangeItem,
        val selectedSymbol: UiSymbolItem,
        val orderBookData: UiOrderBookData
    ) : AssetDetailsData(
        assetId = assetId,
        assetName = assetName,
        assetIconId = assetIconId,
        assetPrice = assetPrice,
        assetDailyVolume = assetDailyVolume
    )
}

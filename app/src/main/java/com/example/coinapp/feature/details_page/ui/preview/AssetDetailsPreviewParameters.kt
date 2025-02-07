package com.example.coinapp.feature.details_page.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.coinapp.feature.details_page.ui.model.AssetDetailsData
import com.example.coinapp.feature.details_page.ui.model.UiExchangeItem
import com.example.coinapp.feature.details_page.ui.model.UiOrderBookData
import com.example.coinapp.feature.details_page.ui.model.UiSymbolItem
import java.math.BigDecimal

class AssetDetailsPreviewParameters : PreviewParameterProvider<AssetDetailsData> {
    override val values: Sequence<AssetDetailsData>
        get() = sequenceOf(
            AssetDetailsData.InitialData(
                assetIconId = "IPE",
                assetName = "Initial Preview - empty",
                assetPrice = BigDecimal("1234567.89"),
                assetDailyVolume = BigDecimal("9876543.21"),
                assetId = "PRV",
                exchangeList = emptyList()
            ),
            AssetDetailsData.InitialData(
                assetIconId = "IPW",
                assetName = "Initial Preview",
                assetPrice = BigDecimal("1234567.89"),
                assetDailyVolume = BigDecimal("9876543.21"),
                assetId = "PRV",
                exchangeList = listOf(
                    UiExchangeItem(
                        id = "PRW",
                        name = "PREVIEW"
                    ),
                    UiExchangeItem(
                        id = "PRW",
                        name = "PREVIEW"
                    ),
                )
            ),
            AssetDetailsData.SelectedExchangeData(
                assetIconId = "EPE",
                assetName = "Exchange Preview - empty",
                assetPrice = BigDecimal("1234567.89"),
                assetDailyVolume = BigDecimal("9876543.21"),
                assetId = "PRV",
                selectedExchange = UiExchangeItem(
                    id = "PRW",
                    name = "PREVIEW"
                ),
                symbolsList = emptyList(),
            ),
            AssetDetailsData.SelectedExchangeData(
                assetIconId = "EPW",
                assetName = "Exchange Preview",
                assetPrice = BigDecimal("1234567.89"),
                assetDailyVolume = BigDecimal("9876543.21"),
                assetId = "PRV",
                selectedExchange = UiExchangeItem(
                    id = "PRW",
                    name = "PREVIEW"
                ),
                symbolsList = listOf(
                    UiSymbolItem(
                        id = "SPW",
                        name = "PREVIEW_SPOT_PREVIEW",
                        hasCurrentOrderBook = false
                    ),
                    UiSymbolItem(
                        id = "SPW",
                        name = "PREVIEW_SPOT_PREVIEW",
                        hasCurrentOrderBook = true
                    ),
                ),
            ),
            AssetDetailsData.SelectedExchangeSymbolData(
                assetIconId = "SPE",
                assetName = "Symbol Preview - empty",
                assetPrice = BigDecimal("1234567.89"),
                assetDailyVolume = BigDecimal("9876543.21"),
                assetId = "PRV",
                selectedExchange = UiExchangeItem(
                    id = "PRW",
                    name = "PREVIEW"
                ),
                selectedSymbol = UiSymbolItem(
                    id = "SPW",
                    name = "PREVIEW_SPOT_PREVIEW",
                    hasCurrentOrderBook = true
                ),
                orderBookData = UiOrderBookData(
                    symbolId = "SPW",
                    asks = emptyList(),
                    bids = emptyList(),
                ),
            ),
            AssetDetailsData.SelectedExchangeSymbolData(
                assetIconId = "SPW",
                assetName = "Symbol Preview",
                assetPrice = BigDecimal("1234567.89"),
                assetDailyVolume = BigDecimal("9876543.21"),
                assetId = "PRV",
                selectedExchange = UiExchangeItem(
                    id = "PRW",
                    name = "PREVIEW"
                ),
                selectedSymbol = UiSymbolItem(
                    id = "SPW",
                    name = "PREVIEW_SPOT_PREVIEW",
                    hasCurrentOrderBook = true
                ),
                orderBookData = UiOrderBookData(
                    symbolId = "SPW",
                    asks = listOf(
                        UiOrderBookData.UiEntry(
                            price = BigDecimal("234567.89"),
                            size = BigDecimal("234567.89"),
                        ),
                        UiOrderBookData.UiEntry(
                            price = BigDecimal("1234567.89"),
                            size = BigDecimal("1234567.89"),
                        ),
                    ),
                    bids = listOf(
                        UiOrderBookData.UiEntry(
                            price = BigDecimal("234567.89"),
                            size = BigDecimal("234567.89"),
                        ),
                        UiOrderBookData.UiEntry(
                            price = BigDecimal("1234567.89"),
                            size = BigDecimal("1234567.89"),
                        ),
                    ),
                ),
            ),
        )
}

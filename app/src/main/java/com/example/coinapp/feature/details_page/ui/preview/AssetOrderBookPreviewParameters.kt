package com.example.coinapp.feature.details_page.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.coinapp.feature.details_page.ui.model.UiOrderBookData
import java.math.BigDecimal

class AssetOrderBookPreviewParameters : PreviewParameterProvider<UiOrderBookData> {
    override val values: Sequence<UiOrderBookData>
        get() = sequenceOf(
            UiOrderBookData(
                symbolId = "SPW",
                asks = emptyList(),
                bids = emptyList()
            ),
            UiOrderBookData(
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
            )
        )
}

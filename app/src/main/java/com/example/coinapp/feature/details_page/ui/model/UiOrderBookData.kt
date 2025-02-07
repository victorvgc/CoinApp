package com.example.coinapp.feature.details_page.ui.model

import java.math.BigDecimal

data class UiOrderBookData(
    val symbolId: String,
    val asks: List<UiEntry>,
    val bids: List<UiEntry>,
) {
    data class UiEntry(
        val price: BigDecimal,
        val size: BigDecimal
    )
}

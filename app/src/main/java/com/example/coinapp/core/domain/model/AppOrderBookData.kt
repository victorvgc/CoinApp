package com.example.coinapp.core.domain.model

import java.math.BigDecimal

data class AppOrderBookData(
    val symbolId: String,
    val asks: List<Entry>,
    val bids: List<Entry>
) {
    data class Entry(
        val price: BigDecimal,
        val size: BigDecimal,
    )
}

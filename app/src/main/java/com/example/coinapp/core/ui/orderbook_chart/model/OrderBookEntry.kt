package com.example.coinapp.core.ui.orderbook_chart.model

import java.math.BigDecimal

data class OrderBookEntry(
    val price: BigDecimal,
    val size: BigDecimal,
    val type: OrderBookEntryType
)

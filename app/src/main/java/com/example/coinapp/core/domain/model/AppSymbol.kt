package com.example.coinapp.core.domain.model

import java.util.Date

data class AppSymbol(
    val id: String,
    val baseAssetId: String,
    val quoteAssetId: String,
    val exchangeId: String,
    val orderBookEndDate: Date
)

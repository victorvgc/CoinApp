package com.example.coinapp.core.domain.model

data class AppSymbol(
    val id: String,
    val baseAssetId: String,
    val quoteAssetId: String,
    val exchangeId: String,
)

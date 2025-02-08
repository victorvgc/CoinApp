package com.example.coinapp.core.domain.model

import java.time.LocalDate

data class AppSymbol(
    val id: String,
    val baseAssetId: String,
    val quoteAssetId: String,
    val exchangeId: String,
    val orderBookEndDate: LocalDate?
)

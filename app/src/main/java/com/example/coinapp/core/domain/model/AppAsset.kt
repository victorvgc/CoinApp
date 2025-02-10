package com.example.coinapp.core.domain.model

import java.math.BigDecimal

data class AppAsset(
    val id: String,
    val name: String,
    val price: BigDecimal,
    val dailyVolume: BigDecimal,
    val iconId: String,
    val isCrypto: Boolean
)

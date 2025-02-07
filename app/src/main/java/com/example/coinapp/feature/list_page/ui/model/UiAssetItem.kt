package com.example.coinapp.feature.list_page.ui.model

import java.math.BigDecimal

data class UiAssetItem(
    val id: String,
    val iconId: String,
    val name: String,
    val dailyVolume: BigDecimal
)

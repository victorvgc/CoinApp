package com.example.coinapp.core.data.model.remote

import com.squareup.moshi.Json
import java.io.Serializable
import java.math.BigDecimal

data class RemoteOrderBook(
    @field:Json(name = "symbol_id") val symbolId: String,
    @field:Json(name = "asks") val asks: List<Entry>,
    @field:Json(name = "bids") val bids: List<Entry>,
) : Serializable {
    data class Entry(
        @field:Json(name = "price") val price: BigDecimal,
        @field:Json(name = "size") val size: BigDecimal,
    ) : Serializable
}

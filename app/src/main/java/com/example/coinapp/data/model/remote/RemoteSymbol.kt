package com.example.coinapp.data.model.remote

import com.squareup.moshi.Json
import java.io.Serializable

data class RemoteSymbol(
    @field:Json(name = "symbol_id") val id: String,
    @field:Json(name = "exchange_id") val exchangeId: String,
    @field:Json(name = "asset_id_base") val baseAssetId: String,
    @field:Json(name = "asset_id_quote") val quoteAssetId: String,
) : Serializable

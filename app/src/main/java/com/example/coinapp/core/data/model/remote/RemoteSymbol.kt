package com.example.coinapp.core.data.model.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RemoteSymbol(
    @SerializedName("symbol_id") val id: String,
    @SerializedName("exchange_id") val exchangeId: String,
    @SerializedName("asset_id_base") val baseAssetId: String,
    @SerializedName("asset_id_quote") val quoteAssetId: String,
    @SerializedName("data_orderbook_end") val orderBookEndDate: String
) : Serializable

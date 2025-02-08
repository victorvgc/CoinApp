package com.example.coinapp.core.data.model.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RemoteOrderBook(
    @SerializedName("symbol_id") val symbolId: String,
    @SerializedName("asks") val asks: List<Entry>,
    @SerializedName("bids") val bids: List<Entry>,
) : Serializable {
    data class Entry(
        @SerializedName("price") val price: String,
        @SerializedName("size") val size: String,
    ) : Serializable
}

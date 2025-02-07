package com.example.coinapp.core.data.model.remote

import com.squareup.moshi.Json
import java.io.Serializable

data class RemoteExchange(
    @field:Json(name = "exchange_id") val id: String,
    @field:Json(name = "rank") val rank: Int,
) : Serializable

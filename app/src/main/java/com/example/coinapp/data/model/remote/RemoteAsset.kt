package com.example.coinapp.data.model.remote

import com.squareup.moshi.Json
import java.io.Serializable
import java.math.BigDecimal

data class RemoteAsset(
    @field:Json(name = "asset_id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "type_is_crypto") val isCrypto: Boolean,
    @field:Json(name = "volume_1day_usd") val volumeOneDayUsd: BigDecimal,
    @field:Json(name = "id_icon") val icon: String,
) : Serializable

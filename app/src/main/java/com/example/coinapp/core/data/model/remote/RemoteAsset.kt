package com.example.coinapp.core.data.model.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RemoteAsset(
    @SerializedName("asset_id") val id: String,
    @SerializedName("name") val name: String?,
    @SerializedName("type_is_crypto") val isCrypto: Int,
    @SerializedName("volume_1day_usd") val dailyVolume: Double,
    @SerializedName("id_icon") val icon: String?,
    @SerializedName("price_usd") val price: String?,
) : Serializable

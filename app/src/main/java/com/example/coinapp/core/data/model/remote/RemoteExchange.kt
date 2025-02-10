package com.example.coinapp.core.data.model.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RemoteExchange(
    @SerializedName("exchange_id") val id: String,
    @SerializedName("name") val name: String?,
    @SerializedName("rank") val rank: Int,
) : Serializable

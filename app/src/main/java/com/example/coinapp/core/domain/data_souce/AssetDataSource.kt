package com.example.coinapp.core.domain.data_souce

import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper

sealed interface AssetDataSource {
    interface Remote : AssetDataSource

    suspend fun getAssetsList(): ResultWrapper<List<AppAsset>>
}

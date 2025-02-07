package com.example.coinapp.core.domain.data_souce

import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper

interface AssetsDataSource {
    interface Remote : AssetsDataSource

    suspend fun getAssetsList(): ResultWrapper<List<AppAsset>>
}

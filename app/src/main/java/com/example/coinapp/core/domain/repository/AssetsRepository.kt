package com.example.coinapp.core.domain.repository

import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper

interface AssetsRepository {
    suspend fun getAssetsList(): ResultWrapper<List<AppAsset>>
}

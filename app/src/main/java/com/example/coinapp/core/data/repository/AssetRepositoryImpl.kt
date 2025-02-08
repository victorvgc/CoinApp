package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.AssetDataSource
import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.AssetRepository

class AssetRepositoryImpl(
    private val remoteAssetDataSource: AssetDataSource.Remote
) : AssetRepository {
    override suspend fun getAssetsList(): ResultWrapper<List<AppAsset>> =
        remoteAssetDataSource.getAssetsList()
}

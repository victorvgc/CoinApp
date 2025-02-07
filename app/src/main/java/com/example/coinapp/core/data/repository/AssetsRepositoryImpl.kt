package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.AssetsDataSource
import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.AssetsRepository

class AssetsRepositoryImpl(
    private val remoteAssetsDataSource: AssetsDataSource.Remote
) : AssetsRepository {
    override suspend fun getAssetsList(): ResultWrapper<List<AppAsset>> =
        remoteAssetsDataSource.getAssetsList()
}

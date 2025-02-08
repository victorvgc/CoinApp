package com.example.coinapp.feature.list_page.domain.use_case

import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.AssetRepository

class GetAllAssetsUseCaseImpl(
    private val assetRepository: AssetRepository
) : GetAllAssetsUseCase {
    override suspend fun invoke(): ResultWrapper<List<AppAsset>> = assetRepository.getAssetsList()
}

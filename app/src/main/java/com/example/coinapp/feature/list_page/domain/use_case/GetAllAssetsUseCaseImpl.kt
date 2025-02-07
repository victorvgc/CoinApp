package com.example.coinapp.feature.list_page.domain.use_case

import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.AssetsRepository

class GetAllAssetsUseCaseImpl(
    private val assetsRepository: AssetsRepository
) : GetAllAssetsUseCase {
    override suspend fun invoke(): ResultWrapper<List<AppAsset>> = assetsRepository.getAssetsList()
}

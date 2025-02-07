package com.example.coinapp.feature.list_page.domain.use_case

import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper

interface GetAllAssetsUseCase {

    suspend operator fun invoke(): ResultWrapper<List<AppAsset>>
}

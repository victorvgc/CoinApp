package com.example.coinapp.feature.details_page.domain.use_case

import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.ResultWrapper

interface GetSymbolsForAssetAndExchangeUseCase {
    suspend operator fun invoke(
        asset: AppAsset,
        exchange: AppExchange
    ): ResultWrapper<List<AppSymbol>>
}

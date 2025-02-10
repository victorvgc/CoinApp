package com.example.coinapp.feature.details_page.domain.use_case

import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.SymbolRepository

class GetSymbolsForAssetAndExchangeUseCaseImpl(
    private val symbolRepository: SymbolRepository
) : GetSymbolsForAssetAndExchangeUseCase {
    override suspend fun invoke(
        asset: AppAsset,
        exchange: AppExchange
    ): ResultWrapper<List<AppSymbol>> = symbolRepository.getSymbolsListFromAssetAndExchangeIds(
        exchangeId = exchange.id,
        assetId = asset.id
    )
}

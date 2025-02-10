package com.example.coinapp.core.domain.data_souce

import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.ResultWrapper

sealed interface SymbolDataSource {
    interface Remote : SymbolDataSource

    suspend fun getSymbolsFromAssetAndExchangeIds(
        assetId: String,
        exchangeId: String
    ): ResultWrapper<List<AppSymbol>>
}

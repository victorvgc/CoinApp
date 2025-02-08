package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.SymbolDataSource
import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.SymbolRepository

class SymbolRepositoryImpl(
    private val symbolDataSource: SymbolDataSource.Remote
) : SymbolRepository {
    override suspend fun getSymbolsListFromAssetAndExchangeIds(
        assetId: String,
        exchangeId: String
    ): ResultWrapper<List<AppSymbol>> =
        symbolDataSource.getSymbolsFromAssetAndExchangeIds(
            assetId = assetId,
            exchangeId = exchangeId
        )
}

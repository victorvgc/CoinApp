package com.example.coinapp.core.domain.repository

import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.ResultWrapper

interface SymbolRepository {
    suspend fun getSymbolsListFromAssetAndExchangeIds(
        assetId: String,
        exchangeId: String
    ): ResultWrapper<List<AppSymbol>>
}

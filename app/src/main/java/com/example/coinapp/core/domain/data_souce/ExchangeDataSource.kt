package com.example.coinapp.core.domain.data_souce

import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.ResultWrapper

sealed interface ExchangeDataSource {
    interface Remote : ExchangeDataSource

    suspend fun getExchangesList(): ResultWrapper<List<AppExchange>>
}

package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.ExchangeDataSource
import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.ExchangeRepository

class ExchangeRepositoryImpl(
    private val exchangeDataSource: ExchangeDataSource.Remote
) : ExchangeRepository {
    override suspend fun getExchangeList(): ResultWrapper<List<AppExchange>> =
        exchangeDataSource.getExchangesList()
}

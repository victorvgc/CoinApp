package com.example.coinapp.core.domain.repository

import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.ResultWrapper

interface ExchangeRepository {
    suspend fun getExchangeList(): ResultWrapper<List<AppExchange>>
}

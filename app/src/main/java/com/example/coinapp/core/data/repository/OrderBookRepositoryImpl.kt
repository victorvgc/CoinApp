package com.example.coinapp.core.data.repository

import com.example.coinapp.core.domain.data_souce.OrderBookDataSource
import com.example.coinapp.core.domain.model.AppOrderBookData
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.OrderBookRepository

class OrderBookRepositoryImpl(
    private val orderBookDataSource: OrderBookDataSource.Remote
) : OrderBookRepository {
    override suspend fun getOrderBookFromSymbolId(symbolId: String): ResultWrapper<AppOrderBookData> =
        orderBookDataSource.getOrderBookFromSymbolId(symbolId = symbolId)
}

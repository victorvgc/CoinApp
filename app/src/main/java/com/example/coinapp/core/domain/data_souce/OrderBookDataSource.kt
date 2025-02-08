package com.example.coinapp.core.domain.data_souce

import com.example.coinapp.core.domain.model.AppOrderBookData
import com.example.coinapp.core.domain.model.ResultWrapper

sealed interface OrderBookDataSource {
    interface Remote : OrderBookDataSource

    suspend fun getOrderBookFromSymbolId(symbolId: String): ResultWrapper<AppOrderBookData>
}

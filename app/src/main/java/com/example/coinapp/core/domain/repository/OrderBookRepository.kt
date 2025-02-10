package com.example.coinapp.core.domain.repository

import com.example.coinapp.core.domain.model.AppOrderBookData
import com.example.coinapp.core.domain.model.ResultWrapper

interface OrderBookRepository {
    suspend fun getOrderBookFromSymbolId(symbolId: String): ResultWrapper<AppOrderBookData>
}

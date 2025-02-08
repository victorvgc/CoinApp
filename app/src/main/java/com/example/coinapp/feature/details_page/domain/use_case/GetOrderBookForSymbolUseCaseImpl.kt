package com.example.coinapp.feature.details_page.domain.use_case

import com.example.coinapp.core.domain.model.AppOrderBookData
import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.OrderBookRepository

class GetOrderBookForSymbolUseCaseImpl(
    private val orderBookRepository: OrderBookRepository
) : GetOrderBookForSymbolUseCase {
    override suspend fun invoke(symbol: AppSymbol): ResultWrapper<AppOrderBookData> =
        orderBookRepository.getOrderBookFromSymbolId(symbolId = symbol.id)
}

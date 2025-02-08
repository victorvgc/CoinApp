package com.example.coinapp.feature.details_page.domain.use_case

import com.example.coinapp.core.domain.model.AppOrderBookData
import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.ResultWrapper

interface GetOrderBookForSymbolUseCase {
    suspend operator fun invoke(symbol: AppSymbol): ResultWrapper<AppOrderBookData>
}

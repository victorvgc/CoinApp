package com.example.coinapp.feature.details_page.domain.use_case

import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.domain.repository.ExchangeRepository

class GetAllExchangesUseCaseImpl(
    private val exchangeRepository: ExchangeRepository
) : GetAllExchangesUseCase {
    override suspend fun invoke(): ResultWrapper<List<AppExchange>> =
        exchangeRepository.getExchangeList()
}

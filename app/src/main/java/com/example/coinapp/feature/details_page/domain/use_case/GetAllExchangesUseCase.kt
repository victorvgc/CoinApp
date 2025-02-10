package com.example.coinapp.feature.details_page.domain.use_case

import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.ResultWrapper

interface GetAllExchangesUseCase {
    suspend operator fun invoke(): ResultWrapper<List<AppExchange>>
}

package com.example.coinapp.core.data.data_source

import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.domain.data_souce.ExchangeDataSource
import com.example.coinapp.core.domain.model.AppExchange
import com.example.coinapp.core.domain.model.ResultWrapper

class RemoteExchangeDataSourceImpl(
    private val coinAppService: CoinAppService
) : ExchangeDataSource.Remote {
    override suspend fun getExchangesList(): ResultWrapper<List<AppExchange>> {
        return ResultWrapper.runCatching {
            val remoteResult = coinAppService.listAllExchanges()

            if (remoteResult.isSuccessful) {
                val body = remoteResult.body().orEmpty()

                val exchangeList = body.mapNotNull { remote ->
                    if (remote.name.isNullOrEmpty())
                        null
                    else
                        AppExchange(
                            id = remote.id,
                            name = remote.name,
                            rank = remote.rank,
                        )
                }

                return@runCatching exchangeList
            } else {
                throw Throwable(
                    message = remoteResult.errorBody()?.string() ?: remoteResult.message()
                )
            }
        }
    }
}

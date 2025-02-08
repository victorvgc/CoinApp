package com.example.coinapp.core.data.data_source

import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.domain.data_souce.SymbolDataSource
import com.example.coinapp.core.domain.model.AppSymbol
import com.example.coinapp.core.domain.model.ResultWrapper
import com.example.coinapp.core.extensions.toDate

class RemoteSymbolDataSourceImpl(
    private val coinAppService: CoinAppService
) : SymbolDataSource.Remote {
    override suspend fun getSymbolsFromAssetAndExchangeIds(
        assetId: String,
        exchangeId: String
    ): ResultWrapper<List<AppSymbol>> {
        return ResultWrapper.runCatching {
            val remoteResult = coinAppService.listSymbolsByAssetAndExchange(
                baseAssetId = assetId,
                exchangeId = exchangeId,
                symbolId = "${exchangeId}_SPOT_${assetId}_USD"
            )

            if (remoteResult.isSuccessful) {
                val body = remoteResult.body().orEmpty()

                val symbolList = body.map { remote ->
                    AppSymbol(
                        id = remote.id,
                        exchangeId = remote.exchangeId,
                        baseAssetId = remote.baseAssetId,
                        quoteAssetId = remote.quoteAssetId,
                        orderBookEndDate = remote.orderBookEndDate.toDate()
                    )
                }

                return@runCatching symbolList
            } else {
                throw Throwable(
                    message = remoteResult.errorBody()?.string() ?: remoteResult.message()
                )
            }
        }
    }
}

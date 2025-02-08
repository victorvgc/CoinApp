package com.example.coinapp.core.data.data_source

import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.domain.data_souce.OrderBookDataSource
import com.example.coinapp.core.domain.model.AppOrderBookData
import com.example.coinapp.core.domain.model.ResultWrapper
import java.math.BigDecimal

class RemoteOrderBookDataSourceImpl(
    private val coinAppService: CoinAppService
) : OrderBookDataSource.Remote {

    override suspend fun getOrderBookFromSymbolId(symbolId: String): ResultWrapper<AppOrderBookData> {
        return ResultWrapper.runCatching {
            val remoteResult = coinAppService.getOrderBook(
                symbolId = symbolId,
            )

            val body = remoteResult.body()

            if (remoteResult.isSuccessful && body != null) {
                val orderBook = AppOrderBookData(
                    symbolId = body.symbolId,
                    asks = body.asks.map {
                        AppOrderBookData.Entry(
                            price = BigDecimal(it.price),
                            size = BigDecimal(it.size),
                        )
                    },
                    bids = body.bids.map {
                        AppOrderBookData.Entry(
                            price = BigDecimal(it.price),
                            size = BigDecimal(it.size),
                        )
                    }
                )

                return@runCatching orderBook
            } else {
                throw Throwable(
                    message = remoteResult.errorBody()?.string() ?: remoteResult.message()
                )
            }
        }
    }
}

package com.example.coinapp.test_utils

import com.example.coinapp.core.data.model.remote.RemoteOrderBook
import com.example.coinapp.core.domain.model.AppOrderBookData
import java.math.BigDecimal

object OrderBookUtils {
    const val PRICE = "1234567.89"
    const val SIZE = "9876543.21"

    val remoteOrderBook = RemoteOrderBook(
        symbolId = SymbolsUtils.ID,
        asks = listOf(
            RemoteOrderBook.Entry(
                price = PRICE,
                size = SIZE
            )
        ),
        bids = listOf(
            RemoteOrderBook.Entry(
                price = PRICE,
                size = SIZE
            )
        ),
    )

    val appOrderBookData = AppOrderBookData(
        symbolId = SymbolsUtils.ID,
        asks = listOf(
            AppOrderBookData.Entry(
                price = BigDecimal(PRICE),
                size = BigDecimal(SIZE)
            )
        ),
        bids = listOf(
            AppOrderBookData.Entry(
                price = BigDecimal(PRICE),
                size = BigDecimal(SIZE)
            )
        )
    )
}

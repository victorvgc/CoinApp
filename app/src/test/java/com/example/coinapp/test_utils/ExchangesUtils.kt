package com.example.coinapp.test_utils

import com.example.coinapp.core.data.model.remote.RemoteExchange
import com.example.coinapp.core.domain.model.AppExchange

object ExchangesUtils {
    const val ID = "TST"
    const val NAME = "TEST"

    val remoteExchangeList = listOf(
        RemoteExchange(
            id = ID,
            name = NAME,
            rank = 1,
        ),
        RemoteExchange(
            id = ID,
            name = NAME,
            rank = 2,
        ),
    )

    val appExchangeList = listOf(
        AppExchange(
            id = ID,
            name = NAME,
            rank = 1,
        ),
        AppExchange(
            id = ID,
            name = NAME,
            rank = 2,
        ),
    )
}

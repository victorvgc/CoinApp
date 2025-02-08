package com.example.coinapp.test_utils

import com.example.coinapp.core.data.model.remote.RemoteSymbol
import com.example.coinapp.core.domain.model.AppSymbol
import java.time.LocalDate
import java.time.Month

object SymbolsUtils {
    const val ID = "TST"
    const val DATE = "2025-02-05T00:00:00.0000000Z"

    val remoteSymbolList = listOf(
        RemoteSymbol(
            id = ID,
            exchangeId = ExchangesUtils.ID,
            baseAssetId = AssetsUtils.ID,
            quoteAssetId = AssetsUtils.ID,
            orderBookEndDate = DATE
        )
    )

    val appSymbolList = listOf(
        AppSymbol(
            id = ID,
            exchangeId = ExchangesUtils.ID,
            baseAssetId = AssetsUtils.ID,
            quoteAssetId = AssetsUtils.ID,
            orderBookEndDate = LocalDate.of(
                2025,
                Month.FEBRUARY,
                5
            )
        )
    )
}

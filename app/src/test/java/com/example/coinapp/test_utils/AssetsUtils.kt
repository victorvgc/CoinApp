package com.example.coinapp.test_utils

import com.example.coinapp.core.data.model.remote.RemoteAsset
import com.example.coinapp.core.domain.model.AppAsset
import java.math.BigDecimal

object AssetsUtils {
    const val ID = "TST"
    const val NAME = "Test"
    const val DAILY_VOLUME = 123.4
    const val ICON = "icon"

    val remoteAssetsList = listOf(
        RemoteAsset(
            id = ID,
            name = NAME,
            dailyVolume = DAILY_VOLUME,
            isCrypto = 1,
            icon = ICON
        ),
        RemoteAsset(
            id = ID,
            name = NAME,
            dailyVolume = DAILY_VOLUME,
            isCrypto = 0,
            icon = ICON
        ),
    )

    val appAssetsList = listOf(
        AppAsset(
            id = ID,
            name = NAME,
            dailyVolume = BigDecimal(DAILY_VOLUME),
            isCrypto = true,
            iconId = ICON
        ),
        AppAsset(
            id = ID,
            name = NAME,
            dailyVolume = BigDecimal(DAILY_VOLUME),
            isCrypto = false,
            iconId = ICON
        ),
    )
}

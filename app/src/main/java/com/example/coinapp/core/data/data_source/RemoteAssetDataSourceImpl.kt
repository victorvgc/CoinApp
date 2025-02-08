package com.example.coinapp.core.data.data_source

import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.domain.data_souce.AssetDataSource
import com.example.coinapp.core.domain.model.AppAsset
import com.example.coinapp.core.domain.model.ResultWrapper
import java.math.BigDecimal

class RemoteAssetDataSourceImpl(
    private val coinAppService: CoinAppService
) : AssetDataSource.Remote {
    override suspend fun getAssetsList(): ResultWrapper<List<AppAsset>> {
        return ResultWrapper.runCatching {
            val remoteResult = coinAppService.listAllAssets()

            if (remoteResult.isSuccessful) {
                val body = remoteResult.body().orEmpty()

                val assetList = body.mapNotNull { remote ->
                    if (remote.name.isNullOrEmpty())
                        null
                    else {
                        val price = if (remote.price.isNullOrEmpty())
                            BigDecimal.ONE
                        else
                            BigDecimal(remote.price)

                        AppAsset(
                            id = remote.id,
                            name = remote.name,
                            isCrypto = remote.isCrypto == 1,
                            iconId = remote.icon.orEmpty().replace("-", ""),
                            dailyVolume = BigDecimal(remote.dailyVolume),
                            price = price
                        )
                    }
                }

                return@runCatching assetList
            } else {
                throw Throwable(
                    message = remoteResult.errorBody()?.string() ?: remoteResult.message()
                )
            }
        }

    }
}

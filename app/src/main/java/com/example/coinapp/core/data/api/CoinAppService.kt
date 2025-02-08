package com.example.coinapp.core.data.api

import com.example.coinapp.core.data.model.remote.RemoteAsset
import com.example.coinapp.core.data.model.remote.RemoteExchange
import com.example.coinapp.core.data.model.remote.RemoteOrderBook
import com.example.coinapp.core.data.model.remote.RemoteSymbol
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MAX_ORDER_BOOK_ENTRIES = 50

interface CoinAppService {
    @GET("assets")
    suspend fun listAllAssets(): Response<List<RemoteAsset>>

    @GET("exchanges")
    suspend fun listAllExchanges(): Response<List<RemoteExchange>>

    @GET("symbols")
    suspend fun listSymbolsByAssetAndExchange(
        @Query("filter_asset_ID") baseAssetId: String,
        @Query("filter_exchange") exchangeId: String,
        @Query("filter_symbol_id") symbolId: String,
    ): Response<List<RemoteSymbol>>

    @GET("orderbooks/{symbol_id}/current")
    suspend fun getOrderBook(
        @Path("symbol_id") symbolId: String,
        @Query("limit_levels") limit: Int = MAX_ORDER_BOOK_ENTRIES
    ): Response<RemoteOrderBook>
}

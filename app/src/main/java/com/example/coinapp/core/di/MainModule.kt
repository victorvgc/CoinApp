package com.example.coinapp.core.di

import com.example.coinapp.BuildConfig
import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.data.data_source.RemoteAssetDataSourceImpl
import com.example.coinapp.core.data.data_source.RemoteExchangeDataSourceImpl
import com.example.coinapp.core.data.data_source.RemoteOrderBookDataSourceImpl
import com.example.coinapp.core.data.data_source.RemoteSymbolDataSourceImpl
import com.example.coinapp.core.data.repository.AssetRepositoryImpl
import com.example.coinapp.core.data.repository.ExchangeRepositoryImpl
import com.example.coinapp.core.data.repository.OrderBookRepositoryImpl
import com.example.coinapp.core.data.repository.SymbolRepositoryImpl
import com.example.coinapp.core.domain.data_souce.AssetDataSource
import com.example.coinapp.core.domain.data_souce.ExchangeDataSource
import com.example.coinapp.core.domain.data_souce.OrderBookDataSource
import com.example.coinapp.core.domain.data_souce.SymbolDataSource
import com.example.coinapp.core.domain.repository.AssetRepository
import com.example.coinapp.core.domain.repository.ExchangeRepository
import com.example.coinapp.core.domain.repository.OrderBookRepository
import com.example.coinapp.core.domain.repository.SymbolRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val mainModule = module {
    single<Retrofit> {
        val loggingInterceptor = HttpLoggingInterceptor {
            Timber.tag("OkHttp")
        }.also {
            it.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("X-CoinAPI-Key", BuildConfig.COIN_API_KEY)
                    .build()

                chain.proceed(request)
            }

        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
        }

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    single<CoinAppService> {
        get<Retrofit>().create(CoinAppService::class.java)
    }

    // data source
    factory<AssetDataSource.Remote> {
        RemoteAssetDataSourceImpl(
            coinAppService = get()
        )
    }

    factory<ExchangeDataSource.Remote> {
        RemoteExchangeDataSourceImpl(
            coinAppService = get()
        )
    }

    factory<SymbolDataSource.Remote> {
        RemoteSymbolDataSourceImpl(
            coinAppService = get()
        )
    }

    factory<OrderBookDataSource.Remote> {
        RemoteOrderBookDataSourceImpl(
            coinAppService = get()
        )
    }

    // repository
    factory<AssetRepository> {
        AssetRepositoryImpl(
            remoteAssetDataSource = get()
        )
    }

    factory<ExchangeRepository> {
        ExchangeRepositoryImpl(
            exchangeDataSource = get()
        )
    }

    factory<SymbolRepository> {
        SymbolRepositoryImpl(
            symbolDataSource = get()
        )
    }

    factory<OrderBookRepository> {
        OrderBookRepositoryImpl(
            orderBookDataSource = get()
        )
    }
}

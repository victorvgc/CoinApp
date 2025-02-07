package com.example.coinapp.core.di

import com.example.coinapp.BuildConfig
import com.example.coinapp.core.data.api.CoinAppService
import com.example.coinapp.core.data.data_source.RemoteAssetsDataSourceImpl
import com.example.coinapp.core.data.repository.AssetsRepositoryImpl
import com.example.coinapp.core.domain.data_souce.AssetsDataSource
import com.example.coinapp.core.domain.repository.AssetsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client.build())
            .build()
    }

    single<CoinAppService> {
        get<Retrofit>().create(CoinAppService::class.java)
    }

    // data source
    factory<AssetsDataSource.Remote> {
        RemoteAssetsDataSourceImpl(
            coinAppService = get()
        )
    }

    // repository
    factory<AssetsRepository> {
        AssetsRepositoryImpl(
            remoteAssetsDataSource = get()
        )
    }
}

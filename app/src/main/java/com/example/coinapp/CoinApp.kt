package com.example.coinapp

import android.app.Application
import timber.log.Timber

class CoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
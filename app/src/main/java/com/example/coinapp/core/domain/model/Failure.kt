package com.example.coinapp.core.domain.model

import timber.log.Timber

data class Failure(
    val throwable: Throwable,
) {
    init {
        Timber.tag("AppFailure")
        Timber.e(throwable)
    }
}

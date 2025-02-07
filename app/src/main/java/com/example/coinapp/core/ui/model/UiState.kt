package com.example.coinapp.core.ui.model

sealed class UiState<Data>(open val data: Data?, open val error: UiError?) {
    data class Success<T>(
        override val data: T
    ) : UiState<T>(
        data = data,
        error = null,
    )

    data class Error<T>(
        override val error: UiError,
    ) : UiState<T>(
        data = null,
        error = error
    )

    class Loading<T> : UiState<T>(
        data = null,
        error = null
    )
}
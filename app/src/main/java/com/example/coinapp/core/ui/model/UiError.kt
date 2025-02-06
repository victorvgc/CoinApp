package com.example.coinapp.core.ui.model

data class UiError(
    val message: String,
    val icon: ErrorIcon? = null,
    val onRetry: (() -> Unit)? = null
) {
    enum class ErrorIcon {
        ALERT, FAILURE
    }
}
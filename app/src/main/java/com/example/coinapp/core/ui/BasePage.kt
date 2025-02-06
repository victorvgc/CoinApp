package com.example.coinapp.core.ui

import androidx.compose.runtime.Composable
import com.example.coinapp.core.ui.model.UiState

@Composable
inline fun <reified T> BasePage(
    state: UiState<T>,
    content: @Composable (T) -> Unit
) {
    when (state) {
        is UiState.Error -> ErrorPage(state.error)
        is UiState.Loading -> LoadingPage()
        is UiState.Success -> content(state.data)
    }
}

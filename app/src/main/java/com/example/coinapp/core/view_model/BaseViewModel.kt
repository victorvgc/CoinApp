package com.example.coinapp.core.view_model

import androidx.lifecycle.ViewModel
import com.example.coinapp.core.ui.model.UiError
import com.example.coinapp.core.ui.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<Data, Action> : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Data>> =
        MutableStateFlow(UiState.Loading<Data>())
    val uiState = _uiState.asStateFlow()

    abstract fun onUiAction(action: Action)

    fun updateUiWithSuccess(data: Data) {
        _uiState.update {
            UiState.Success(data)
        }
    }

    fun updateUiWithError(error: UiError) {
        _uiState.update {
            UiState.Error(error)
        }
    }

    fun updateUiWithLoading() {
        _uiState.update {
            UiState.Loading<Data>()
        }
    }
}

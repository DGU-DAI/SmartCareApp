package com.dgu.smartcareapp.util

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>

    data class Success<T>(
        val data: T,
    ) : UiState<T>

    object Failure : UiState<Nothing>
}

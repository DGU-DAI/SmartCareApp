package com.dgu.smartcareapp.presentation.safeword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgu.smartcareapp.domain.usecase.SafeWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SafeWordViewModel @Inject constructor(
    private val safeWordUseCase: SafeWordUseCase
) : ViewModel() {

    private val _safeWords = MutableStateFlow<List<String>>(emptyList())
    val safeWords: StateFlow<List<String>> get() = _safeWords.asStateFlow()

    init {
        getAllSafeWords()
    }

    private fun getAllSafeWords() {
        viewModelScope.launch {
            safeWordUseCase.getAllSafeWords().collect { safeWordsList ->
                _safeWords.value = safeWordsList
            }
        }
    }

    fun insertSafeWord(safeWord: String) {
        viewModelScope.launch {
            safeWordUseCase.insertSafeWord(safeWord)
            getAllSafeWords()
        }
    }
}
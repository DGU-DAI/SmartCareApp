package com.dgu.smartcareapp.presentation.main

import android.content.Context
import android.telephony.SmsManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgu.smartcareapp.domain.entity.SmartCareStorage
import com.dgu.smartcareapp.domain.usecase.SafeWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val safeWordUseCase: SafeWordUseCase,
    private val smartCareStorage: SmartCareStorage
) : ViewModel() {

    private val _safeWords = MutableStateFlow<List<String>>(emptyList())
    val safeWords: StateFlow<List<String>> get() = _safeWords.asStateFlow()
    private val _isChecked = MutableStateFlow(smartCareStorage.isChecked)
    val isChecked: StateFlow<Boolean> get() = _isChecked

    init {
        getAllSafeWords()
    }

    fun setIsChecked(isChecked: Boolean) {
        _isChecked.value = isChecked
    }

    private fun getAllSafeWords() {
        viewModelScope.launch {
            safeWordUseCase.getAllSafeWords().collect { safeWordsList ->
                _safeWords.value = safeWordsList
            }
        }
    }

    fun handleSafeWordDetected(safeWord: String, context: Context) {
        sendSMS(smartCareStorage.phoneNumber, "세이프워드가 감지되었습니다: $safeWord", context)
        Log.d("aaa", "세이프워드 감지: $safeWord")
    }

    private fun sendSMS(phoneNumber: String, message: String, context: Context) {
        val smsManager = ContextCompat.getSystemService(context, SmsManager::class.java)
        smsManager?.sendTextMessage(phoneNumber, null, message, null, null)
    }
}
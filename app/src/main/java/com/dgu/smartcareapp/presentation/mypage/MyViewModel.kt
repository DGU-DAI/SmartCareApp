package com.dgu.smartcareapp.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
) : ViewModel() {
    private val _sideEffect = MutableSharedFlow<MySideEffect>(replay = 1, extraBufferCapacity = 1)
    val sideEffect: SharedFlow<MySideEffect>
        get() = _sideEffect.asSharedFlow()

    fun onSafeWordSettingsClicked() {
        viewModelScope.launch {
            _sideEffect.emit(MySideEffect.NavigateToSafeWordManage)
        }
    }

    fun onGuardianInfoClicked() {
        viewModelScope.launch {
            _sideEffect.emit(MySideEffect.NavigateToGuardianInfoManage)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun clearSideEffect() {
        viewModelScope.launch {
            _sideEffect.resetReplayCache()
        }
    }
}

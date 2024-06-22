package com.dgu.smartcareapp.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgu.smartcareapp.domain.entity.SmartCareStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val smartCareStorage: SmartCareStorage,
) : ViewModel() {
    private val _sideEffect = MutableSharedFlow<MySideEffect>(replay = 1, extraBufferCapacity = 1)
    val sideEffect: SharedFlow<MySideEffect>
        get() = _sideEffect.asSharedFlow()

    private val _isChecked = MutableStateFlow(smartCareStorage.isChecked)
    val isChecked: StateFlow<Boolean> get() = _isChecked

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

    fun setIsChecked(isChecked: Boolean) {
        smartCareStorage.isChecked = isChecked
    }

    fun getIsChecked() {
        _isChecked.value = smartCareStorage.isChecked
    }
}

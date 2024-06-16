package com.dgu.smartcareapp.presentation.CreationTodo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreationViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    fun onTodoTextValueChanged(text: String) {
        _uiState.update {
            if (text.length > 15) {
                it.copy(toDoTitle = text.substring(0, 15))
            } else {
                it.copy(toDoTitle = text)
            }
        }
    }

    fun confirmTodoTime(hour: Int, minute: Int) {
        _uiState.update {
            it.copy(toDoHour = hour, toDoMinute = minute)
        }
    }
}
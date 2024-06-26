package com.dgu.smartcareapp.presentation.CreationTodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.domain.usecase.TodoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoListUseCase: TodoListUseCase
) : ViewModel() {

    private val _todoList = MutableStateFlow<List<TodoList>>(emptyList())
    val todoList: StateFlow<List<TodoList>>
        get() = _todoList.asStateFlow()

    init {
        getTodoList()
    }

    fun getTodoList() {
        viewModelScope.launch {
            todoListUseCase.getTodoList().collect { todoList ->
                _todoList.value = todoList
            }
        }
    }

    fun insertTodoList(todoList: TodoList, onSuccess: (id: Int) -> Unit) {
        viewModelScope.launch {
            val id = todoListUseCase.insertTodoList(todoList)
            onSuccess.invoke(id.toInt())
        }
    }

    fun updateTodoList(todoId: Int, todoFinish: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            todoListUseCase.updateTodoFinish(todoId, todoFinish)
        }
    }

    fun delete() {
        viewModelScope.launch {
            todoListUseCase.delete()
        }
    }
}
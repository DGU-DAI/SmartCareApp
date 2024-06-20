package com.dgu.smartcareapp.presentation.CreationTodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.domain.usecase.TodoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoListUseCase: TodoListUseCase
): ViewModel() {

//    private val _todoList = MutableStateFlow<List<TodoList>>(emptyList())
    private val _todoList: MutableStateFlow<List<TodoList>> =
        MutableStateFlow(
            listOf(
                TodoList("할일1", 12, 0, false),
                TodoList("할일2", 1, 0, false)
            )
        )

    val todoList: StateFlow<List<TodoList>>
        get() = _todoList.asStateFlow()

    init {
//        getTodoList()
    }

    private fun getTodoList() {
        viewModelScope.launch {
            todoListUseCase.getTodoList().collect { todoList ->
                _todoList.value = todoList
            }
        }
    }

    fun insertTodoList(todoList: TodoList) {
        viewModelScope.launch {
            todoListUseCase.insertTodoList(todoList)
        }
    }
}
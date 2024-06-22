package com.dgu.smartcareapp.domain.usecase

import com.dgu.smartcareapp.data.model.roomdb.TodoListData
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoListUseCase @Inject constructor(
    private val todoListRepository: TodoListRepository
) {

    suspend fun getTodoList(): Flow<List<TodoList>> =
        todoListRepository.getTodoList()

    suspend fun insertTodoList(todoList: TodoList): Long =
        todoListRepository.insertTodoList(
            TodoListData(
                todoTitle = todoList.todoTitle,
                todoHour = todoList.todoHour,
                todoMinute = todoList.todoMinute,
                todoFinish = todoList.todoFinish,
                requestCode = todoList.requestCode
            )
        )

    suspend fun updateTodoFinish(todoId: Int, todoFinish: Boolean) {
        todoListRepository.updateTodoFinish(todoId, todoFinish)
    }

    suspend fun delete() {
        todoListRepository.delete()
    }
}
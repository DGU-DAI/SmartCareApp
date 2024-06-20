package com.dgu.smartcareapp.domain.repository

import com.dgu.smartcareapp.data.model.roomdb.TodoListData
import com.dgu.smartcareapp.domain.entity.TodoList
import kotlinx.coroutines.flow.Flow

interface TodoListRepository {

    suspend fun getTodoList(): Flow<List<TodoList>>

    suspend fun insertTodoList(todoListItem: TodoListData)

    suspend fun updateTodoFinish(todoId: Int, todoFinish: Boolean)

    suspend fun delete()
}
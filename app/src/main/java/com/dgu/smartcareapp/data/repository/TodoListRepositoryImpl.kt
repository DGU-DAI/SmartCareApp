package com.dgu.smartcareapp.data.repository

import com.dgu.smartcareapp.data.localdatasource.TodoListDao
import com.dgu.smartcareapp.data.model.roomdb.TodoListData
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.domain.repository.TodoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoListRepositoryImpl @Inject constructor(
    private val todoListDao: TodoListDao
): TodoListRepository {

    override suspend fun getTodoList(): Flow<List<TodoList>> =
        todoListDao.getTodoList().map { todoList ->
            todoList.map {
                TodoList(
                    id = it.id,
                    todoTitle = it.todoTitle,
                    todoHour = it.todoHour,
                    todoMinute = it.todoMinute,
                    todoFinish = it.todoFinish,
                    requestCode = it.requestCode
                )
            }
        }

    override suspend fun insertTodoList(todoListItem: TodoListData): Long =
        todoListDao.insertTodoList(todoListItem)

    override suspend fun updateTodoFinish(todoId: Int, todoFinish: Boolean) {
        todoListDao.updateTodoFinish(todoId, todoFinish)
    }

    override suspend fun delete() {
        todoListDao.deleteAllTodoList()
    }
}
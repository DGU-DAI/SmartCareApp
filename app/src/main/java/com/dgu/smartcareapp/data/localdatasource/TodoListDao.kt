package com.dgu.smartcareapp.data.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dgu.smartcareapp.data.model.roomdb.TodoListData
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {

    @Query("SELECT * FROM todolist")
    fun getTodoList(): Flow<List<TodoListData>>

    @Insert
    suspend fun insertTodoList(todoList: TodoListData)
}
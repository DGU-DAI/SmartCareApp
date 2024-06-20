package com.dgu.smartcareapp.data.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dgu.smartcareapp.data.model.roomdb.TodoListData
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {

    @Query("SELECT * FROM todo_list")
    fun getTodoList(): Flow<List<TodoListData>>

    @Insert
    suspend fun insertTodoList(todoList: TodoListData)

    @Query("UPDATE todo_list SET todoFinish = :todoFinish WHERE id = :todoId")
    fun updateTodoFinish(todoId: Int, todoFinish: Boolean)

    @Query("DELETE FROM todo_list")
    suspend fun deleteAllTodoList()
}
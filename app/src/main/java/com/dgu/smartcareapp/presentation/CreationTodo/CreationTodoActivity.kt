package com.dgu.smartcareapp.presentation.CreationTodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreationTodoActivity : ComponentActivity() {
    val viewModel: CreationViewModel by viewModels()
    val todoViewModel: TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            SmartCareAppTheme {
                CreationTodoScreen(
                    uiState = uiState,
                    onValueChanged = {
                        viewModel.onTodoTextValueChanged(it)
                    },
                    onButtonClick = {
                        todoViewModel.insertTodoList(
                            TodoList(
                                todoTitle = uiState.toDoTitle,
                                todoHour = uiState.toDoHour ?: 0,
                                todoMinute = uiState.toDoMinute ?: 0,
                                todoFinish = false
                            )
                        )
                        finish()
                    },
                    onConfirmToDoTime = { hour, minute ->
                        viewModel.confirmTodoTime(hour, minute)
                    },
                    onNavigationIconClick = {
                        finish()
                    }
                )
            }
        }
    }
}
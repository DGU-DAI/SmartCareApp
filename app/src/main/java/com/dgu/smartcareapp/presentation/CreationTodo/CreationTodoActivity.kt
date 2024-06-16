package com.dgu.smartcareapp.presentation.CreationTodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme

class CreationTodoActivity : ComponentActivity() {
    val viewModel: CreationViewModel by viewModels()
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
                        // todo 할일 추가
                    },
                    onConfirmToDoTime = { hour, minute ->
                        viewModel.confirmTodoTime(hour, minute)
                    },
                    onNavigationIconClick = {
                        // todo 뒤로가기
                    }
                )
            }
        }
    }
}
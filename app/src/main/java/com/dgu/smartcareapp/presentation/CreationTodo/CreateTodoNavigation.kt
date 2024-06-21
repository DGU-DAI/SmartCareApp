package com.dgu.smartcareapp.presentation.CreationTodo

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateTodo() {
    navigate(ToDo.ROUTE)
}

fun NavGraphBuilder.todoNavGraph(
    modifier: Modifier = Modifier,
//    onButtonClick: (time: String, toDoTitle: String) -> Unit = { _, _ -> },
    onNavigationIconClick: () -> Unit = {},
) {
    composable(route = ToDo.ROUTE) {
        CreationTodoScreen(
            modifier = modifier,
//            onButtonClick = onButtonClick,
            onNavigationIconClick = onNavigationIconClick
        )
    }
}

object ToDo {
    const val ROUTE = "TODO"
}
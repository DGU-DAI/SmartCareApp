package com.dgu.smartcareapp.presentation.CreationTodo

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateTodo() {
    navigate(ToDo.ROUTE)
}

fun NavGraphBuilder.todoNavGraph(
    modifier: Modifier = Modifier,
//    onButtonClick: (time: String, toDoTitle: String) -> Unit = { _, _ -> },
    onButtonClick: () -> Unit = {},
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    onNavigationIconClick: () -> Unit = {},
) {
    composable(route = ToDo.ROUTE) {
        CreationTodoScreen(
            modifier = modifier,
            onButtonClick = onButtonClick,
            context = context,
            lifecycleScope = lifecycleScope,
            onNavigationIconClick = onNavigationIconClick
        )
    }
}

object ToDo {
    const val ROUTE = "TODO"
}
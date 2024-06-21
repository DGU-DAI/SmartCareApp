package com.dgu.smartcareapp.presentation.home

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeNavGraph(
    modifier: Modifier,
    onClickMyPage: () -> Unit,
    onClickTodo: () -> Unit
) {
    composable(route = Home.Route) {
        Home(
            modifier = modifier,
            onClickMyPage = onClickMyPage,
            onClickTodo = onClickTodo
        )
    }
}

object Home {
    const val Route = "HOME"
}
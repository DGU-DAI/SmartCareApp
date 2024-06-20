package com.dgu.smartcareapp.presentation.home

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeNavGraph(
    modifier: Modifier,
    onClickMyPage: () -> Unit
) {
    composable(route = Home.Route) {
        Home(
            modifier = modifier,
            onClickMyPage = onClickMyPage
        )
    }
}

object Home {
    const val Route = "HOME"
}
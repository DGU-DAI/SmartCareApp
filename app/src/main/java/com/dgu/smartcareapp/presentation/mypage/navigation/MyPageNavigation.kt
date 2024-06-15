package com.dgu.smartcareapp.presentation.mypage.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dgu.smartcareapp.presentation.mypage.MyScreen

fun NavController.navigateMy() {
    navigate(MyRoute.ROUTE)
}

fun NavGraphBuilder.myNavGraph(
    modifier: Modifier,
    onRequestBack: () -> Unit,
    onSafeWordClick: () -> Unit,
    onGuardianInfoClick: () -> Unit,
) {
    composable(route = MyRoute.ROUTE) {
        MyScreen(
            modifier = modifier,
            onRequestBack = onRequestBack,
            onSafeWordClick = onSafeWordClick,
            onGuardianInfoClick = onGuardianInfoClick
        )
    }
}

object MyRoute {
    const val ROUTE = "my"
}

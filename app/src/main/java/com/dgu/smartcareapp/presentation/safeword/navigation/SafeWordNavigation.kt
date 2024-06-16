package com.dgu.smartcareapp.presentation.safeword.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dgu.smartcareapp.presentation.safeword.SafeWordScreen

fun NavController.navigateSafeWord() {
    navigate(SafeWordRoute.ROUTE)
}

fun NavGraphBuilder.safeWordNavGraph(
    modifier: Modifier,
    onRequestBack: () -> Unit,
) {
    composable(route = SafeWordRoute.ROUTE) {
        SafeWordScreen(
            modifier = modifier,
            onRequestBack = onRequestBack
        )
    }
}

object SafeWordRoute {
    const val ROUTE = "safe_word"
}

package com.dgu.smartcareapp.presentation.guardian.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dgu.smartcareapp.presentation.guardian.GuardianScreen

fun NavController.navigateGuardian() {
    navigate(GuardianRoute.ROUTE)
}

fun NavGraphBuilder.guardianNavGraph(
    modifier: Modifier,
    onRequestBack: () -> Unit,
) {
    composable(route = GuardianRoute.ROUTE) {
        GuardianScreen(
            modifier = modifier,
            onRequestBack = onRequestBack
        )
    }
}

object GuardianRoute {
    const val ROUTE = "guardian"
}

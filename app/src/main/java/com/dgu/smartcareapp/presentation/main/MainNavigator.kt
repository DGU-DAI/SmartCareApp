package com.dgu.smartcareapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dgu.smartcareapp.presentation.mypage.navigation.MyRoute
import com.dgu.smartcareapp.presentation.mypage.navigation.navigateMy

class MainNavigator(
    val navController: NavHostController,
) {

    //추후 홈으로 변경
    val startDestination = MyRoute.ROUTE
    fun navigateMyPage() {
        navController.navigateMy()
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}

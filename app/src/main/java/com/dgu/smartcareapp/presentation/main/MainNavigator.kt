package com.dgu.smartcareapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dgu.smartcareapp.presentation.CreationTodo.navigateTodo
import com.dgu.smartcareapp.presentation.home.Home
import com.dgu.smartcareapp.presentation.mypage.navigation.navigateMy
import com.dgu.smartcareapp.presentation.safeword.navigation.navigateSafeWord

class MainNavigator(
    val navController: NavHostController,
) {

    //추후 홈으로 변경
    val startDestination = Home.Route
    fun navigateMyPage() {
        navController.navigateMy()
    }

    fun navigateToDo() {
        navController.navigateTodo()
    }

    fun navigateSafeWord() {
        navController.navigateSafeWord()
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}

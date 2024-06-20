package com.dgu.smartcareapp.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import com.dgu.smartcareapp.presentation.home.homeNavGraph
import com.dgu.smartcareapp.presentation.mypage.navigation.myNavGraph
import com.dgu.smartcareapp.presentation.safeword.navigation.safeWordNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {

    Scaffold(
        content = { paddingValue ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startDestination,
                ) {
                    homeNavGraph(
                        modifier = Modifier.padding(paddingValue),
                        onClickMyPage = { navigator.navigateMyPage() }
                    )
                    myNavGraph(
                        modifier = Modifier.padding(paddingValue),
                        onSafeWordClick = { navigator.navigateSafeWord() },
                        onGuardianInfoClick = {},
                        onRequestBack = { navigator.navController.popBackStack() }
                    )
                    safeWordNavGraph(
                        modifier = Modifier.padding(paddingValue),
                        onRequestBack = { navigator.navController.popBackStack() }
                    )
                }
            }
        },
    )
}
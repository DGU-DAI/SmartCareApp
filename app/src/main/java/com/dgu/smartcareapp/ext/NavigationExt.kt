package com.dgu.smartcareapp.ext

import androidx.navigation.NavController

fun NavController.navigateClear(route: String) = navigate(route) {
    popUpTo(graph.id) {
        inclusive = true
    }
}

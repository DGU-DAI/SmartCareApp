package com.dgu.smartcareapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SmartCareAppTheme(
    typography: SCATypography = SmartCardAppTheme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides typography
    ) {
        MaterialTheme(content = content)
    }
}

val LocalTypography = staticCompositionLocalOf { SCATypography() }

object SmartCardAppTheme {
    val typography: SCATypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
}
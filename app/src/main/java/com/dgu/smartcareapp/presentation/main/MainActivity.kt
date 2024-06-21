package com.dgu.smartcareapp.presentation.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
import dagger.hilt.android.AndroidEntryPoint

val LocalDeviceSizeComposition = staticCompositionLocalOf {
    com.dgu.smartcareapp.presentation.main.DeviceSize.MEDIUM
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            val deviceWidth = applicationContext?.resources?.displayMetrics?.widthPixels ?: 0

            SmartCareAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    CompositionLocalProvider(
                        LocalDeviceSizeComposition provides DeviceSize.of(
                            deviceWidth
                        )
                    ) {
                        MainScreen(navigator, this)
                    }
                }
            }
        }
    }
}


enum class DeviceSize(val minWidthSize: Int) {
    BIG(1840), // Pixel Fold 기준
    MEDIUM(1080), // Android Studio Medium Phone 기준
    SMALL(720); // Android Studio Small Phone 기준

    companion object {
        fun of(deviceWidth: Int): DeviceSize = when {
            BIG.minWidthSize <= deviceWidth -> BIG
            MEDIUM.minWidthSize <= deviceWidth -> MEDIUM
            else -> SMALL
        }
    }
}

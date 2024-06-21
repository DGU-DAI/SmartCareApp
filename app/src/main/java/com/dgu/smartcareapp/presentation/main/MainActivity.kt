package com.dgu.smartcareapp.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.dgu.smartcareapp.alarm.AlarmManager
import com.dgu.smartcareapp.component.AlarmDialog
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

val LocalDeviceSizeComposition = staticCompositionLocalOf {
    com.dgu.smartcareapp.presentation.main.DeviceSize.MEDIUM
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // todo rememberSaveble로 수정
    val isShow = mutableStateOf(false)
    val toDoTitle: MutableState<String?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            val deviceWidth = applicationContext?.resources?.displayMetrics?.widthPixels ?: 0

            val lifecycleOwner = LocalLifecycleOwner.current

            // 이 부분 확인해보기
            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_RESUME) {
                        lifecycleScope.launch {
                            // 가장 최근 알람을 받아 옴
                            AlarmManager.alarm.collectLatest {
                                Log.d("dana", "알람왔다!")
                                isShow.value = true
                                toDoTitle.value = it
                            }
                        }
                    }
                }
                lifecycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }

            SmartCareAppTheme {

                if (isShow.value) {
                    toDoTitle.value?.let {
                        AlarmDialog(
                            toDoTitle = it,
                            onConfirm = { isShow.value = false }
                        )
                    }
                }

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

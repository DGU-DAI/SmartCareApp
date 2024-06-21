package com.dgu.smartcareapp.presentation.CreationTodo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.dgu.smartcareapp.alarm.AlarmManager
import com.dgu.smartcareapp.alarm.AlarmReceiver
import com.dgu.smartcareapp.alarm.AlarmUtils
import com.dgu.smartcareapp.component.AlarmDialog
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreationTodoActivity : ComponentActivity() {
    val viewModel: CreationViewModel by viewModels()
    val todoViewModel: TodoViewModel by viewModels()
    lateinit var alarmUtils: AlarmUtils
    lateinit var alarmReceiver: AlarmReceiver

    // todo rememberSaveble로 수정
    val isShow = mutableStateOf(false)
    val toDoTitle: MutableState<String?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        alarmUtils = AlarmUtils(this)
        alarmReceiver = AlarmReceiver()

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
                Box(modifier = Modifier.fillMaxSize()) {

                    if (isShow.value) {
                        toDoTitle.value?.let {
                            AlarmDialog(
                                toDoTitle = it,
                                onConfirm = { isShow.value = false }
                            )
                        }
                    }

                    CreationTodoScreen(
//                        uiState = uiState,
//                        onValueChanged = {
//                            viewModel.onTodoTextValueChanged(it)
//                        },
                        onButtonClick = { time, toDoTitle ->
                            // todo 할일 추가 (로컬에서)
                            // todo 로컬에서 추가 onSuccess 한 후에 알람 등록되는 거로 수정
                            val (hour, minute) = time.split(":").map { it.toInt() }

                            // request code의 유일성을 위해 랜덤함수를 사용 -> 차후에 리팩토링
                            val randomRequestCode = (1..100000) // 1~100000 범위에서 알람코드 랜덤으로 생성
                            val alarmCode = randomRequestCode.random()

                            alarmUtils.setAlarm(hour, minute, toDoTitle, alarmCode)

                            todoViewModel.insertTodoList(
                                TodoList(
                                    todoTitle = uiState.toDoTitle,
                                    todoHour = uiState.toDoHour ?: 0,
                                    todoMinute = uiState.toDoMinute ?: 0,
                                    todoFinish = false
                                )
                            )
                            finish()
                        },
//                        onConfirmToDoTime = { hour, minute ->
//                            viewModel.confirmTodoTime(hour, minute)
//                        },
//                        onNavigationIconClick = {
//                            finish()
//                        }
                    )
                }
            }
        }
    }
}
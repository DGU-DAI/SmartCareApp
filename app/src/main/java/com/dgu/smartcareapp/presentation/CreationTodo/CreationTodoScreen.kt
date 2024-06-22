package com.dgu.smartcareapp.presentation.CreationTodo

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dgu.smartcareapp.R
import com.dgu.smartcareapp.alarm.AlarmUtils
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.ui.theme.mainGrey
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.regular14
import com.dgu.smartcareapp.ui.theme.semiBold16

@SuppressLint("UnrememberedMutableState")
@Composable
fun CreationTodoScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    context: Context,
    onNavigationIconClick: () -> Unit = {},
    viewModel: CreationViewModel = hiltViewModel(),
    todoViewModel: TodoViewModel = hiltViewModel()
) {
    lateinit var alarmUtils: AlarmUtils

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val toDoTextEnabled by remember(key1 = uiState.toDoTitle) {
        derivedStateOf(uiState.toDoTitle::isNotEmpty)
    }

    val isTimeInputDialogShow: MutableState<Boolean> = mutableStateOf(false)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CreationTodoTopBar(
                title = uiState.appBarTitle,
                onNavigationIconClick = onNavigationIconClick
            )
        }
    ) { paddingValues ->

        alarmUtils = AlarmUtils(context)

        // timeInputDialog
        Box(modifier = Modifier.fillMaxSize()) {
            CreationTodoTimeInputDialog(
                uiData = uiState.timeInputDialogUiData.copy(
                    isShow = isTimeInputDialogShow.value
                ),
                onClickCancel = {
                    isTimeInputDialogShow.value = false
                },
                onClickConfirm = { hour, minute ->
//                    onConfirmToDoTime(hour, minute)
                    viewModel.confirmTodoTime(hour, minute)
                    isTimeInputDialogShow.value = false
                }
            )
        }

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.TopStart)
            ) {
                Title(
                    modifier = Modifier.padding(top = 40.dp),
                    title = "앞으로 도전해볼 할 일을 추가해볼까요? *"
                )

                CreationTodoTextField(
                    text = uiState.toDoTitle,
//                    onValueChanged = onValueChanged,
                    viewModel = viewModel,
                    enabled = toDoTextEnabled
                )

                TextFieldCount(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    textCount = uiState.toDoTitle.length,
                    maxTextCount = 15
                )

                Title(
                    modifier = Modifier.padding(top = 40.dp),
                    title = "할 일을 언제 수행할까요? *"
                )

                TimeField(
                    time = uiState.selectedTimeText,
                    openTimeInputDialog = {
                        isTimeInputDialogShow.value = true
                    }
                )
            }

            CreationTodoButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                enabled = uiState.toDoTitle.isNotBlank() && uiState.selectedTimeText.isNotBlank(),
                todoViewModel = todoViewModel,
                uiState = uiState,
                onButtonClick = onButtonClick,
                alarmUtils = alarmUtils
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreationTodoTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigationIconClick: () -> Unit = {}
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = semiBold16(),
                )
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier.clickable { onNavigationIconClick.invoke() },
                    imageVector = ImageVector.vectorResource(R.drawable.icn_left),
                    tint = mainGrey,
                    contentDescription = null
                )
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(mainGrey),
        )
    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = title,
        style = semiBold16()
    )
}

@Composable
private fun CreationTodoTextField(
    modifier: Modifier = Modifier,
    text: String,
//    onValueChanged: (String) -> Unit,
    viewModel: CreationViewModel,
    enabled: Boolean,
) {
    BasicTextField(
        value = text,
        onValueChange = {
//            onValueChanged.invoke(it)
            viewModel.onTodoTextValueChanged(it)
        },
        textStyle = semiBold16(),
    ) { innerTextField ->
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 3.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (text.isBlank()) {
                    Text(
                        text = "ex) 약먹기",
                        color = mainGrey,
                        style = semiBold16()
                    )
                }

                innerTextField()
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(if (enabled) Color.Black else mainGrey)
            )
        }
    }
}

@Composable
private fun TextFieldCount(
    modifier: Modifier = Modifier,
    textCount: Int = 0,
    maxTextCount: Int
) {
    Text(
        modifier = modifier,
        text = "($textCount/$maxTextCount)",
        style = regular14(),
        color = mainGrey
    )
}

@Composable
private fun TimeField(
    modifier: Modifier = Modifier,
    time: String = "",
    openTimeInputDialog: () -> Unit = {}
) {
    Column(modifier = modifier
        .clickable { openTimeInputDialog.invoke() }) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp, start = 3.dp),
            text = time.takeIf { time.isNotBlank() } ?: "ex) 11:00",
            style = semiBold16(),
            color = Color.Black.takeIf { time.isNotBlank() } ?: mainGrey
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black.takeIf { time.isNotBlank() } ?: mainGrey))
    }
}

@Composable
private fun CreationTodoButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
    todoViewModel: TodoViewModel,
    uiState: UiState,
    enabled: Boolean = false,
    alarmUtils: AlarmUtils
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) mainOrange else mainGrey,
            contentColor = Color.White
        ),
//        onClick = onButtonClick
        onClick = {
            // todo 할일 추가 (로컬에서)
            // todo 로컬에서 추가 onSuccess 한 후에 알람 등록되는 거로 수정
            val (hour, minute) = uiState.selectedTimeText.split(":").map { it.toInt() }

            // request code의 유일성을 위해 랜덤함수를 사용 -> 차후에 리팩토링
            val randomRequestCode = (1..100000) // 1~100000 범위에서 알람코드 랜덤으로 생성
            val alarmCode = randomRequestCode.random()

            todoViewModel.insertTodoList(
                TodoList(
                    todoTitle = uiState.toDoTitle,
                    todoHour = uiState.toDoHour ?: 0,
                    todoMinute = uiState.toDoMinute ?: 0,
                    todoFinish = false,
                    requestCode = 0
                )
            ) { id ->
                alarmUtils.setAlarm(
                    hour,
                    minute,
                    uiState.toDoTitle,
                    alarmCode,
                    id
                )
            }

            onButtonClick()
        }
    ) {
        Text(text = "완료")
    }
}

@Immutable
data class UiState(
    val appBarTitle: String = "할일 추가",
    val toDoTitle: String = "",
    val toDoHour: Int? = null,
    val toDoMinute: Int? = null,
    val timeInputDialogUiData: TimeInputDialogUiData = TimeInputDialogUiData()
) {
    val selectedTimeText: String
        get() = if (toDoHour != null && toDoMinute != null) {
            String.format("%02d:%02d", toDoHour, toDoMinute)
        } else {
            ""
        }
}

//@Preview(showBackground = true)
//@Composable
//private fun CreationTodoScreenPreview() {
//    SmartCareAppTheme {
//        CreationTodoScreen(
//            uiState = UiState(
//                timeInputDialogUiData = TimeInputDialogUiData().copy(isShow = true)
//            )
//        )
//    }
//}
package com.dgu.smartcareapp.presentation.CreationTodo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.semiBold14

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationTodoTimeInputDialog(
    modifier: Modifier = Modifier,
    uiData: TimeInputDialogUiData,
    onClickConfirm: (hour: Int, minute: Int) -> Unit = { _, _ -> },
    onClickCancel: () -> Unit = {}
) {
    val timePickerState = rememberTimePickerState(
        initialHour = uiData.selectedHour ?: 0,
        initialMinute = uiData.selectedMinute ?: 0,
        is24Hour = false
    )

    if (uiData.isShow) {
        Dialog(
            onDismissRequest = { onClickCancel.invoke() }) {
            Card(
                shape = RoundedCornerShape(size = 18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                TimeInput(
                    modifier = Modifier.padding(16.dp),
                    state = timePickerState,
                    colors = TimePickerDefaults.colors()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, end = 24.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                onClickConfirm.invoke(
                                    timePickerState.hour,
                                    timePickerState.minute
                                )
                            },
                        text = "확인",
                        style = semiBold14(),
                        color = mainOrange,
                    )
                }
            }
        }
    }
}

@Stable
data class TimeInputDialogUiData(
    val selectedHour: Int? = null,
    val selectedMinute: Int? = null,
    val isShow: Boolean = false
)

@Preview(showBackground = true)
@Composable
private fun CreationTodoTimePickerDialogPreview() {
    SmartCareAppTheme {
        CreationTodoTimeInputDialog(
            uiData = TimeInputDialogUiData(
                selectedMinute = null,
                selectedHour = null,
                isShow = true
            )
        )
    }
}
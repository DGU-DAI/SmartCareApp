package com.dgu.smartcareapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.semiBold14
import com.dgu.smartcareapp.ui.theme.semiBold16

@Composable
fun AlarmDialog(
    toDoTitle: String = "",
    onConfirm: () -> Unit = {}
) {
    Dialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(18.dp))
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = toDoTitle,
                style = semiBold16()
            )

            Box(
                modifier = Modifier.padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable { onConfirm.invoke() },
                    text = "완료했어요",
                    style = semiBold14(),
                    color = mainOrange
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlarmDialogPreview() {
    SmartCareAppTheme {
        AlarmDialog(
            toDoTitle = "한자두자세자네자다섯자여섯"
        )
    }
}
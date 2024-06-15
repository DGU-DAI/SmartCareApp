package com.dgu.smartcareapp.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dgu.smartcareapp.ui.theme.black
import com.dgu.smartcareapp.ui.theme.mainGrey
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.semiBold14
import com.dgu.smartcareapp.ui.theme.semiBold16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    showDialog: MutableState<Boolean>,
    title: String,
    hint: String,
    leftButtonText: String,
    rightButtonText: String,
    showTextField: Boolean = true,
    textFieldValue: String = "",
    onLeftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
) {
    if (showDialog.value) {
        var inputText by remember { mutableStateOf(textFieldValue) }
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Surface(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize(),
                shape = RoundedCornerShape(18.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(
                        text = title,
                        style = semiBold16(),
                        color = black,
                    )
                    if (showTextField) {
                        TextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            placeholder = {
                                Text(
                                    text = hint,
                                    style = semiBold16(),
                                    color = mainGrey
                                )
                            },
                            textStyle = semiBold16(),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = black,
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = mainOrange,
                                unfocusedIndicatorColor = mainGrey,
                                cursorColor = mainOrange
                            ),
                            maxLines = 1,
                            singleLine = true,
                        )
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier
                                .clickable(onClick = onLeftButtonClick)
                                .padding(vertical = 8.dp),
                            text = leftButtonText,
                            style = semiBold14(),
                            color = mainGrey,
                        )
                        Spacer(modifier = Modifier.width(28.dp))
                        Text(
                            modifier = Modifier
                                .clickable(onClick = onRightButtonClick)
                                .padding(vertical = 8.dp),
                            text = rightButtonText,
                            style = semiBold14(),
                            color = mainOrange,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomAlertDialogPreview() {
    val showDialog = remember { mutableStateOf(true) }
    CustomAlertDialog(
        showDialog = showDialog,
        title = "새로운 세이프 워드 추가하기",
        hint = "ex) 도와줘",
        leftButtonText = "취소",
        rightButtonText = "저장하기",
        showTextField = true,
        textFieldValue = "",
        onLeftButtonClick = {},
        onRightButtonClick = {},
    )
}

package com.dgu.smartcareapp.presentation.safeword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgu.smartcareapp.R
import com.dgu.smartcareapp.component.CustomAlertDialog
import com.dgu.smartcareapp.ui.theme.black
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.semiBold16
import com.dgu.smartcareapp.ui.theme.semiBold20


@Composable
fun SafeWordScreen(
    modifier: Modifier = Modifier,
    onRequestBack: () -> Unit,
    safeWordViewModel: SafeWordViewModel = hiltViewModel()
) {
    val safeWords by safeWordViewModel.safeWords.collectAsState()
    Column {
        SafeWordAppBar(onRequestBack = onRequestBack, modifier = modifier)
        SafeWordList(safeWordList = safeWords)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SafeWordAppBar(onRequestBack: () -> Unit, modifier: Modifier) {
    Column {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onRequestBack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "뒤로가기"
                        )
                    }
                },
                title = {},
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "세이프 워드 설정",
                    style = semiBold16(),
                    color = Color.Black,
                )
            }
        }
        Divider(thickness = 2.dp)
    }
}


@Composable
fun SafeWordList(
    safeWordList: List<String>,
    safeWordViewModel: SafeWordViewModel = hiltViewModel()
) {
    val newSafeWord = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 42.dp)
            .padding(horizontal = 15.dp)
    ) {
        Text(text = "세이프 워드 리스트", style = semiBold20(), color = black)
        Spacer(modifier = Modifier.height(7.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(safeWordList.size) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = safeWordList[it],
                        modifier = Modifier.padding(start = 3.dp),
                        style = semiBold16(),
                        color = black
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(thickness = 1.dp)
                }
            }

        }
        FloatingActionButton(
            onClick = { showDialog.value = true },
            containerColor = mainOrange,
            contentColor = Color.White,
            shape = androidx.compose.foundation.shape.CircleShape,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "추가"
            )
        }

        if (showDialog.value) {
            CustomAlertDialog(
                showDialog = showDialog,
                title = "새로운 세이프 워드 추가하기",
                hint = "ex) 도와줘",
                leftButtonText = "",
                rightButtonText = "저장하기",
                showTextField = true,
                textFieldValue = newSafeWord.value,
                onTextFieldValueChange = { newSafeWord.value = it },
                onLeftButtonClick = {},
                onRightButtonClick = {
                    if (newSafeWord.value.isEmpty()) return@CustomAlertDialog
                    else {
                        safeWordViewModel.insertSafeWord(newSafeWord.value)
                        showDialog.value = false
                    }
                },
            )
        }
    }
}
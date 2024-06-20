package com.dgu.smartcareapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dgu.smartcareapp.R
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
import com.dgu.smartcareapp.ui.theme.mainGrey
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.semiBold16

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onClickMyPage: () -> Unit,
//    onClickTodo: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopBar(onClickMyPage)
        },
        floatingActionButton = {
            FloatingActionBtn {
                //
            }
        }
    ) { contentPadding ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            //
        }
    }
}

@Composable
fun TopBar(onClickMyPage: () -> Unit) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "00님 오늘도 화이팅 하세요!",
                    style = semiBold16()
                )
            },
            actions = {
                IconButton(onClick = {onClickMyPage()}) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_mypage),
                        tint = mainGrey,
                        contentDescription = null
                    )
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .height(1.dp)
                .background(mainGrey)
        )
    }
}

@Composable
fun FloatingActionBtn(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = {},
        text = { Text(text = "일정 추가") },
        containerColor = mainOrange,
        contentColor = Color.White,
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    SmartCareAppTheme {
        Home (onClickMyPage = {})
    }
}
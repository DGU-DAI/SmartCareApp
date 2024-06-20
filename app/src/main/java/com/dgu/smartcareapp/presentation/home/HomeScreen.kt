package com.dgu.smartcareapp.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgu.smartcareapp.R
import com.dgu.smartcareapp.data.model.roomdb.TodoListData
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.presentation.CreationTodo.TodoViewModel
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
import com.dgu.smartcareapp.ui.theme.black
import com.dgu.smartcareapp.ui.theme.mainGrey
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.regular16
import com.dgu.smartcareapp.ui.theme.semiBold16
import com.dgu.smartcareapp.ui.theme.semiBold24

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    modifier: Modifier = Modifier,
    onClickMyPage: () -> Unit,
    todoViewModel: TodoViewModel = hiltViewModel()
//    onClickTodo: () -> Unit
) {
    val todoLists by todoViewModel.todoList.collectAsState()

    Scaffold (
        topBar = {
            TopBar(onClickMyPage, modifier)
                 },
        content = { paddingValues ->
            TodoList(todoLists)
        },
        floatingActionButton = {
            FloatingActionBtn {
                //
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onClickMyPage: () -> Unit, modifier: Modifier) {
    Column {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "00님 오늘도 화이팅 하세요!",
                        style = semiBold16()
                    )
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_mypage),
                            tint = mainGrey,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
            )
        }
        Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(mainGrey)
            )
    }
}

@Composable
fun TodoList(
    todoList: List<TodoList>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 7.dp, horizontal = 12.dp)
    ) {
        Log.d("home", "[todoList] -> ${todoList}")
        items(todoList.size) {
            Card(
                Modifier
                    .padding(vertical = 7.dp)
                    .background(color = Color.White)
                    .fillMaxWidth(),
                border = BorderStroke(width = 2.dp, color = mainOrange),
                shape = RoundedCornerShape(corner = CornerSize(12.dp))
            ) {
                Row {
                    Column (
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "${todoList[it].todoHour}:${todoList[it].todoMinute}",
                            style = regular16(),
                            color = Color.Black
                        )
                        Text(
                            text = todoList[it].todoTitle,
                            style = semiBold24(),
                            color = Color.Black)
                    }
//                    IconButton(
//                        onClick = {},
//                        modifier = Modifier.padding(end = 12.dp)) {
//                        Icon(
//                            imageVector = ImageVector.vectorResource(id = R.drawable.icn_check),
//                            tint = mainGrey,
//                            contentDescription = null
//                        )
//                    }
                }
            }
        }
    }
}

//@Composable
//fun listLayout() {
//    Card(
//        Modifier
//            .padding(vertical = 7.dp)
//            .border(width = 2.dp, color = mainOrange)
//            .background(color = Color.White)
//            .fillMaxWidth()
//    ) {
//        Column {
//            Text(text =)
//        }
//    }
//}
//

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
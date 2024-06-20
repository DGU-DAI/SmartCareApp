package com.dgu.smartcareapp.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgu.smartcareapp.R
import com.dgu.smartcareapp.domain.entity.TodoList
import com.dgu.smartcareapp.presentation.CreationTodo.CreationTodoActivity
import com.dgu.smartcareapp.presentation.CreationTodo.TodoViewModel
import com.dgu.smartcareapp.ui.theme.SmartCareAppTheme
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
) {

    val todoLists by todoViewModel.todoList.collectAsState()
//    var todoLists = listOf<TodoList>()
//    LaunchedEffect(Unit) {
//        todoViewModel.todoList.collect {
//            Log.d("테스트", "[todoList] activity -> $it")
//            todoLists = it
//        }
//    }
    Log.d("테스트", "[todoList] activity -> $todoLists")

    Scaffold(
        topBar = {
            TopBar(onClickMyPage, modifier)
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                TodoList(todoLists)
            }
        },
        floatingActionButton = {
            FloatingActionBtn()
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
                    IconButton(onClickMyPage) {
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
        items(todoList.size) {
            todoListLayout(todoList = todoList, index = it)
        }
    }
}

@Composable
fun todoListLayout(
    todoList: List<TodoList>,
    index: Int
) {
    Card(
        Modifier
            .padding(vertical = 7.dp)
            .background(color = Color.White)
            .fillMaxWidth(),
        border = BorderStroke(width = 2.dp, color = mainOrange),
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 25.dp, bottom = 22.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "${todoList[index].todoHour}:${todoList[index].todoMinute}",
                    style = regular16(),
                    color = Color.Black
                )
                Text(
                    text = todoList[index].todoTitle,
                    style = semiBold24(),
                    color = Color.Black
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.CenterVertically)

            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icn_check),
                    tint = if (todoList[index].todoFinish) mainOrange else mainGrey,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun FloatingActionBtn() {
    val context = LocalContext.current

    ExtendedFloatingActionButton(
        onClick = {
            val intent = Intent(context, CreationTodoActivity::class.java)
            context.startActivity(intent)
        },
        icon = {},
        text = { Text(text = "일정 추가") },
        containerColor = mainOrange,
        contentColor = Color.White,
    )
}
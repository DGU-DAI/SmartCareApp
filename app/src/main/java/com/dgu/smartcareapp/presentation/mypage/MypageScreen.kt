package com.dgu.smartcareapp.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.dgu.smartcareapp.R
import com.dgu.smartcareapp.ui.theme.black
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.semiBold16
import kotlinx.coroutines.flow.distinctUntilChanged


@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    onRequestBack: () -> Unit,
    onSafeWordClick: () -> Unit,
    onGuardianInfoClick: () -> Unit,
    myPageViewModel: MyViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Column {
        MyPageAppBar(onRequestBack = onRequestBack, modifier = modifier)
        SettingsScreen()
    }

    LaunchedEffect(myPageViewModel.sideEffect, lifecycleOwner) {
        myPageViewModel.sideEffect.flowWithLifecycle(
            lifecycle = lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
            .distinctUntilChanged()
            .collect { sideEffect ->
                when (sideEffect) {
                    MySideEffect.NavigateToSafeWordManage -> onSafeWordClick()

                    MySideEffect.NavigateToGuardianInfoManage -> onGuardianInfoClick()
                }
            }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageAppBar(onRequestBack: () -> Unit, modifier: Modifier) {
    Column {
        TopAppBar(
            {
                Row(
                    modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "마이페이지", style = semiBold16(), color = black)
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    onRequestBack()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "뒤로가기"
                    )
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.White
            )

        )
        Divider(thickness = 2.dp)
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        SettingSwitchItem(title = "세이프 워드 기능")
        Divider()
        SettingNavigationItem(
            title = "세이프 워드 설정",
        )
        Divider()
        SettingNavigationItem(
            title = "보호자 정보 수정",
        )
        Divider()
    }
}

@Composable
fun SettingSwitchItem(title: String) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 31.dp, bottom = 19.dp)
            .padding(start = 11.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = semiBold16(), color = black)
        Spacer(Modifier.weight(1f))
        Switch(
            modifier = Modifier.scale(0.8f),
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = mainOrange,
                uncheckedThumbColor = Color.Black,
                checkedTrackColor = Color.White.copy(alpha = 0.7f),
                uncheckedTrackColor = Color.White.copy(alpha = 0.7f),
                uncheckedBorderColor = Color.Black,
                checkedBorderColor = mainOrange
            )
        )
    }
}

@Composable
fun SettingNavigationItem(
    title: String,
    myPageViewModel: MyViewModel = hiltViewModel(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 19.dp)
            .padding(start = 11.dp)
            .clickable {
                if (title == "세이프 워드 설정") {
                    myPageViewModel.onSafeWordSettingsClicked()
                } else {
                    myPageViewModel.onGuardianInfoClicked()
                }
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = semiBold16(), color = black)
        Spacer(Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_right),
            contentDescription = "",
            tint = Color.Gray
        )
    }
}


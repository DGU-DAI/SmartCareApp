package com.dgu.smartcareapp.presentation.guardian

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgu.smartcareapp.R
import com.dgu.smartcareapp.ext.addFocusCleaner
import com.dgu.smartcareapp.ui.theme.black
import com.dgu.smartcareapp.ui.theme.mainGrey
import com.dgu.smartcareapp.ui.theme.mainOrange
import com.dgu.smartcareapp.ui.theme.semiBold16
import com.dgu.smartcareapp.ui.theme.semiBold24


@Composable
fun GuardianScreen(
    modifier: Modifier = Modifier,
    onRequestBack: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
    ) {
        GuardianAppBar(onRequestBack = onRequestBack, modifier = modifier)
        Spacer(modifier = Modifier.height(145.dp))
        GuardianInfo(onRequestBack = onRequestBack)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardianAppBar(onRequestBack: () -> Unit, modifier: Modifier) {
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
                colors = TopAppBarDefaults.topAppBarColors(Color.White)
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "보호자 정보 수정",
                    style = semiBold16(),
                    color = Color.Black,
                )
            }
        }
        HorizontalDivider(thickness = 2.dp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardianInfo(
    modifier: Modifier = Modifier,
    onRequestBack: () -> Unit,
    guardianViewModel: GuardianViewModel = hiltViewModel()
) {
    var phoneNumber by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        phoneNumber = guardianViewModel.phoneNumber.value
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 26.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "보호자 정보",
            style = semiBold24(),
            color = Color.Black
        )

        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            textStyle = semiBold16(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = black,
                unfocusedTextColor = black,
                cursorColor = black,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = mainOrange,
                unfocusedIndicatorColor = mainGrey
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
            singleLine = true,
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = {
                guardianViewModel.setPhoneNumber(phoneNumber)
                onRequestBack()
            },
            enabled = phoneNumber.length == 11
        ) {

        }
    }
}
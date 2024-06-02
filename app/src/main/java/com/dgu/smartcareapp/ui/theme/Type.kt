package com.dgu.smartcareapp.ui.theme

import android.content.res.Resources.Theme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dgu.smartcareapp.R
import org.w3c.dom.Text

val FontFamily = FontFamily(
    Font(R.font.petandard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold)
)


//폰트 패밀리가 폰트웨이트에 따라 알아서 선택이 된다.
data class SCATypography(
    val semiBold24: TextStyle = TextStyle(
        fontFamily = com.dgu.smartcareapp.ui.theme.FontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 38.sp,
        fontSize = 24.sp
    ),
    val semiBold20: TextStyle = TextStyle(
        fontFamily = com.dgu.smartcareapp.ui.theme.FontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        fontSize = 20.sp
    ),
    val semiBold16: TextStyle = TextStyle(
        fontFamily = com.dgu.smartcareapp.ui.theme.FontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        fontSize = 16.sp
    ),
    val semiBold14: TextStyle = TextStyle(
        fontFamily = com.dgu.smartcareapp.ui.theme.FontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        fontSize = 14.sp
    ),
    val regular16: TextStyle = TextStyle(
        fontFamily = com.dgu.smartcareapp.ui.theme.FontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        fontSize = 16.sp
    ),
    val regular14: TextStyle = TextStyle(
        fontFamily = com.dgu.smartcareapp.ui.theme.FontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        fontSize = 14.sp
    ),
)

@Composable
fun semiBold24(): TextStyle {
    return SmartCardAppTheme.typography.semiBold24
}

@Composable
fun semiBold20(): TextStyle {
    return SmartCardAppTheme.typography.semiBold20
}

@Composable
fun semiBold16(): TextStyle {
    return SmartCardAppTheme.typography.semiBold16
}

@Composable
fun semiBold14(): TextStyle {
    return SmartCardAppTheme.typography.semiBold14
}

@Composable
fun regular16(): TextStyle {
    return SmartCardAppTheme.typography.regular16
}

@Composable
fun regular14(): TextStyle {
    return SmartCardAppTheme.typography.regular14
}
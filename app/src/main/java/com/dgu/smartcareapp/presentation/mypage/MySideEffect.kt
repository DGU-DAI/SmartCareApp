package com.dgu.smartcareapp.presentation.mypage

sealed class MySideEffect {
    object NavigateToSafeWordManage : MySideEffect()
    object NavigateToGuardianInfoManage : MySideEffect()
}

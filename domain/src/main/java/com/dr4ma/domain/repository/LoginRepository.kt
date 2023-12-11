package com.dr4ma.domain.repository

import com.dr4ma.domain.models.LoginModel
import com.dr4ma.domain.models.ResponseLoginModel

interface LoginRepository {

    suspend fun login(
        loginModel: LoginModel,
        onSuccess: (ResponseLoginModel) -> Unit,
        onFailure: (failureMessage: String) -> Unit
    )
}
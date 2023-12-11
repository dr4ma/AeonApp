package com.dr4ma.domain.usecases

import com.dr4ma.domain.models.LoginModel
import com.dr4ma.domain.models.ResponseLoginModel
import com.dr4ma.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun login(
        model: LoginModel,
        onSuccess: (model: ResponseLoginModel) -> Unit,
        onFailure: (failureMessage: String) -> Unit
    ) {
        loginRepository.login(model, onSuccess, onFailure)
    }
}
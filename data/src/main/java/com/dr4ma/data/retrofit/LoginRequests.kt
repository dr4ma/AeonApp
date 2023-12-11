package com.dr4ma.data.retrofit

import android.util.Log
import com.dr4ma.domain.models.LoginModel
import com.dr4ma.domain.models.ResponseLoginModel
import com.dr4ma.domain.repository.LoginRepository

class LoginRequests : LoginRepository {

    override suspend fun login(
        loginModel: LoginModel,
        onSuccess: (model : ResponseLoginModel) -> Unit,
        onFailure: (failureMessage : String) -> Unit
    ) {
        val request = RetrofitInstance.apiService.login(loginModel)
        if(request.isSuccessful){
            onSuccess(request.body() ?: ResponseLoginModel(false))
        }
        else{
            Log.e("Login request", "Failure login: ${request.message()}")
            onFailure(request.message())
        }
    }
}
package com.dr4ma.aeonapp.presentation.fragments.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dr4ma.aeonapp.utils.AppPreferences
import com.dr4ma.aeonapp.utils.TokenStatus
import com.dr4ma.domain.models.LoginModel
import com.dr4ma.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun login(
        login: String,
        password: String,
        onSuccessResponse: (status : TokenStatus, errorMsg : String?) -> Unit,
        onFailureResponse: (failureMessage: String) -> Unit
    ) {
        viewModelScope.launch {
            loginUseCase.login(LoginModel(login, password), { responseModel ->
                if(responseModel.successAuth){
                    if(responseModel.response?.token != null || responseModel.response?.token != ""){
                        AppPreferences.rememberToken(responseModel.response!!.token)
                        onSuccessResponse(TokenStatus.EXIST, null)
                    }
                    else{
                        Log.e("LoginViewModel", "TokenModel is empty")
                        onSuccessResponse(TokenStatus.NOT_EXIST, null)
                    }
                }
                else{
                    onSuccessResponse(TokenStatus.NOT_EXIST, responseModel.error?.errorMessage)
                }
            }, { failureMessage ->
                onFailureResponse(failureMessage)
            })
        }
    }
}
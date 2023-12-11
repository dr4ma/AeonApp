package com.dr4ma.data.retrofit

import com.dr4ma.domain.models.LoginModel
import com.dr4ma.domain.models.ResponseLoginModel
import com.dr4ma.domain.models.ResponsePaymentsModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginBody : LoginModel) : Response<ResponseLoginModel>

    @GET("payments")
    suspend fun getPayments(@Header("token") tokenName : String) : Response<ResponsePaymentsModel>
}
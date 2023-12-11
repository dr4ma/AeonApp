package com.dr4ma.data.retrofit

import com.dr4ma.data.utils.APP_KEY
import com.dr4ma.data.utils.APP_V
import com.dr4ma.data.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("app-key", APP_KEY)
                .addHeader("v", APP_V)
                .build()
            chain.proceed(newRequest)
        }
        readTimeout(15, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
        connectTimeout(15, TimeUnit.SECONDS)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
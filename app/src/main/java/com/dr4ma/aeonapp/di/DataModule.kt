package com.dr4ma.aeonapp.di

import com.dr4ma.data.retrofit.LoginRequests
import com.dr4ma.data.retrofit.PaymentsRequests
import com.dr4ma.domain.repository.LoginRepository
import com.dr4ma.domain.repository.PaymentsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideLoginRequests() : LoginRepository{
        return LoginRequests()
    }

    @Provides
    fun providePaymentsRequests() : PaymentsRepository{
        return PaymentsRequests()
    }
}
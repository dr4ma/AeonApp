package com.dr4ma.aeonapp.di

import com.dr4ma.domain.repository.LoginRepository
import com.dr4ma.domain.repository.PaymentsRepository
import com.dr4ma.domain.usecases.LoginUseCase
import com.dr4ma.domain.usecases.PaymentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideLoginUseCase(repository: LoginRepository) : LoginUseCase{
        return LoginUseCase(loginRepository = repository)
    }

    @Provides
    fun provideLPaymentsUseCase(repository: PaymentsRepository) : PaymentsUseCase{
        return PaymentsUseCase(paymentsRepository = repository)
    }
}
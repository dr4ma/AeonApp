package com.dr4ma.aeonapp.di

import com.dr4ma.aeonapp.presentation.fragments.payments.paymentsAdapter.PaymentsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun providePaymentsAdapter(): PaymentsAdapter {
        return PaymentsAdapter()
    }
}
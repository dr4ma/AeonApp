package com.dr4ma.domain.repository

import com.dr4ma.domain.models.LoginModel
import com.dr4ma.domain.models.PaymentModel
import com.dr4ma.domain.models.ResponseLoginModel
import kotlinx.coroutines.flow.Flow

interface PaymentsRepository {

    suspend fun getPayments(token : String, onError:(errorMsg : String) -> Unit) : Flow<List<PaymentModel>>
}
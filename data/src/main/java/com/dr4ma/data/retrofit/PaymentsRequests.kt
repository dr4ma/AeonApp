package com.dr4ma.data.retrofit

import android.util.Log
import com.dr4ma.domain.models.PaymentModel
import com.dr4ma.domain.repository.PaymentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PaymentsRequests : PaymentsRepository {

    override suspend fun getPayments(
        token: String,
        onError: (errorMsg: String) -> Unit
    ): Flow<List<PaymentModel>> = callbackFlow<List<PaymentModel>> {

        val request = RetrofitInstance.apiService.getPayments(token)
        if (request.isSuccessful) {
            if (request.body()?.success == true) {
                launch {
                    send(request.body()?.response ?: emptyList())
                }
            } else {
                Log.e("Payments request", request.body()?.error?.errorMessage ?: "Invalid token")
                onError(request.body()?.error?.errorMessage ?: "Error getting payments")
            }
        } else {
            Log.e("Get payments failure", request.message())
            onError(request.message())
        }
        awaitClose()
    }.flowOn(Dispatchers.IO).distinctUntilChanged()
}
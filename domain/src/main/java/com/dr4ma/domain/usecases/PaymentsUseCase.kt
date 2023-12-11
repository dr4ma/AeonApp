package com.dr4ma.domain.usecases

import com.dr4ma.domain.models.PaymentModel
import com.dr4ma.domain.repository.PaymentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class PaymentsUseCase @Inject constructor(private val paymentsRepository: PaymentsRepository) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getPayments(
        token: String,
        onError: (errorMsg: String) -> Unit
    ): Flow<List<PaymentModel>> = flow {
        paymentsRepository.getPayments(token, onError).mapLatest { list ->
            list.filter { model ->
                model.amount?.isEmpty() != true &&
                        model.created?.toInt() != 0 &&
                        model.title?.isEmpty() != true &&
                        model.created != null &&
                        model.title != null &&
                        model.amount != null
            }
        }.collect { paymentsList ->
            emit(paymentsList)
        }
    }.flowOn(Dispatchers.IO).distinctUntilChanged()
}
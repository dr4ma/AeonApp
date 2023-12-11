package com.dr4ma.domain.models

data class ResponsePaymentsModel(
    val success: Boolean,
    val response: List<PaymentModel>? = null,
    val error : ErrorResponseModel? = null
)

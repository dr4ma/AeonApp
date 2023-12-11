package com.dr4ma.domain.models

data class PaymentModel(
    val id: String,
    val title: String? = null,
    val amount: String? = null,
    val created: Long? = null
)

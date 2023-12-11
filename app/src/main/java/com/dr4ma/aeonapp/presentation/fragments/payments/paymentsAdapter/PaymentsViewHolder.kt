package com.dr4ma.aeonapp.presentation.fragments.payments.paymentsAdapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.dr4ma.aeonapp.databinding.PaymentItemRecyclerBinding
import com.dr4ma.aeonapp.utils.timeStampToDate
import com.dr4ma.domain.models.PaymentModel

class PaymentsViewHolder(private val binding: PaymentItemRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(payment: PaymentModel) {
        with(binding){
            tittle.text = payment.title
            amount.text = "${payment.amount} $"
            created.text = payment.created.timeStampToDate()
        }
    }
}
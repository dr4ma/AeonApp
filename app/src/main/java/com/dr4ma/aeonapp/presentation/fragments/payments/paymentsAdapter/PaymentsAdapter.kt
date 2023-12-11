package com.dr4ma.aeonapp.presentation.fragments.payments.paymentsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dr4ma.aeonapp.databinding.PaymentItemRecyclerBinding
import com.dr4ma.domain.models.PaymentModel

class PaymentsAdapter : RecyclerView.Adapter<PaymentsViewHolder>() {

    private var paymentsList = mutableListOf<PaymentModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentsViewHolder {
        val binding = PaymentItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return paymentsList.size
    }

    override fun onBindViewHolder(holder: PaymentsViewHolder, position: Int) {
        holder.bind(paymentsList[position])
    }

    fun setList(newList: List<PaymentModel>) {
        val diffUtil = PaymentsDiffUtil(paymentsList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        paymentsList.clear()
        paymentsList.addAll(newList)
        diffResults.dispatchUpdatesTo(this)
    }
}
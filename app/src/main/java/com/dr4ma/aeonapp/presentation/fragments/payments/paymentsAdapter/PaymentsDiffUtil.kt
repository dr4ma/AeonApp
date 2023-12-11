package com.dr4ma.aeonapp.presentation.fragments.payments.paymentsAdapter

import androidx.recyclerview.widget.DiffUtil
import com.dr4ma.domain.models.PaymentModel

class PaymentsDiffUtil(private val oldList: List<PaymentModel>, private val newList: List<PaymentModel>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }
}
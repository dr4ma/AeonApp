package com.dr4ma.aeonapp.presentation.fragments.payments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dr4ma.domain.models.PaymentModel
import com.dr4ma.domain.usecases.PaymentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(private val paymentsUseCase: PaymentsUseCase) : ViewModel() {

    private val _payments: MutableLiveData<List<PaymentModel>> = MutableLiveData()
    val payments = _payments

    fun getPayments(token : String, onError:(errorMsg : String) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            paymentsUseCase.getPayments(token, onError).collect{ list ->
                _payments.postValue(list)
            }
        }
    }
}
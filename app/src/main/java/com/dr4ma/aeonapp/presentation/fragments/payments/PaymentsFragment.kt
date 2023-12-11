package com.dr4ma.aeonapp.presentation.fragments.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dr4ma.aeonapp.R
import com.dr4ma.aeonapp.databinding.FragmentPaymentsBinding
import com.dr4ma.aeonapp.presentation.fragments.payments.paymentsAdapter.PaymentsAdapter
import com.dr4ma.aeonapp.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaymentsFragment : Fragment() {

    private var binding: FragmentPaymentsBinding? = null
    private val mBinding get() = binding!!

    private val mViewModel: PaymentsViewModel by viewModels()
    private lateinit var mRecycler: RecyclerView

    @Inject
    lateinit var paymentsAdapter: PaymentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentsBinding.inflate(layoutInflater, container, false)
        mBinding.apply {
            mRecycler = paymentsRecycler
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFields()

        initObservers()
        getPayments()
    }

    private fun initFields() {
        mBinding.apply {
            mRecycler.adapter = paymentsAdapter
            progress.visibility = View.VISIBLE

            logout.setOnClickListener {
                AppPreferences.rememberToken(null)
                findNavController().navigate(R.id.action_paymentsFragment_to_loginFragment)
            }
        }
    }

    private fun initObservers() {
        mBinding.apply {
            mViewModel.payments.observe(viewLifecycleOwner) { paymentsList ->
                progress.visibility = View.GONE
                if (paymentsList.isEmpty()) {
                    infoText.text = getString(R.string.no_payments)
                } else {
                    infoText.visibility = View.GONE
                    paymentsAdapter.setList(paymentsList)
                }
            }
        }
    }

    private fun getPayments() {
        mBinding.apply {
            mViewModel.getPayments(AppPreferences.findToken() ?: "") { errorMsg ->
                activity?.runOnUiThread {
                    progress.visibility = View.GONE
                    infoText.text = errorMsg
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package com.dr4ma.aeonapp.presentation.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dr4ma.aeonapp.R
import com.dr4ma.aeonapp.databinding.FragmentLoginBinding
import com.dr4ma.aeonapp.utils.AppPreferences
import com.dr4ma.aeonapp.utils.TokenStatus
import com.dr4ma.aeonapp.utils.hideKeyBoard
import com.dr4ma.aeonapp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val mBinding get() = binding!!

    private val mViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppPreferences.getPreferences(requireContext())
        if(AppPreferences.findToken() != null){
            findNavController().navigate(R.id.action_loginFragment_to_paymentsFragment)
        }
        else{
            initFields()
        }
    }

    private fun initFields(){
        mBinding.apply {
            confirm.setOnClickListener {
                hideKeyBoard(requireActivity())
                if(login.text.isEmpty() || password.text.isEmpty()){
                    showSnackBar(requireContext(), confirm, getString(R.string.some_field_is_empty), R.color.colorError)
                }
                else{
                    getAuthToken()
                }
            }
        }
    }

    private fun getAuthToken(){
        mBinding.apply {
            initLoadingViews(true)
            mViewModel.login(login.text.toString(), password.text.toString(), { tokenStatus, errorMsg ->
                initLoadingViews(false)

                if(tokenStatus == TokenStatus.EXIST){
                    findNavController().navigate(R.id.action_loginFragment_to_paymentsFragment)
                }
                else{
                    showSnackBar(requireContext(), confirm, errorMsg ?: getString(R.string.unknown_error), R.color.colorError)
                }
            }, { failureMessage ->
                showSnackBar(requireContext(), confirm, failureMessage, R.color.colorError)
            })
        }
    }

    private fun initLoadingViews(loading : Boolean){
        mBinding.apply {
            if (loading){
                confirm.visibility = View.INVISIBLE
                loginProgress.visibility = View.VISIBLE
            }
            else{
                confirm.visibility = View.VISIBLE
                loginProgress.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
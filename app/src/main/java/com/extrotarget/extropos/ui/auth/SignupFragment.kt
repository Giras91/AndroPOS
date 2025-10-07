package com.extrotarget.extropos.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.extrotarget.extropos.R
import com.extrotarget.extropos.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()
            val companyName = binding.companyNameEditText.text.toString().trim()
            val companyRegistration = binding.companyRegEditText.text.toString().trim()
            val address = binding.addressEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()

            viewModel.signup(
                email = email,
                password = password,
                name = name,
                companyName = companyName,
                companyRegistrationNumber = companyRegistration,
                address = address,
                phoneNumber = phone
            )
        }

        binding.loginLink.setOnClickListener {
            findNavController().navigate(R.id.action_signup_to_login)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                        binding.signupButton.isEnabled = !isLoading
                    }
                }

                launch {
                    viewModel.error.collect { error ->
                        binding.errorTextView.text = error
                        binding.errorTextView.visibility = if (error != null) View.VISIBLE else View.GONE
                    }
                }

                launch {
                    viewModel.signupResult.collect { result ->
                        result?.let {
                            if (it.success) {
                                // Navigate to app lock screen for verification
                                findNavController().navigate(R.id.action_signup_to_app_lock)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.extrotarget.extropos.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
            val confirmPassword = binding.confirmPasswordEditText.text.toString()
            val companyName = binding.companyNameEditText.text.toString().trim()
            val companyRegistration = binding.companyRegistrationEditText.text.toString().trim()
            val address = binding.addressEditText.text.toString().trim()
            val city = binding.cityEditText.text.toString().trim()
            val state = binding.stateEditText.text.toString().trim()
            val postcode = binding.postcodeEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()

            // Validate passwords match
            if (password != confirmPassword) {
                binding.errorTextView.text = "Passwords do not match"
                binding.errorTextView.visibility = View.VISIBLE
                return@setOnClickListener
            }

            viewModel.signup(
                name = name,
                email = email,
                password = password,
                companyName = companyName,
                companyRegistration = companyRegistration,
                address = address,
                city = city,
                state = state,
                postcode = postcode,
                phone = phone
            )
        }

        binding.loginLink.setOnClickListener {
            findNavController().navigate(R.id.action_signup_to_login)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                binding.signupButton.isEnabled = !isLoading
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect { error ->
                binding.errorTextView.text = error
                binding.errorTextView.visibility = if (error != null) View.VISIBLE else View.GONE
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
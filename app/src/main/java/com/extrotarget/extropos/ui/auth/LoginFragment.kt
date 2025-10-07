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
import com.extrotarget.extropos.databinding.FragmentLoginBinding
import com.extrotarget.extropos.domain.model.AuthState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    // Use _binding directly and keep accesses null-safe to avoid NPEs

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        // Use null-safe binding access in listeners
        _binding?.loginButton?.setOnClickListener {
            val email = _binding?.emailEditText?.text?.toString()?.trim() ?: ""
            val password = _binding?.passwordEditText?.text?.toString() ?: ""

            viewModel.login(email, password)
        }

        _binding?.signupLink?.setOnClickListener {
            if (isAdded) findNavController().navigate(R.id.action_login_to_signup)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Collect only while the view lifecycle is at least STARTED.
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // launch separate coroutines for each collector so they run concurrently
                launch {
                    viewModel.isLoading.collect { isLoading ->
                        _binding?.let { b ->
                            b.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                            b.loginButton.isEnabled = !isLoading
                        }
                    }
                }

                launch {
                    viewModel.error.collect { error ->
                        _binding?.let { b ->
                            b.errorTextView.text = error
                            b.errorTextView.visibility = if (error != null) View.VISIBLE else View.GONE
                        }
                    }
                }

                launch {
                    viewModel.loginResult.collect { result ->
                        result?.let {
                            if (it.success) {
                                if (it.requiresVerification) {
                                    // Navigate to app lock screen
                                    if (isAdded) findNavController().navigate(R.id.action_login_to_app_lock)
                                } else {
                                    // Navigate to main app
                                    if (isAdded) findNavController().navigate(R.id.action_login_to_main)
                                }
                            }
                        }
                    }
                }

                launch {
                    viewModel.authState.collect { state ->
                        when (state) {
                            AuthState.AUTHENTICATED_VERIFIED -> {
                                // User is already verified, go to main app
                                if (isAdded) findNavController().navigate(R.id.action_login_to_main)
                            }
                            AuthState.AUTHENTICATED_NOT_VERIFIED -> {
                                // User is logged in but not verified, go to lock screen
                                if (isAdded) findNavController().navigate(R.id.action_login_to_app_lock)
                            }
                            else -> {
                                // Stay on login screen
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
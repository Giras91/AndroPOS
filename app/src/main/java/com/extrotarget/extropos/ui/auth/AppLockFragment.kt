package com.extrotarget.extropos.ui.auth

import android.content.Intent
import android.net.Uri
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
import com.extrotarget.extropos.databinding.FragmentAppLockBinding
import com.extrotarget.extropos.domain.model.AuthState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AppLockFragment : Fragment() {

    private var _binding: FragmentAppLockBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppLockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        observeViewModel()
        startVerificationCheck()
    }

    private fun setupClickListeners() {
        binding.resendEmailButton.setOnClickListener {
            viewModel.resendVerificationEmail()
        }

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_app_lock_to_login)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                    }
                }

                launch {
                    viewModel.error.collect { error ->
                        binding.statusTextView.text = error ?: ""
                        binding.statusTextView.visibility = if (error != null) View.VISIBLE else View.GONE
                    }
                }

                launch {
                    viewModel.authState.collect { state ->
                        when (state) {
                            AuthState.AUTHENTICATED_VERIFIED -> {
                                // User is now verified, go to main app
                                findNavController().navigate(R.id.action_app_lock_to_main)
                            }
                            AuthState.NOT_AUTHENTICATED -> {
                                // User logged out, go back to login
                                findNavController().navigate(R.id.action_app_lock_to_login)
                            }
                            else -> {
                                // Stay on lock screen
                            }
                        }
                    }
                }
            }
        }

        // AuthViewModel exposes resendVerificationEmail() and sets error/messages via error/state flows.
    }

    private fun startVerificationCheck() {
        // Check verification status every 5 seconds while view is STARTED
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (true) {
                    viewModel.checkAppActivation()
                    kotlinx.coroutines.delay(5000) // 5 seconds
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
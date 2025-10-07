package com.extrotarget.extropos.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.extrotarget.extropos.R
import android.widget.*
import kotlinx.coroutines.launch
import android.content.Intent
import com.extrotarget.extropos.MainActivity

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var loginButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupClickListeners()
        observeViewModel()
    }

    private fun initializeViews(view: View) {
        loginButton = view.findViewById(R.id.loginButton)
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        progressBar = view.findViewById(R.id.progressBar)
        errorTextView = view.findViewById(R.id.errorTextView)
    }

    private fun setupClickListeners() {
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                viewModel.login(email, password)
            }
        }
    }

    private fun appendToPassword(digit: String) {
        val currentText = passwordEditText.text.toString()
        passwordEditText.setText(currentText + digit)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.loginState.collect { state ->
                        when (state) {
                            is LoginState.Loading -> {
                                loginButton.isEnabled = false
                                progressBar.visibility = View.VISIBLE
                            }
                            is LoginState.Success -> {
                                loginButton.isEnabled = true
                                progressBar.visibility = View.GONE
                                // Navigate to main POS screen
                                navigateToMainScreen()
                            }
                            is LoginState.Error -> {
                                loginButton.isEnabled = true
                                progressBar.visibility = View.GONE
                                errorTextView.text = state.message
                                errorTextView.visibility = View.VISIBLE
                            }
                            LoginState.Idle -> {
                                loginButton.isEnabled = true
                                progressBar.visibility = View.GONE
                                errorTextView.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToMainScreen() {
        // Simple navigation: start MainActivity and finish the current activity.
        val ctx = requireContext()
        val intent = Intent(ctx, MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
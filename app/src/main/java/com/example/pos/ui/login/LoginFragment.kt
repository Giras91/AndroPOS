package com.example.pos.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pos.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                viewModel.login(username, password)
            }
        }

        // Numeric keypad buttons
        binding.keypad.button0.setOnClickListener { appendToPassword("0") }
        binding.keypad.button1.setOnClickListener { appendToPassword("1") }
        binding.keypad.button2.setOnClickListener { appendToPassword("2") }
        binding.keypad.button3.setOnClickListener { appendToPassword("3") }
        binding.keypad.button4.setOnClickListener { appendToPassword("4") }
        binding.keypad.button5.setOnClickListener { appendToPassword("5") }
        binding.keypad.button6.setOnClickListener { appendToPassword("6") }
        binding.keypad.button7.setOnClickListener { appendToPassword("7") }
        binding.keypad.button8.setOnClickListener { appendToPassword("8") }
        binding.keypad.button9.setOnClickListener { appendToPassword("9") }

        binding.keypad.buttonClear.setOnClickListener {
            binding.passwordEditText.setText("")
        }

        binding.keypad.buttonBackspace.setOnClickListener {
            val currentText = binding.passwordEditText.text.toString()
            if (currentText.isNotEmpty()) {
                binding.passwordEditText.setText(currentText.dropLast(1))
            }
        }
    }

    private fun appendToPassword(digit: String) {
        val currentText = binding.passwordEditText.text.toString()
        binding.passwordEditText.setText(currentText + digit)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginState.Loading -> {
                        binding.loginButton.isEnabled = false
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is LoginState.Success -> {
                        binding.loginButton.isEnabled = true
                        binding.progressBar.visibility = View.GONE
                        // Navigate to main POS screen
                        navigateToMainScreen()
                    }
                    is LoginState.Error -> {
                        binding.loginButton.isEnabled = true
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.text = state.message
                        binding.errorTextView.visibility = View.VISIBLE
                    }
                    LoginState.Idle -> {
                        binding.loginButton.isEnabled = true
                        binding.progressBar.visibility = View.GONE
                        binding.errorTextView.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun navigateToMainScreen() {
        // TODO: Navigate to main POS screen
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
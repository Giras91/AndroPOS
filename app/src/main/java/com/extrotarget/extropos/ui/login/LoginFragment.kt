package com.extrotarget.extropos.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.R
import android.widget.*
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var loginButton: Button
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var keypad: ViewGroup

    private lateinit var viewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Initialize ViewModel
        viewModel = LoginViewModel()
    }

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
        usernameEditText = view.findViewById(R.id.usernameEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        progressBar = view.findViewById(R.id.progressBar)
        errorTextView = view.findViewById(R.id.errorTextView)
        keypad = view.findViewById(R.id.keypad)
    }

    private fun setupClickListeners() {
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                viewModel.login(username, password)
            }
        }

        // Numeric keypad buttons
        keypad.findViewById<Button>(R.id.button0).setOnClickListener { appendToPassword("0") }
        keypad.findViewById<Button>(R.id.button1).setOnClickListener { appendToPassword("1") }
        keypad.findViewById<Button>(R.id.button2).setOnClickListener { appendToPassword("2") }
        keypad.findViewById<Button>(R.id.button3).setOnClickListener { appendToPassword("3") }
        keypad.findViewById<Button>(R.id.button4).setOnClickListener { appendToPassword("4") }
        keypad.findViewById<Button>(R.id.button5).setOnClickListener { appendToPassword("5") }
        keypad.findViewById<Button>(R.id.button6).setOnClickListener { appendToPassword("6") }
        keypad.findViewById<Button>(R.id.button7).setOnClickListener { appendToPassword("7") }
        keypad.findViewById<Button>(R.id.button8).setOnClickListener { appendToPassword("8") }
        keypad.findViewById<Button>(R.id.button9).setOnClickListener { appendToPassword("9") }

        keypad.findViewById<Button>(R.id.buttonClear).setOnClickListener {
            passwordEditText.setText("")
        }

        keypad.findViewById<Button>(R.id.buttonBackspace).setOnClickListener {
            val currentText = passwordEditText.text.toString()
            if (currentText.isNotEmpty()) {
                passwordEditText.setText(currentText.dropLast(1))
            }
        }
    }

    private fun appendToPassword(digit: String) {
        val currentText = passwordEditText.text.toString()
        passwordEditText.setText(currentText + digit)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
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

    private fun navigateToMainScreen() {
        // TODO: Navigate to main POS screen
    }
}
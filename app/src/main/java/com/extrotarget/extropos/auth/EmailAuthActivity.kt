package com.extrotarget.extropos.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.R
import com.extrotarget.extropos.ui.pin.PinLoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Handles Appwrite email login / signup and transitions to PIN setup/login.
 */
@AndroidEntryPoint
class EmailAuthActivity : ComponentActivity() {

    private val vm: EmailAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_auth)
        val email: EditText = findViewById(R.id.emailInput)
        val pass: EditText = findViewById(R.id.passwordInput)
        val loginBtn: Button = findViewById(R.id.loginBtn)
        val loading: ProgressBar = findViewById(R.id.loading)
        val errorText: TextView = findViewById(R.id.errorText)
        val verifyText: TextView = findViewById(R.id.verificationText)

        loginBtn.setOnClickListener {
            vm.performLogin(email.text.toString().trim(), pass.text.toString())
        }
        lifecycleScope.launch {
            vm.state.collectLatest { state ->
                when (state) {
                    EmailAuthState.Idle -> {
                        loading.visibility = View.GONE
                        errorText.visibility = View.GONE
                        verifyText.visibility = View.GONE
                    }
                    EmailAuthState.Loading -> {
                        loading.visibility = View.VISIBLE
                        errorText.visibility = View.GONE
                        verifyText.visibility = View.GONE
                    }
                    EmailAuthState.NeedsVerification -> {
                        loading.visibility = View.GONE
                        verifyText.visibility = View.VISIBLE
                        errorText.visibility = View.GONE
                    }
                    EmailAuthState.Authenticated -> {
                        startActivity(Intent(this@EmailAuthActivity, PinLoginActivity::class.java))
                        finish()
                    }
                    is EmailAuthState.Error -> {
                        loading.visibility = View.GONE
                        errorText.visibility = View.VISIBLE
                        errorText.text = state.message
                        verifyText.visibility = View.GONE
                    }
                }
            }
        }
        vm.bootstrap()
    }
}

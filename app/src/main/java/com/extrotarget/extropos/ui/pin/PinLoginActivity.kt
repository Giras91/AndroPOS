package com.extrotarget.extropos.ui.pin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.ui.user.AddUserActivity
import com.extrotarget.extropos.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Offline PIN login screen. Performs three roles:
 * 1. First-time setup: allow technician PIN (888888) once → AddUserActivity.
 * 2. After a user PIN is configured, validate it offline.
 * 3. Transition to MainActivity (existing) upon success.
 */
@AndroidEntryPoint
class PinLoginActivity : ComponentActivity() {

    private val vm: PinLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_login)
        val pinInput: EditText = findViewById(R.id.pinInput)
        val submit: Button = findViewById(R.id.submitBtn)
        submit.setOnClickListener {
            vm.submitPin(pinInput.text.toString().trim())
        }

        lifecycleScope.launch {
            vm.events.collectLatest { ev ->
                when (ev) {
                    PinLoginEvent.NavigateTechnicianSetup -> {
                        startActivity(Intent(this@PinLoginActivity, AddUserActivity::class.java))
                        finish()
                    }
                    PinLoginEvent.NavigateMain -> {
                        // Navigate to Dashboard after successful PIN login
                        val intent = Intent(this@PinLoginActivity, 
                            Class.forName("com.extrotarget.extropos.ui.dashboard.DashboardActivity"))
                        startActivity(intent)
                        finish()
                    }
                    is PinLoginEvent.ShowMessage -> Toast.makeText(this@PinLoginActivity, ev.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

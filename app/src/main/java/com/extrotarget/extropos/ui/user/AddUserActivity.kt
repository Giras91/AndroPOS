package com.extrotarget.extropos.ui.user

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.ui.pin.PinLoginActivity
import com.extrotarget.extropos.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.content.Intent
import kotlinx.coroutines.flow.collectLatest

/**
 * Activity to set the first user PIN after technician unlock.
 */
@AndroidEntryPoint
class AddUserActivity : ComponentActivity() {

    private val vm: AddUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        val p1: EditText = findViewById(R.id.pin1)
        val p2: EditText = findViewById(R.id.pin2)
        val save: Button = findViewById(R.id.savePinBtn)
        save.setOnClickListener {
            vm.setPin(p1.text.toString().trim(), p2.text.toString().trim())
        }

        lifecycleScope.launch {
            vm.events.collectLatest { ev ->
                when (ev) {
                    AddUserEvent.NavigatePinLogin -> {
                        startActivity(Intent(this@AddUserActivity, PinLoginActivity::class.java))
                        finish()
                    }
                    is AddUserEvent.ShowMessage -> Toast.makeText(this@AddUserActivity, ev.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

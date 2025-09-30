package com.extrotarget.extropos.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.extrotarget.extropos.services.AppwriteService

class LoginActivity : AppCompatActivity() {

    private lateinit var statusTextView: TextView
    private lateinit var pingButton: Button
    private val appwriteService = AppwriteService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a simple layout with title, status, and ping button
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val titleTextView = TextView(this).apply {
            text = "ExtroPOS - Login Screen"
            textSize = 24f
            setPadding(0, 0, 0, 32)
        }

        statusTextView = TextView(this).apply {
            text = "Status: Ready to ping Appwrite"
            textSize = 16f
            setPadding(0, 0, 0, 32)
        }

        pingButton = Button(this).apply {
            text = "Send a ping to Appwrite"
            setOnClickListener { pingAppwrite() }
        }

        layout.addView(titleTextView)
        layout.addView(statusTextView)
        layout.addView(pingButton)

        setContentView(layout)
    }

    private fun pingAppwrite() {
        statusTextView.text = "Status: Pinging Appwrite..."
        pingButton.isEnabled = false

        appwriteService.pingAppwrite(object : AppwriteService.PingCallback {
            override fun onSuccess(message: String) {
                runOnUiThread {
                    statusTextView.text = "Status: $message"
                    Toast.makeText(this@LoginActivity, "Ping successful!", Toast.LENGTH_LONG).show()
                    pingButton.isEnabled = true
                }
            }

            override fun onFailure(error: Throwable) {
                runOnUiThread {
                    val errorMessage = error.message ?: "Unknown error"
                    statusTextView.text = "Status: Ping failed - $errorMessage"
                    Toast.makeText(this@LoginActivity, "Ping failed: $errorMessage", Toast.LENGTH_LONG).show()
                    pingButton.isEnabled = true
                }
            }
        })
    }
}
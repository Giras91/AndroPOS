package com.extrotarget.extropos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.extrotarget.extropos.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupMiniApp()
    }
    
    private fun setupMiniApp() {
        // Update welcome message with current time
        val currentTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        binding.statusText.text = "ExtroPOS MiniApp - Ready\nLast updated: $currentTime"
        
        // Setup button click listeners
        binding.openMainAppButton.setOnClickListener {
            openMainApp()
        }
        
        binding.quickSaleButton.setOnClickListener {
            showQuickSaleFeature()
        }
        
        binding.viewReportsButton.setOnClickListener {
            showReportsFeature()
        }
        
        binding.settingsButton.setOnClickListener {
            showSettingsFeature()
        }
    }
    
    private fun openMainApp() {
        try {
            // Try to open the main AndroPOS app
            val intent = packageManager.getLaunchIntentForPackage("com.extrotarget.extropos")
            if (intent != null) {
                startActivity(intent)
                Toast.makeText(this, "Opening AndroPOS Main App", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "AndroPOS Main App not found. Please install it first.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error opening main app: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun showQuickSaleFeature() {
        binding.statusText.text = "Quick Sale Feature\n• Fast checkout for common items\n• Barcode scanning support\n• Quick payment processing"
        Toast.makeText(this, "Quick Sale - Feature available in main app", Toast.LENGTH_SHORT).show()
    }
    
    private fun showReportsFeature() {
        binding.statusText.text = "Reports & Analytics\n• Daily sales summary\n• Top selling items\n• Revenue tracking\n• Export capabilities"
        Toast.makeText(this, "Reports - Feature available in main app", Toast.LENGTH_SHORT).show()
    }
    
    private fun showSettingsFeature() {
        binding.statusText.text = "Settings & Configuration\n• Printer setup\n• Receipt customization\n• User management\n• System preferences"
        Toast.makeText(this, "Settings - Feature available in main app", Toast.LENGTH_SHORT).show()
    }
}

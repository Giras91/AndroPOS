package com.extrotarget.extropos

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.extrotarget.extropos.databinding.ActivityMainBinding
import com.extrotarget.extropos.domain.model.AuthState
import com.extrotarget.extropos.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        observeAuthState()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Set the navigation graph
        navController.setGraph(R.navigation.nav_graph_auth)
    }

    private fun observeAuthState() {
        lifecycleScope.launch {
            authViewModel.authState.collect { state ->
                when (state) {
                    AuthState.AUTHENTICATED_VERIFIED -> {
                        // User is authenticated and verified, navigate to main app
                        navigateToMainApp()
                    }
                    AuthState.AUTHENTICATED_NOT_VERIFIED -> {
                        // User is authenticated but not verified, show lock screen
                        navigateToLockScreen()
                    }
                    AuthState.NOT_AUTHENTICATED -> {
                        // User is not authenticated, show login screen
                        navigateToLogin()
                    }
                    AuthState.LOADING -> {
                        // Show loading state if needed
                    }
                }
            }
        }
    }

    private fun navigateToMainApp() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Navigate to main app if not already there
        if (navController.currentDestination?.id != R.id.mainFragment) {
            navController.navigate(R.id.action_login_to_main)
        }
    }

    private fun navigateToLockScreen() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Navigate to lock screen if not already there
        if (navController.currentDestination?.id != R.id.appLockFragment) {
            navController.navigate(R.id.action_login_to_app_lock)
        }
    }

    private fun navigateToLogin() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Navigate to login if not already there
        if (navController.currentDestination?.id != R.id.loginFragment) {
            navController.navigate(R.id.action_app_lock_to_login)
        }
    }
}
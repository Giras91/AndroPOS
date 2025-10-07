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

        // If started from debug launcher with the debug_open_dashboard flag, skip auth
        // and navigate to the main app immediately. Also support debug_open_pos which
        // requests opening the POS fragment after reaching the main screen.
        try {
            val openDashboard = intent?.getBooleanExtra("debug_open_dashboard", false) == true
            val openPos = intent?.getBooleanExtra("debug_open_pos", false) == true
            if (openDashboard) {
                navigateToMainApp()
                if (openPos) {
                    // Defer POS navigation until after MainApp navigation completes and
                    // the mainFragment is active. Post to the main looper to allow the
                    // NavController to settle.
                    binding.root.post {
                        navigateToPos()

                        // If debug requested auto adding a product to cart, schedule it after POS appears
                        val autoAddId = intent?.getStringExtra("debug_auto_add_product_id")
                        if (!autoAddId.isNullOrBlank()) {
                            binding.root.postDelayed({
                                try {
                                    // Find the active fragment inside the nav host and call autoAdd
                                    val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                                    val child = navHost?.childFragmentManager?.fragments?.firstOrNull { it is com.extrotarget.extropos.ui.pos.PosFragment }
                                    if (child is com.extrotarget.extropos.ui.pos.PosFragment) {
                                        child.autoAddProduct(autoAddId)
                                    }
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }, 300)
                        }
                    }
                }
            }
        } catch (_: Exception) {
            // ignore on older devices or if BuildConfig not accessible
        }
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
            try {
                // Try navigating directly to the destination id; this works regardless of current
                // destination as long as the destination exists in the graph.
                navController.navigate(R.id.mainFragment)
            } catch (e: Exception) {
                // Fallback: attempt the original action if present
                try {
                    if (navController.currentDestination?.getAction(R.id.action_login_to_main) != null) {
                        navController.navigate(R.id.action_login_to_main)
                    }
                } catch (_: Exception) {
                    // ignore navigation errors in debug
                    e.printStackTrace()
                }
            }
        }
    }

    private fun navigateToLockScreen() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Navigate to lock screen if not already there
        if (navController.currentDestination?.id != R.id.appLockFragment) {
            try {
                navController.navigate(R.id.appLockFragment)
            } catch (e: Exception) {
                try {
                    if (navController.currentDestination?.getAction(R.id.action_login_to_app_lock) != null) {
                        navController.navigate(R.id.action_login_to_app_lock)
                    }
                } catch (_: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun navigateToLogin() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Navigate to login if not already there
        if (navController.currentDestination?.id != R.id.loginFragment) {
            try {
                navController.navigate(R.id.loginFragment)
            } catch (e: Exception) {
                try {
                    if (navController.currentDestination?.getAction(R.id.action_app_lock_to_login) != null) {
                        navController.navigate(R.id.action_app_lock_to_login)
                    }
                } catch (_: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun navigateToPos() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // If mainFragment is active, navigate to POS. Otherwise, attempt safe navigation.
        try {
            if (navController.currentDestination?.id == R.id.mainFragment) {
                navController.navigate(R.id.action_main_to_pos)
            } else {
                // If we aren't on mainFragment yet, navigate directly to the main destination
                // then post navigation to POS. Use destination ids to avoid missing action issues.
                navController.navigate(R.id.mainFragment)
                binding.root.post {
                    try {
                        navController.navigate(R.id.action_main_to_pos)
                    } catch (e: Exception) {
                        // ignore if action not present
                        e.printStackTrace()
                    }
                }
            }
        } catch (e: Exception) {
            // Navigation may throw if destinations aren't in the current graph; ignore in debug
            e.printStackTrace()
        }
    }
}
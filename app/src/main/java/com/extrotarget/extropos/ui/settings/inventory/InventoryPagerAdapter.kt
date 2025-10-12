package com.extrotarget.extropos.ui.settings.inventory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.extrotarget.extropos.ui.product.ProductsGridFragment
import com.extrotarget.extropos.ui.menu.MenuFragment

class InventoryPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProductsGridFragment().apply {
                // Hide internal search since we want inventory management interface
                hideInternalSearch(true)
                // Hide internal FAB to prevent overlap with InventoryManagement's FAB
                hideInternalFab(true)
                // Enable management mode - clicking products shows edit dialog
                setManagementMode(true)
            }
            1 -> MenuFragment().apply {
                // Hide internal FAB to prevent overlap with InventoryManagement's FAB
                hideInternalFab(true)
            } // Shows categories
            2 -> PlaceholderFragment("Stock Levels") // TODO: Create proper stock fragment
            3 -> PlaceholderFragment("Barcodes") // TODO: Create proper barcode fragment
            else -> PlaceholderFragment("Unknown")
        }
    }
}

class PlaceholderFragment(private val title: String) : Fragment() {
    
    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): android.view.View {
        val textView = android.widget.TextView(requireContext()).apply {
            text = "$title - Coming Soon"
            textAlignment = android.view.View.TEXT_ALIGNMENT_CENTER
            textSize = 18f
            setPadding(32, 32, 32, 32)
        }
        return textView
    }
}
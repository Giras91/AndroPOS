package com.extrotarget.extropos.ui.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.extrotarget.extropos.R

/**
 * Simple customers list placeholder.
 */
class CustomersFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customers, container, false)
    }
}

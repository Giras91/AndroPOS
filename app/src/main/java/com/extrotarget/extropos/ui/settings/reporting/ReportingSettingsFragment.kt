package com.extrotarget.extropos.ui.settings.reporting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.extrotarget.extropos.databinding.FragmentReportingSettingsBinding

class ReportingSettingsFragment : Fragment() {

    private var _binding: FragmentReportingSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportingSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.toolbar.title = "Reporting & Analytics"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        // TODO: Implement sales reporting and data export configuration
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
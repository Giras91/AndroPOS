package com.extrotarget.extropos.ui.settings.hardware

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.extrotarget.extropos.databinding.FragmentHardwareSettingsBinding

class HardwareSettingsFragment : Fragment() {

    private var _binding: FragmentHardwareSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHardwareSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.toolbar.title = "Hardware & Device Settings"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        // TODO: Implement device pairing (KDS) and receipt customization
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
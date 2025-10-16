package com.extrotarget.extropos.ui.settings.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.extrotarget.extropos.databinding.FragmentReservationsBinding
import com.extrotarget.extropos.domain.model.Reservation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReservationsFragment : Fragment() {

    private var _binding: FragmentReservationsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReservationsViewModel by viewModels()
    private lateinit var adapter: ReservationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReservationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setupFab()
        observeViewModel()
        setupSwipeRefresh()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                com.extrotarget.extropos.R.id.action_add_reservation -> { showAddEditDialog(null); true }
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = ReservationsAdapter(
            onEditClick = { reservation -> showAddEditDialog(reservation) },
            onDeleteClick = { reservation -> confirmDelete(reservation) },
            onStatusChangeClick = { reservation -> showStatusChangeDialog(reservation) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupFab() { binding.fabAddReservation.setOnClickListener { showAddEditDialog(null) } }

    private fun setupSwipeRefresh() { binding.swipeRefreshLayout.setOnRefreshListener { viewModel.loadAll() } }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.reservations.collect { list ->
                adapter.submitList(list)
                updateEmptyState(list.isEmpty())
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { loading ->
                binding.progressBar.visibility = if (loading && !binding.swipeRefreshLayout.isRefreshing) View.VISIBLE else View.GONE
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect { err -> err?.let { Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show() } }
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyStateLayout.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        binding.btnAddFirstReservation.setOnClickListener { showAddEditDialog(null) }
    }

    private fun confirmDelete(reservation: Reservation) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Reservation")
            .setMessage("Delete reservation for ${reservation.customerName}? This action cannot be undone.")
            .setPositiveButton("Delete") { _, _ -> viewModel.delete(reservation.id) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showStatusChangeDialog(reservation: Reservation) {
        val statusOptions = arrayOf("PENDING", "CONFIRMED", "SEATED", "COMPLETED", "CANCELLED", "NO_SHOW")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Change Status")
            .setItems(statusOptions) { _, which ->
                val newStatus = when (which) {
                    0 -> com.extrotarget.extropos.domain.model.ReservationStatus.PENDING
                    1 -> com.extrotarget.extropos.domain.model.ReservationStatus.CONFIRMED
                    2 -> com.extrotarget.extropos.domain.model.ReservationStatus.SEATED
                    3 -> com.extrotarget.extropos.domain.model.ReservationStatus.COMPLETED
                    4 -> com.extrotarget.extropos.domain.model.ReservationStatus.CANCELLED
                    5 -> com.extrotarget.extropos.domain.model.ReservationStatus.NO_SHOW
                    else -> reservation.status
                }
                viewModel.updateStatus(reservation.id, newStatus)
            }
            .show()
    }

    private fun showAddEditDialog(reservation: Reservation?) {
        val dialog = ReservationDialogFragment.newInstance(reservation)
        dialog.onSaved = { saved -> viewModel.save(saved) }
        dialog.show(parentFragmentManager, "reservation_dialog")
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
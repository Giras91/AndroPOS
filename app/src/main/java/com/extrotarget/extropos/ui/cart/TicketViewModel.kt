package com.extrotarget.extropos.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.CartItem
import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.domain.model.TicketItem
import com.extrotarget.extropos.domain.usecase.ticket.AddItemToTicketUseCase
import com.extrotarget.extropos.domain.usecase.ticket.ClearTicketUseCase
import com.extrotarget.extropos.domain.usecase.ticket.CompleteTicketUseCase
import com.extrotarget.extropos.domain.usecase.ticket.CreateTicketUseCase
import com.extrotarget.extropos.domain.usecase.ticket.GetCurrentTicketUseCase
import com.extrotarget.extropos.domain.usecase.ticket.RemoveItemFromTicketUseCase
import com.extrotarget.extropos.domain.usecase.ticket.SuspendTicketUseCase
import com.extrotarget.extropos.domain.usecase.ticket.UpdateItemQuantityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketViewModel(
    private val getCurrentTicket: GetCurrentTicketUseCase = GetCurrentTicketUseCase(),
    private val createTicket: CreateTicketUseCase = CreateTicketUseCase(),
    private val addItemToTicket: AddItemToTicketUseCase = AddItemToTicketUseCase(),
    private val updateItemQuantity: UpdateItemQuantityUseCase = UpdateItemQuantityUseCase(),
    private val removeItemFromTicket: RemoveItemFromTicketUseCase = RemoveItemFromTicketUseCase(),
    private val clearTicket: ClearTicketUseCase = ClearTicketUseCase(),
    private val suspendTicket: SuspendTicketUseCase = SuspendTicketUseCase(),
    private val completeTicket: CompleteTicketUseCase = CompleteTicketUseCase()
) : ViewModel() {

    private val _currentTicket = MutableStateFlow<Ticket?>(null)
    val currentTicket: StateFlow<Ticket?> = _currentTicket

    private val _itemCount = MutableStateFlow(0)
    val itemCount: StateFlow<Int> = _itemCount

    init {
        loadCurrentTicket()
    }

    private fun loadCurrentTicket() {
        viewModelScope.launch {
            getCurrentTicket().collect { ticket ->
                _currentTicket.value = ticket
                _itemCount.value = ticket?.items?.sumOf { it.quantity } ?: 0
            }
        }
    }

    fun addItem(cartItem: CartItem) {
        viewModelScope.launch {
            val ticketItem = TicketItem(
                id = 0, // Will be assigned by repository
                productId = cartItem.productId,
                productName = cartItem.name,
                quantity = cartItem.qty,
                unitPriceCents = cartItem.unitPriceCents,
                totalCents = cartItem.qty * cartItem.unitPriceCents
            )
            addItemToTicket(ticketItem)
        }
    }

    fun updateItemQuantity(item: TicketItem, newQuantity: Int) {
        viewModelScope.launch {
            updateItemQuantity(item, newQuantity)
        }
    }

    fun removeItem(item: TicketItem) {
        viewModelScope.launch {
            removeItemFromTicket(item)
        }
    }

    fun clearCurrentTicket() {
        viewModelScope.launch {
            clearTicket()
        }
    }

    fun suspendCurrentTicket() {
        viewModelScope.launch {
            suspendTicket()
        }
    }

    fun completeCurrentTicket() {
        viewModelScope.launch {
            completeTicket()
        }
    }
}
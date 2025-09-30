package com.example.pos.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pos.domain.model.CartItem
import com.example.pos.domain.model.Ticket
import com.example.pos.domain.model.TicketItem
import com.example.pos.domain.usecase.ticket.AddItemToTicketUseCase
import com.example.pos.domain.usecase.ticket.ClearTicketUseCase
import com.example.pos.domain.usecase.ticket.CompleteTicketUseCase
import com.example.pos.domain.usecase.ticket.CreateTicketUseCase
import com.example.pos.domain.usecase.ticket.GetCurrentTicketUseCase
import com.example.pos.domain.usecase.ticket.RemoveItemFromTicketUseCase
import com.example.pos.domain.usecase.ticket.SuspendTicketUseCase
import com.example.pos.domain.usecase.ticket.UpdateItemQuantityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val getCurrentTicket: GetCurrentTicketUseCase,
    private val createTicket: CreateTicketUseCase,
    private val addItemToTicket: AddItemToTicketUseCase,
    private val updateItemQuantity: UpdateItemQuantityUseCase,
    private val removeItemFromTicket: RemoveItemFromTicketUseCase,
    private val clearTicket: ClearTicketUseCase,
    private val suspendTicket: SuspendTicketUseCase,
    private val completeTicket: CompleteTicketUseCase
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
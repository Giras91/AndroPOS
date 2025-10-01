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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCurrentTicketUseCase: GetCurrentTicketUseCase,
    private val createTicketUseCase: CreateTicketUseCase,
    private val addItemToTicketUseCase: AddItemToTicketUseCase,
    private val updateItemQuantityUseCase: UpdateItemQuantityUseCase,
    private val removeItemFromTicketUseCase: RemoveItemFromTicketUseCase,
    private val clearTicketUseCase: ClearTicketUseCase,
    private val suspendTicketUseCase: SuspendTicketUseCase,
    private val completeTicketUseCase: CompleteTicketUseCase
) : ViewModel() {

    private val _currentTicket = MutableStateFlow<Ticket?>(null)
    val currentTicket: StateFlow<Ticket?> = _currentTicket.asStateFlow()

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items.asStateFlow()

    private val _subtotalCents = MutableStateFlow(0L)
    val subtotalCents: StateFlow<Long> = _subtotalCents.asStateFlow()

    private val _taxCents = MutableStateFlow(0L)
    val taxCents: StateFlow<Long> = _taxCents.asStateFlow()

    private val _totalCents = MutableStateFlow(0L)
    val totalCents: StateFlow<Long> = _totalCents.asStateFlow()

    private val _itemCount = MutableStateFlow(0)
    val itemCount: StateFlow<Int> = _itemCount.asStateFlow()

    init {
        loadCurrentTicket()
    }

    private fun loadCurrentTicket() {
        viewModelScope.launch {
            getCurrentTicketUseCase().collect { ticket ->
                ticket?.let {
                    _items.value = it.items.map { ticketItem ->
                        CartItem(
                            productId = ticketItem.productId,
                            name = ticketItem.name,
                            unitPriceCents = ticketItem.unitPriceCents,
                            quantity = ticketItem.quantity
                        )
                    }
                    updateTotals()
                }
            }
        }
    }

    fun addItem(productId: String, name: String, unitPriceCents: Long, quantity: Int = 1) {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            val cartItem = CartItem(
                productId = productId,
                name = name,
                quantity = quantity,
                unitPriceCents = unitPriceCents
            )
            // map CartItem -> TicketItem and call ticket use-case; rely on ticket flow to update current ticket
            val ticketItem = TicketItem(
                id = "",
                productId = cartItem.productId,
                name = cartItem.name,
                quantity = cartItem.quantity,
                unitPriceCents = cartItem.unitPriceCents,
                notes = ""
            )
            addItemToTicketUseCase(ticketItem)
        }
    }

    fun updateItemQuantity(item: CartItem, newQuantity: Int) {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            val ticketItem = TicketItem(
                id = "",
                productId = item.productId,
                name = item.name,
                quantity = item.quantity,
                unitPriceCents = item.unitPriceCents,
                notes = ""
            )
            updateItemQuantityUseCase(ticketItem, newQuantity)
        }
    }

    fun removeItem(item: CartItem) {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            val ticketItem = TicketItem(
                id = "",
                productId = item.productId,
                name = item.name,
                quantity = item.quantity,
                unitPriceCents = item.unitPriceCents,
                notes = ""
            )
            removeItemFromTicketUseCase(ticketItem)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            clearTicketUseCase()
        }
    }

    fun suspendTicket() {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            suspendTicketUseCase()
        }
    }

    fun completeTicket() {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            completeTicketUseCase()
        }
    }

    private fun updateTotals() {
        val items = _items.value
        val subtotal = items.sumOf { it.unitPriceCents * it.quantity }
        val tax = (subtotal * 0.06).toLong() // 6% tax
        val total = subtotal + tax

        _subtotalCents.value = subtotal
        _taxCents.value = tax
        _totalCents.value = total
        _itemCount.value = items.sumOf { it.quantity }
    }

    fun getFormattedSubtotal(): String {
        return "RM ${String.format("%.2f", _subtotalCents.value / 100.0)}"
    }

    fun getFormattedTax(): String {
        return "RM ${String.format("%.2f", _taxCents.value / 100.0)}"
    }

    fun getFormattedTotal(): String {
        return "RM ${String.format("%.2f", _totalCents.value / 100.0)}"
    }
}
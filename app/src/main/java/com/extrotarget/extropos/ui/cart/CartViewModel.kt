package com.extrotarget.extropos.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.CartItem
import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.domain.usecase.*
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
            val updatedTicket = addItemToTicketUseCase(currentTicket, cartItem)
            _currentTicket.value = updatedTicket
        }
    }

    fun updateItemQuantity(item: CartItem, newQuantity: Int) {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            val updatedTicket = updateItemQuantityUseCase(currentTicket, item.productId, newQuantity)
            _currentTicket.value = updatedTicket
        }
    }

    fun removeItem(item: CartItem) {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            val updatedTicket = removeItemFromTicketUseCase(currentTicket, item.productId)
            _currentTicket.value = updatedTicket
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            val clearedTicket = clearTicketUseCase(currentTicket)
            _currentTicket.value = clearedTicket
        }
    }

    fun suspendTicket() {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            val suspendedTicket = suspendTicketUseCase(currentTicket)
            _currentTicket.value = suspendedTicket
        }
    }

    fun completeTicket() {
        viewModelScope.launch {
            val currentTicket = _currentTicket.value ?: return@launch
            val completedTicket = completeTicketUseCase(currentTicket)
            _currentTicket.value = completedTicket
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
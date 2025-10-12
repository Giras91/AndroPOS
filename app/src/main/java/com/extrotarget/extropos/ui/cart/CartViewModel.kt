package com.extrotarget.extropos.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.CartItem
import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.domain.model.TicketItem
import com.extrotarget.extropos.domain.usecase.ticket.AddItemToTicketUseCase
import com.extrotarget.extropos.data.model.PaymentMethod
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
import android.util.Log

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCurrentTicketUseCase: GetCurrentTicketUseCase,
    private val createTicketUseCase: CreateTicketUseCase,
    private val addItemToTicketUseCase: AddItemToTicketUseCase,
    private val updateItemQuantityUseCase: UpdateItemQuantityUseCase,
    private val removeItemFromTicketUseCase: RemoveItemFromTicketUseCase,
    private val clearTicketUseCase: ClearTicketUseCase,
    private val suspendTicketUseCase: SuspendTicketUseCase,
    private val completeTicketUseCase: CompleteTicketUseCase,
    private val addTicketTenderUseCase: com.extrotarget.extropos.domain.usecase.ticket.AddTicketTenderUseCase,
    private val paymentConfigRepo: com.extrotarget.extropos.data.repository.PaymentConfigurationRepository
) : ViewModel() {

    private val _currentTicket = MutableStateFlow<Ticket?>(null)
    val currentTicket: StateFlow<Ticket?> = _currentTicket.asStateFlow()

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items.asStateFlow()
    
    // Temporary simplified cart storage (bypassing ticket system for debugging)
    private val _tempCartItems = mutableListOf<CartItem>()

    private val _subtotalCents = MutableStateFlow(0L)
    val subtotalCents: StateFlow<Long> = _subtotalCents.asStateFlow()

    private val _taxCents = MutableStateFlow(0L)
    val taxCents: StateFlow<Long> = _taxCents.asStateFlow()

    private val _totalCents = MutableStateFlow(0L)
    val totalCents: StateFlow<Long> = _totalCents.asStateFlow()

    private var selectedPaymentMethodId: String? = null

    private val _itemCount = MutableStateFlow(0)
    val itemCount: StateFlow<Int> = _itemCount.asStateFlow()

    init {
        loadCurrentTicket()
    }

    private fun loadCurrentTicket() {
        viewModelScope.launch {
            getCurrentTicketUseCase().collect { ticket ->
                if (ticket != null) {
                    _currentTicket.value = ticket
                    _items.value = ticket.items.map { ticketItem ->
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
        Log.d("CartViewModel", "addItem called: productId=$productId, name=$name, price=$unitPriceCents, qty=$quantity")
        
        // TEMPORARY: Use simplified cart for immediate testing
        val existingIndex = _tempCartItems.indexOfFirst { it.productId == productId }
        if (existingIndex >= 0) {
            // Update quantity if item already exists
            _tempCartItems[existingIndex] = _tempCartItems[existingIndex].copy(
                quantity = _tempCartItems[existingIndex].quantity + quantity
            )
            Log.d("CartViewModel", "Updated existing item quantity")
        } else {
            // Add new item
            val cartItem = CartItem(
                productId = productId,
                name = name,
                quantity = quantity,
                unitPriceCents = unitPriceCents
            )
            _tempCartItems.add(cartItem)
            Log.d("CartViewModel", "Added new item to cart")
        }
        
        // Update the StateFlow
        _items.value = _tempCartItems.toList()
        updateTotals()
        
        Log.d("CartViewModel", "Cart now has ${_tempCartItems.size} different products, total items: ${_tempCartItems.sumOf { it.quantity }}")
        
        // ALSO try the original ticket-based approach in parallel for comparison
        viewModelScope.launch {
            try {
                var currentTicket = _currentTicket.value
                Log.d("CartViewModel", "Current ticket: ${currentTicket?.id}")
                
                if (currentTicket == null) {
                    // Create a new ticket when none exists (debug-friendly)
                    try {
                        Log.d("CartViewModel", "Creating new ticket...")
                        val created = createTicketUseCase()
                        _currentTicket.value = created
                        currentTicket = created
                        Log.i("CartViewModel", "CreateTicket success: id=${created.id}")
                    } catch (e: Exception) {
                        Log.e("CartViewModel", "CreateTicket failed: ${e.message}", e)
                        return@launch
                    }
                }
                
                val ticketItem = TicketItem(
                    id = "",
                    productId = productId,
                    name = name,
                    quantity = quantity,
                    unitPriceCents = unitPriceCents,
                    notes = ""
                )
                
                Log.d("CartViewModel", "Adding item to ticket: ${ticketItem.name}")
                addItemToTicketUseCase(ticketItem)
                
                Log.d("CartViewModel", "Item added to ticket successfully")
                
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error in ticket-based addItem", e)
            }
        }
    }

    fun updateItemQuantity(item: CartItem, newQuantity: Int) {
        Log.d("CartViewModel", "updateItemQuantity: ${item.name} to quantity $newQuantity")
        
        // TEMPORARY: Update simplified cart
        val index = _tempCartItems.indexOfFirst { it.productId == item.productId }
        if (index >= 0) {
            if (newQuantity > 0) {
                _tempCartItems[index] = _tempCartItems[index].copy(quantity = newQuantity)
            } else {
                _tempCartItems.removeAt(index)
            }
            _items.value = _tempCartItems.toList()
            updateTotals()
        }
        
        // Also try original ticket approach
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
        Log.d("CartViewModel", "removeItem: ${item.name}")
        
        // TEMPORARY: Remove from simplified cart
        _tempCartItems.removeAll { it.productId == item.productId }
        _items.value = _tempCartItems.toList()
        updateTotals()
        
        // Also try original ticket approach
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

            // Attach selected payment method and financial metadata to ticket if necessary
            // (This is a simplified hook - real ticket model changes may be required)
            if (selectedPaymentMethodId == null) {
                // If no method selected, fall back to default enabled method
                selectedPaymentMethodId = paymentConfigRepo.getPaymentMethods().find { it.isEnabled }?.id
            }

            // If a payment method is selected, persist it as a TicketTender before completing
            selectedPaymentMethodId?.let { pmId ->
                try {
                    // Use payment method id as tender id for now; amount is total
                    val tender = com.extrotarget.extropos.domain.model.TicketTender(
                        id = java.util.UUID.randomUUID().toString(),
                        ticketId = currentTicket.id,
                        tenderType = paymentConfigRepo.getPaymentMethods().find { it.id == pmId }?.displayName
                            ?: "Unknown",
                        amountCents = _totalCents.value,
                        reference = pmId
                    )
                    addTicketTenderUseCase(tender)
                } catch (e: Exception) {
                    // Log but proceed to complete the ticket
                    android.util.Log.w("CartViewModel", "Failed to add ticket tender: ${e.message}")
                }
            }

            completeTicketUseCase()
        }
    }

    fun setSelectedPaymentMethod(paymentMethodId: String) {
        selectedPaymentMethodId = paymentMethodId
    }

    fun getEnabledPaymentMethods(): List<PaymentMethod> {
        return try {
            paymentConfigRepo.getPaymentMethods().filter { it.isEnabled }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun updateTotals() {
        val items = _items.value
        val subtotal = items.sumOf { it.unitPriceCents * it.quantity }
        val financial = paymentConfigRepo.getFinancialSettings()

        val tax = if (financial.enableTax) {
            // If manual tax percentage is provided (>=0), use it, otherwise use default tax rate
            val taxPercent = if (financial.manualTaxPercentage >= 0.0) financial.manualTaxPercentage else (paymentConfigRepo.getDefaultTaxRate()?.rate ?: 6.0)
            (subtotal * (taxPercent / 100.0)).toLong()
        } else 0L

        // Apply service charge if enabled
        val serviceCharge = if (financial.enableServiceCharge) {
            (subtotal * (financial.serviceChargePercentage / 100.0)).toLong()
        } else 0L

        val total = subtotal + tax + serviceCharge

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
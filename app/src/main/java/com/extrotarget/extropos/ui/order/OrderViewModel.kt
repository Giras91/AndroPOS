package com.extrotarget.extropos.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Order
import com.extrotarget.extropos.domain.model.OrderItem
import com.extrotarget.extropos.domain.model.OrderStatus
import com.extrotarget.extropos.domain.model.OrderType
import com.extrotarget.extropos.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val createOrder: CreateOrderUseCase,
    private val getOrder: GetOrderUseCase,
    private val updateOrderStatus: UpdateOrderStatusUseCase,
    private val addItemToOrder: AddItemToOrderUseCase,
    private val updateOrderItem: UpdateOrderItemUseCase,
    private val removeOrderItem: RemoveOrderItemUseCase,
    private val getActiveOrders: GetActiveOrdersUseCase
) : ViewModel() {

    private val _currentOrder = MutableStateFlow<Order?>(null)
    val currentOrder: StateFlow<Order?> = _currentOrder

    private val _activeOrders = MutableStateFlow<List<Order>>(emptyList())
    val activeOrders: StateFlow<List<Order>> = _activeOrders

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadActiveOrders()
    }

    fun createNewOrder(tableId: String, orderType: OrderType = OrderType.DINE_IN) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val orderId = createOrder(tableId, orderType)
                loadOrder(orderId)
                loadActiveOrders()
            } catch (e: Exception) {
                _error.value = "Failed to create order: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadOrder(orderId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val order = getOrder(orderId)
                _currentOrder.value = order
            } catch (e: Exception) {
                _error.value = "Failed to load order: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addItemToCurrentOrder(menuItemId: String, menuItemName: String, quantity: Int, unitPrice: Long, notes: String? = null) {
        val order = _currentOrder.value ?: return

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // The AddItemToOrderUseCase expects a MenuItem; construct a lightweight MenuItem to reuse it
                val menuItem = com.extrotarget.extropos.domain.model.MenuItem(
                    id = menuItemId,
                    categoryId = "",
                    name = menuItemName,
                    description = "",
                    priceCents = unitPrice,
                    imageUrl = "",
                    preparationTimeMinutes = 0,
                    allergens = emptyList(),
                    isAvailable = true
                )

                addItemToOrder(order.id, menuItem, quantity, notes)
                loadOrder(order.id) // Refresh order with updated items
            } catch (e: Exception) {
                _error.value = "Failed to add item: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateOrderItem(orderId: String, item: OrderItem) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                updateOrderItem(orderId, item)
                loadOrder(orderId)
            } catch (e: Exception) {
                _error.value = "Failed to update item: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun removeItemFromOrder(orderId: String, itemId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                removeOrderItem(orderId, itemId)
                loadOrder(orderId)
            } catch (e: Exception) {
                _error.value = "Failed to remove item: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateOrderStatus(orderId: String, status: OrderStatus) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                updateOrderStatus(orderId, status)
                loadOrder(orderId)
                loadActiveOrders()
            } catch (e: Exception) {
                _error.value = "Failed to update order status: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadActiveOrders() {
        viewModelScope.launch {
            try {
                val orders = getActiveOrders()
                _activeOrders.value = orders
            } catch (e: Exception) {
                _error.value = "Failed to load active orders: ${e.message}"
            }
        }
    }

    fun clearCurrentOrder() {
        _currentOrder.value = null
    }

    fun getOrderTotal(): Long {
        val items = _currentOrder.value?.items ?: return 0L
        val subtotal = items.sumOf { it.totalPriceCents }
        val tax = (subtotal * 0.1).toLong()
        return subtotal + tax
    }

    fun getOrderSubtotal(): Long {
        val items = _currentOrder.value?.items ?: return 0L
        return items.sumOf { it.totalPriceCents }
    }

    fun getOrderTax(): Long {
        val subtotal = getOrderSubtotal()
        return (subtotal * 0.1).toLong()
    }
}
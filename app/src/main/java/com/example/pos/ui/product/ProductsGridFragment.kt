package com.example.pos.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pos.databinding.FragmentProductsGridBinding
import com.example.pos.domain.model.CartItem
import com.example.pos.domain.model.Product
import com.example.pos.ui.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsGridFragment : Fragment() {

    private var _binding: FragmentProductsGridBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        observeViewModel()
        loadProducts()
    }

    private fun setupRecyclerView() {
        productsAdapter = ProductsAdapter { product ->
            addProductToCart(product)
        }

        binding.productsRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = productsAdapter
        }
    }

    private fun setupSearch() {
        binding.searchEditText.addTextChangedListener { text ->
            val query = text?.toString() ?: ""
            productViewModel.searchProducts(query)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.products.collect { products ->
                productsAdapter.submitList(products)
            }
        }
    }

    private fun loadProducts() {
        productViewModel.loadProducts()
    }

    private fun addProductToCart(product: Product) {
        if (product.stockQuantity <= 0) {
            // TODO: Show out of stock message
            return
        }

        val cartItem = CartItem(
            productId = product.id,
            name = product.name,
            qty = 1,
            unitPriceCents = product.priceCents
        )

        cartViewModel.addItem(cartItem)
        // TODO: Show added to cart feedback
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.extrotarget.extropos.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.extrotarget.extropos.ui.cart.CartViewModel
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import com.extrotarget.extropos.databinding.FragmentProductDetailBinding

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    // ProductViewModel is kept parent/activity scoped in the grid; use activityViewModels to share
    private val productViewModel: ProductViewModel by viewModels({ requireParentFragment() })
    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getString("productId")
        if (productId != null) {
            val product = productViewModel.getProductById(productId)
            if (product != null) {
                bindProduct(product)

                // Quantity controls
                var qty = 1
                binding.qtyValue.text = qty.toString()
                binding.qtyDecrease.setOnClickListener {
                    if (qty > 1) {
                        qty -= 1
                        binding.qtyValue.text = qty.toString()
                    }
                }
                binding.qtyIncrease.setOnClickListener {
                    // Respect stock limit if available
                    if (product.stockQuantity <= 0 || qty < product.stockQuantity) {
                        qty += 1
                        binding.qtyValue.text = qty.toString()
                    }
                }

                binding.addToCartButton.setOnClickListener {
                    // Add selected quantity to the cart and show confirmation
                    cartViewModel.addItem(product.id, product.name, product.priceCents, qty)
                    Log.i("DashboardDebug", "AddToCart: id=${product.id}, name=${product.name}, priceCents=${product.priceCents}, qty=$qty")
                    Snackbar.make(requireView(), "Added $qty x ${product.name} to cart", Snackbar.LENGTH_SHORT).show()
                    // Navigate back to the previous screen (product list)
                    findNavController().navigateUp()
                }

            } else {
                // product not found, show placeholder
                binding.productName.text = "Unknown product"
            }
        }
    }

    private fun bindProduct(product: com.extrotarget.extropos.domain.model.Product) {
        binding.productName.text = product.name
        binding.productPrice.text = "RM ${String.format("%.2f", product.priceCents / 100.0)}"
        binding.productDescription.text = product.description
        binding.productStock.text = "Stock: ${product.stockQuantity}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

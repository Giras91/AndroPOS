package com.extrotarget.extropos.ui.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.domain.repository.IProductRepository
import javax.inject.Inject
import android.widget.Toast
import android.util.Log
import java.io.OutputStreamWriter

@AndroidEntryPoint
class DebugProductActivity : AppCompatActivity() {
    @Inject
    lateinit var productRepository: IProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                val debugProduct = com.extrotarget.extropos.domain.model.Product(
                    id = "dbg-prod-1",
                    name = "DebugProduct",
                    priceCents = 420,
                    categoryId = "dbg-cat-1",
                    imageUrl = "",
                    description = "Inserted by DebugProductActivity"
                )
                productRepository.upsertProduct(debugProduct)

                val products = productRepository.getAllProducts()
                val sb = StringBuilder()
                for (p in products) {
                    sb.append("${p.id}|${p.name}|${p.priceCents}\n")
                }

                openFileOutput("products_dump.txt", MODE_PRIVATE).use { fos ->
                    OutputStreamWriter(fos).use { it.write(sb.toString()) }
                }

                Toast.makeText(this@DebugProductActivity, "Dumped ${products.size} products", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Log.e("DebugProductActivity", "error dumping products", e)
                Toast.makeText(this@DebugProductActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                finish()
            }
        }
    }
}

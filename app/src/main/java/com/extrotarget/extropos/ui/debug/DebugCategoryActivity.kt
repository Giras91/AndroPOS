package com.extrotarget.extropos.ui.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.domain.repository.IMenuRepository
import javax.inject.Inject
import android.widget.Toast
import android.util.Log
import java.io.OutputStreamWriter

@AndroidEntryPoint
class DebugCategoryActivity : AppCompatActivity() {
    @Inject
    lateinit var menuRepository: IMenuRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                // Upsert a debug category so we can verify persistence
                val debugCategory = com.extrotarget.extropos.domain.model.Category(
                    id = "dbg-1",
                    name = "Debug Category",
                    description = "Inserted by DebugCategoryActivity",
                    imageUrl = ""
                )
                menuRepository.upsertCategory(debugCategory)

                val cats = menuRepository.getAllCategories()
                val sb = StringBuilder()
                for (c in cats) {
                    sb.append("${c.id}|${c.name}|${c.description}\n")
                }

                openFileOutput("categories_dump.txt", MODE_PRIVATE).use { fos ->
                    OutputStreamWriter(fos).use { it.write(sb.toString()) }
                }

                Toast.makeText(this@DebugCategoryActivity, "Dumped ${cats.size} categories", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Log.e("DebugCategoryActivity", "error dumping categories", e)
                Toast.makeText(this@DebugCategoryActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                finish()
            }
        }
    }
}

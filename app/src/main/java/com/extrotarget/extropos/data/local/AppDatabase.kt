package com.extrotarget.extropos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.extrotarget.extropos.data.local.dao.*
import com.extrotarget.extropos.data.local.entity.*

@Database(
    entities = [
        CategoryEntity::class,
        MenuItemEntity::class,
        ProductEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        TableEntity::class,
        SaleEntity::class,
        SaleItemEntity::class,
        CustomerEntity::class,
        InventoryTransactionEntity::class,
        PaymentEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun menuItemDao(): MenuItemDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun tableDao(): TableDao

    companion object {
        private const val DB_NAME = "extropos.db"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

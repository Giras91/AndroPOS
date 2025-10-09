package com.extrotarget.extropos.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&\u00a8\u0006\u0010"}, d2 = {"Lcom/extrotarget/extropos/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "categoryDao", "Lcom/extrotarget/extropos/data/local/dao/CategoryDao;", "menuItemDao", "Lcom/extrotarget/extropos/data/local/dao/MenuItemDao;", "orderDao", "Lcom/extrotarget/extropos/data/local/dao/OrderDao;", "orderItemDao", "Lcom/extrotarget/extropos/data/local/dao/OrderItemDao;", "productDao", "Lcom/extrotarget/extropos/data/local/dao/ProductDao;", "tableDao", "Lcom/extrotarget/extropos/data/local/dao/TableDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.extrotarget.extropos.data.local.entity.CategoryEntity.class, com.extrotarget.extropos.data.local.entity.MenuItemEntity.class, com.extrotarget.extropos.data.local.entity.ProductEntity.class, com.extrotarget.extropos.data.local.entity.OrderEntity.class, com.extrotarget.extropos.data.local.entity.OrderItemEntity.class, com.extrotarget.extropos.data.local.entity.TableEntity.class, com.extrotarget.extropos.data.local.entity.SaleEntity.class, com.extrotarget.extropos.data.local.entity.SaleItemEntity.class, com.extrotarget.extropos.data.local.entity.CustomerEntity.class, com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity.class, com.extrotarget.extropos.data.local.entity.PaymentEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DB_NAME = "extropos.db";
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.data.local.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.local.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.local.dao.MenuItemDao menuItemDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.local.dao.ProductDao productDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.local.dao.OrderDao orderDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.local.dao.OrderItemDao orderItemDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.local.dao.TableDao tableDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/extrotarget/extropos/data/local/AppDatabase$Companion;", "", "()V", "DB_NAME", "", "create", "Lcom/extrotarget/extropos/data/local/AppDatabase;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.extrotarget.extropos.data.local.AppDatabase create(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}
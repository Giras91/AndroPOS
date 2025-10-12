package com.extrotarget.extropos.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&\u00a8\u0006\u0014"}, d2 = {"Lcom/extrotarget/extropos/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "categoryDao", "Lcom/extrotarget/extropos/data/local/dao/CategoryDao;", "menuItemDao", "Lcom/extrotarget/extropos/data/local/dao/MenuItemDao;", "orderDao", "Lcom/extrotarget/extropos/data/local/dao/OrderDao;", "orderItemDao", "Lcom/extrotarget/extropos/data/local/dao/OrderItemDao;", "productDao", "Lcom/extrotarget/extropos/data/local/dao/ProductDao;", "shiftDao", "Lcom/extrotarget/extropos/data/local/dao/ShiftDao;", "tableDao", "Lcom/extrotarget/extropos/data/local/dao/TableDao;", "ticketDao", "Lcom/extrotarget/extropos/data/local/room/RoomTicketDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.extrotarget.extropos.data.local.entity.CategoryEntity.class, com.extrotarget.extropos.data.local.entity.MenuItemEntity.class, com.extrotarget.extropos.data.local.entity.ProductEntity.class, com.extrotarget.extropos.data.local.entity.OrderEntity.class, com.extrotarget.extropos.data.local.entity.OrderItemEntity.class, com.extrotarget.extropos.data.local.entity.TableEntity.class, com.extrotarget.extropos.data.local.entity.TicketEntity.class, com.extrotarget.extropos.data.local.entity.TicketItemEntity.class, com.extrotarget.extropos.data.local.entity.TicketTenderEntity.class, com.extrotarget.extropos.data.local.entity.TenderEntity.class, com.extrotarget.extropos.data.local.entity.DepartmentEntity.class, com.extrotarget.extropos.data.local.entity.TaxGroupEntity.class, com.extrotarget.extropos.data.local.entity.SaleEntity.class, com.extrotarget.extropos.data.local.entity.SaleItemEntity.class, com.extrotarget.extropos.data.local.entity.CustomerEntity.class, com.extrotarget.extropos.data.local.entity.InventoryTransactionEntity.class, com.extrotarget.extropos.data.local.entity.ShiftEntity.class, com.extrotarget.extropos.data.local.entity.PaymentEntity.class}, version = 4, exportSchema = true)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DB_NAME = "extropos.db";
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_2_3 = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_3_4 = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration[] ALL_MIGRATIONS = null;
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
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.local.dao.ShiftDao shiftDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.data.local.room.RoomTicketDao ticketDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/extrotarget/extropos/data/local/AppDatabase$Companion;", "", "()V", "ALL_MIGRATIONS", "", "Landroidx/room/migration/Migration;", "getALL_MIGRATIONS", "()[Landroidx/room/migration/Migration;", "[Landroidx/room/migration/Migration;", "DB_NAME", "", "MIGRATION_1_2", "MIGRATION_2_3", "MIGRATION_3_4", "create", "Lcom/extrotarget/extropos/data/local/AppDatabase;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.extrotarget.extropos.data.local.AppDatabase create(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration[] getALL_MIGRATIONS() {
            return null;
        }
    }
}
package com.extrotarget.extropos.ui.table;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0012J\u0006\u0010%\u001a\u00020&J\u0006\u0010\'\u001a\u00020&J\u0010\u0010(\u001a\u0004\u0018\u00010\u00102\u0006\u0010$\u001a\u00020\u0012J\u0006\u0010)\u001a\u00020&J\u0006\u0010*\u001a\u00020#J\u0006\u0010+\u001a\u00020#J\u0006\u0010,\u001a\u00020#J\u000e\u0010-\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0012J\u000e\u0010.\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0012J\u000e\u0010/\u001a\u00020#2\u0006\u00100\u001a\u00020\u0010J\u0016\u0010\u0006\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00122\u0006\u00101\u001a\u000202R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001aR\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001aR\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/extrotarget/extropos/ui/table/TableViewModel;", "Landroidx/lifecycle/ViewModel;", "getTables", "Lcom/extrotarget/extropos/domain/usecase/GetTablesUseCase;", "getTable", "Lcom/extrotarget/extropos/domain/usecase/GetTableUseCase;", "updateTableStatus", "Lcom/extrotarget/extropos/domain/usecase/UpdateTableStatusUseCase;", "getAvailableTables", "Lcom/extrotarget/extropos/domain/usecase/GetAvailableTablesUseCase;", "getOccupiedTables", "Lcom/extrotarget/extropos/domain/usecase/GetOccupiedTablesUseCase;", "(Lcom/extrotarget/extropos/domain/usecase/GetTablesUseCase;Lcom/extrotarget/extropos/domain/usecase/GetTableUseCase;Lcom/extrotarget/extropos/domain/usecase/UpdateTableStatusUseCase;Lcom/extrotarget/extropos/domain/usecase/GetAvailableTablesUseCase;Lcom/extrotarget/extropos/domain/usecase/GetOccupiedTablesUseCase;)V", "_availableTables", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/extrotarget/extropos/domain/model/Table;", "_error", "", "_isLoading", "", "_occupiedTables", "_selectedTable", "_tables", "availableTables", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "error", "getError", "isLoading", "occupiedTables", "selectedTable", "getSelectedTable", "tables", "freeTable", "", "tableId", "getAvailableTableCount", "", "getOccupiedTableCount", "getTableById", "getTotalTableCount", "loadAvailableTables", "loadOccupiedTables", "loadTables", "occupyTable", "reserveTable", "selectTable", "table", "status", "Lcom/extrotarget/extropos/domain/model/TableStatus;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TableViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetTablesUseCase getTables = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetTableUseCase getTable = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.UpdateTableStatusUseCase updateTableStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetAvailableTablesUseCase getAvailableTables = null;
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.domain.usecase.GetOccupiedTablesUseCase getOccupiedTables = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> _tables = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> tables = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> _availableTables = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> availableTables = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> _occupiedTables = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> occupiedTables = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.extrotarget.extropos.domain.model.Table> _selectedTable = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.domain.model.Table> selectedTable = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    
    @javax.inject.Inject()
    public TableViewModel(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetTablesUseCase getTables, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetTableUseCase getTable, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.UpdateTableStatusUseCase updateTableStatus, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetAvailableTablesUseCase getAvailableTables, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.usecase.GetOccupiedTablesUseCase getOccupiedTables) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> getTables() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> getAvailableTables() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.extrotarget.extropos.domain.model.Table>> getOccupiedTables() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.extrotarget.extropos.domain.model.Table> getSelectedTable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    public final void loadTables() {
    }
    
    public final void loadAvailableTables() {
    }
    
    public final void loadOccupiedTables() {
    }
    
    public final void selectTable(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.Table table) {
    }
    
    public final void updateTableStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.domain.model.TableStatus status) {
    }
    
    public final void occupyTable(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId) {
    }
    
    public final void freeTable(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId) {
    }
    
    public final void reserveTable(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.extrotarget.extropos.domain.model.Table getTableById(@org.jetbrains.annotations.NotNull()
    java.lang.String tableId) {
        return null;
    }
    
    public final int getAvailableTableCount() {
        return 0;
    }
    
    public final int getOccupiedTableCount() {
        return 0;
    }
    
    public final int getTotalTableCount() {
        return 0;
    }
}
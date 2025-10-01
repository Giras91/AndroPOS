package com.extrotarget.extropos.ui.menu;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0010\u0011B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/extrotarget/extropos/ui/menu/MenuItemAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/extrotarget/extropos/domain/model/MenuItem;", "Lcom/extrotarget/extropos/ui/menu/MenuItemAdapter$MenuItemViewHolder;", "onMenuItemClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MenuItemDiffCallback", "MenuItemViewHolder", "app_debug"})
public final class MenuItemAdapter extends androidx.recyclerview.widget.ListAdapter<com.extrotarget.extropos.domain.model.MenuItem, com.extrotarget.extropos.ui.menu.MenuItemAdapter.MenuItemViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.extrotarget.extropos.domain.model.MenuItem, kotlin.Unit> onMenuItemClick = null;
    
    public MenuItemAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.extrotarget.extropos.domain.model.MenuItem, kotlin.Unit> onMenuItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.extrotarget.extropos.ui.menu.MenuItemAdapter.MenuItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.ui.menu.MenuItemAdapter.MenuItemViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/extrotarget/extropos/ui/menu/MenuItemAdapter$MenuItemDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/extrotarget/extropos/domain/model/MenuItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    static final class MenuItemDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.extrotarget.extropos.domain.model.MenuItem> {
        
        public MenuItemDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.MenuItem oldItem, @org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.MenuItem newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.MenuItem oldItem, @org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.MenuItem newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/extrotarget/extropos/ui/menu/MenuItemAdapter$MenuItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/extrotarget/extropos/databinding/ItemMenuItemBinding;", "(Lcom/extrotarget/extropos/ui/menu/MenuItemAdapter;Lcom/extrotarget/extropos/databinding/ItemMenuItemBinding;)V", "bind", "", "menuItem", "Lcom/extrotarget/extropos/domain/model/MenuItem;", "formatPrice", "", "priceCents", "", "app_debug"})
    public final class MenuItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.extrotarget.extropos.databinding.ItemMenuItemBinding binding = null;
        
        public MenuItemViewHolder(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.databinding.ItemMenuItemBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.MenuItem menuItem) {
        }
        
        private final java.lang.String formatPrice(long priceCents) {
            return null;
        }
    }
}
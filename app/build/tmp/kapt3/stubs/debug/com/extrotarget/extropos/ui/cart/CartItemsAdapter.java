package com.extrotarget.extropos.ui.cart;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0012\u0013B3\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\u00072\n\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u001c\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0016R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/extrotarget/extropos/ui/cart/CartItemsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/extrotarget/extropos/domain/model/CartItem;", "Lcom/extrotarget/extropos/ui/cart/CartItemsAdapter$CartItemViewHolder;", "onQuantityChange", "Lkotlin/Function2;", "", "", "onRemoveItem", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "CartItemDiffCallback", "CartItemViewHolder", "app_debug"})
public final class CartItemsAdapter extends androidx.recyclerview.widget.ListAdapter<com.extrotarget.extropos.domain.model.CartItem, com.extrotarget.extropos.ui.cart.CartItemsAdapter.CartItemViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function2<com.extrotarget.extropos.domain.model.CartItem, java.lang.Integer, kotlin.Unit> onQuantityChange = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.extrotarget.extropos.domain.model.CartItem, kotlin.Unit> onRemoveItem = null;
    
    public CartItemsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.extrotarget.extropos.domain.model.CartItem, ? super java.lang.Integer, kotlin.Unit> onQuantityChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.extrotarget.extropos.domain.model.CartItem, kotlin.Unit> onRemoveItem) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.extrotarget.extropos.ui.cart.CartItemsAdapter.CartItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.ui.cart.CartItemsAdapter.CartItemViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/extrotarget/extropos/ui/cart/CartItemsAdapter$CartItemDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/extrotarget/extropos/domain/model/CartItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    static final class CartItemDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.extrotarget.extropos.domain.model.CartItem> {
        
        public CartItemDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.CartItem oldItem, @org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.CartItem newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.CartItem oldItem, @org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.CartItem newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/extrotarget/extropos/ui/cart/CartItemsAdapter$CartItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/extrotarget/extropos/ui/cart/CartItemsAdapter;Landroid/view/View;)V", "decreaseButton", "Landroid/widget/Button;", "increaseButton", "productNameTextView", "Landroid/widget/TextView;", "quantityTextView", "removeButton", "totalPriceTextView", "unitPriceTextView", "bind", "", "item", "Lcom/extrotarget/extropos/domain/model/CartItem;", "app_debug"})
    public final class CartItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView productNameTextView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView quantityTextView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView unitPriceTextView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView totalPriceTextView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.Button increaseButton = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.Button decreaseButton = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.Button removeButton = null;
        
        public CartItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.domain.model.CartItem item) {
        }
    }
}
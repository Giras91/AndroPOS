package com.example.pos.ui.cart;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B3\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0016R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/pos/ui/cart/CartItemsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "error/NonExistentClass", "Lcom/example/pos/ui/cart/CartItemsAdapter$CartItemViewHolder;", "onQuantityChanged", "Lkotlin/Function2;", "", "", "onItemRemoved", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "CartItemDiffCallback", "CartItemViewHolder", "app_debug"})
public final class CartItemsAdapter extends androidx.recyclerview.widget.ListAdapter<error.NonExistentClass, com.example.pos.ui.cart.CartItemsAdapter.CartItemViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function2<error.NonExistentClass, java.lang.Integer, kotlin.Unit> onQuantityChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<error.NonExistentClass, kotlin.Unit> onItemRemoved = null;
    
    public CartItemsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super error.NonExistentClass, ? super java.lang.Integer, kotlin.Unit> onQuantityChanged, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super error.NonExistentClass, kotlin.Unit> onItemRemoved) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.pos.ui.cart.CartItemsAdapter.CartItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.pos.ui.cart.CartItemsAdapter.CartItemViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a2\u0006\u0002\u0010\bJ\u001d\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a2\u0006\u0002\u0010\b\u00a8\u0006\n"}, d2 = {"Lcom/example/pos/ui/cart/CartItemsAdapter$CartItemDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "error/NonExistentClass", "()V", "areContentsTheSame", "", "oldItem", "newItem", "(Lerror/NonExistentClass;Lerror/NonExistentClass;)Z", "areItemsTheSame", "app_debug"})
    public static final class CartItemDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<error.NonExistentClass> {
        
        public CartItemDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        error.NonExistentClass oldItem, @org.jetbrains.annotations.NotNull()
        error.NonExistentClass newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        error.NonExistentClass oldItem, @org.jetbrains.annotations.NotNull()
        error.NonExistentClass newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\u0002\u0010\nJ\u0013\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000eR\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/pos/ui/cart/CartItemsAdapter$CartItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "error/NonExistentClass", "onQuantityChanged", "Lkotlin/Function2;", "", "", "onItemRemoved", "Lkotlin/Function1;", "(Lerror/NonExistentClass;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "Lerror/NonExistentClass;", "bind", "item", "(Lerror/NonExistentClass;)V", "app_debug"})
    public static final class CartItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final error.NonExistentClass binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function2<error.NonExistentClass, java.lang.Integer, kotlin.Unit> onQuantityChanged = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<error.NonExistentClass, kotlin.Unit> onItemRemoved = null;
        
        public CartItemViewHolder(@org.jetbrains.annotations.NotNull()
        error.NonExistentClass binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super error.NonExistentClass, ? super java.lang.Integer, kotlin.Unit> onQuantityChanged, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super error.NonExistentClass, kotlin.Unit> onItemRemoved) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        error.NonExistentClass item) {
        }
    }
}
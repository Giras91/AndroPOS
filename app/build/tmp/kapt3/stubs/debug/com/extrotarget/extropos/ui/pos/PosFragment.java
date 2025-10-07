package com.extrotarget.extropos.ui.pos;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J&\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\u001a\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\"2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\n\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001c\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/extrotarget/extropos/ui/pos/PosFragment;", "Landroidx/fragment/app/Fragment;", "()V", "cartRecycler", "Landroidx/recyclerview/widget/RecyclerView;", "cartViewModel", "Lcom/extrotarget/extropos/ui/cart/CartViewModel;", "getCartViewModel", "()Lcom/extrotarget/extropos/ui/cart/CartViewModel;", "cartViewModel$delegate", "Lkotlin/Lazy;", "checkoutButton", "Landroid/widget/Button;", "orderViewModel", "Lcom/extrotarget/extropos/ui/order/OrderViewModel;", "getOrderViewModel", "()Lcom/extrotarget/extropos/ui/order/OrderViewModel;", "orderViewModel$delegate", "productsGridFragment", "Lcom/extrotarget/extropos/ui/product/ProductsGridFragment;", "subtotalView", "Landroid/widget/TextView;", "taxView", "ticketViewModel", "Lcom/extrotarget/extropos/ui/cart/TicketViewModel;", "getTicketViewModel", "()Lcom/extrotarget/extropos/ui/cart/TicketViewModel;", "ticketViewModel$delegate", "totalView", "autoAddProduct", "", "productId", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "app_debug"})
public final class PosFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy cartViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy ticketViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy orderViewModel$delegate = null;
    private androidx.recyclerview.widget.RecyclerView cartRecycler;
    private android.widget.TextView subtotalView;
    private android.widget.TextView taxView;
    private android.widget.TextView totalView;
    private android.widget.Button checkoutButton;
    @org.jetbrains.annotations.Nullable()
    private com.extrotarget.extropos.ui.product.ProductsGridFragment productsGridFragment;
    
    public PosFragment() {
        super();
    }
    
    private final com.extrotarget.extropos.ui.cart.CartViewModel getCartViewModel() {
        return null;
    }
    
    private final com.extrotarget.extropos.ui.cart.TicketViewModel getTicketViewModel() {
        return null;
    }
    
    private final com.extrotarget.extropos.ui.order.OrderViewModel getOrderViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void autoAddProduct(@org.jetbrains.annotations.NotNull()
    java.lang.String productId) {
    }
}
package com.extrotarget.extropos;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0002J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\fH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/extrotarget/extropos/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authViewModel", "Lcom/extrotarget/extropos/ui/auth/AuthViewModel;", "getAuthViewModel", "()Lcom/extrotarget/extropos/ui/auth/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "binding", "Lcom/extrotarget/extropos/databinding/ActivityMainBinding;", "navigateToLockScreen", "", "navigateToLogin", "navigateToMainApp", "observeAuthState", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupNavigation", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.extrotarget.extropos.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy authViewModel$delegate = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.extrotarget.extropos.ui.auth.AuthViewModel getAuthViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupNavigation() {
    }
    
    private final void observeAuthState() {
    }
    
    private final void navigateToMainApp() {
    }
    
    private final void navigateToLockScreen() {
    }
    
    private final void navigateToLogin() {
    }
}
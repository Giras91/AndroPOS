package com.extrotarget.extropos.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0002\f\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/extrotarget/extropos/services/AppwriteService;", "", "()V", "healthApi", "Lcom/extrotarget/extropos/services/HealthApi;", "kotlin.jvm.PlatformType", "retrofit", "Lretrofit2/Retrofit;", "pingAppwrite", "", "callback", "Lcom/extrotarget/extropos/services/AppwriteService$PingCallback;", "Companion", "PingCallback", "app_debug"})
public final class AppwriteService {
    private final retrofit2.Retrofit retrofit = null;
    private final com.extrotarget.extropos.services.HealthApi healthApi = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AppwriteService";
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.services.AppwriteService.Companion Companion = null;
    
    public AppwriteService() {
        super();
    }
    
    public final void pingAppwrite(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.services.AppwriteService.PingCallback callback) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/extrotarget/extropos/services/AppwriteService$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/extrotarget/extropos/services/AppwriteService$PingCallback;", "", "onFailure", "", "error", "", "onSuccess", "message", "", "app_debug"})
    public static abstract interface PingCallback {
        
        public abstract void onSuccess(@org.jetbrains.annotations.NotNull()
        java.lang.String message);
        
        public abstract void onFailure(@org.jetbrains.annotations.NotNull()
        java.lang.Throwable error);
    }
}
package com.extrotarget.extropos.ui.guide;

/**
 * Manages contextual hints and overlays for Guide Mode.
 * Shows helpful tooltips and guidance when Guide Mode is enabled.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001%B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nJ\u0006\u0010\u0012\u001a\u00020\u0010J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0007J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J(\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J(\u0010$\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/extrotarget/extropos/ui/guide/GuideOverlayManager;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "prefs", "Lcom/extrotarget/extropos/ui/main/DashboardPrefs;", "(Lcom/extrotarget/extropos/ui/main/DashboardPrefs;)V", "activeOverlays", "", "", "Landroid/view/View;", "currentActivity", "Landroid/app/Activity;", "isGuideEnabled", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "attachToActivity", "", "activity", "clearAllOverlays", "createHintView", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "message", "position", "Lcom/extrotarget/extropos/ui/guide/GuideOverlayManager$HintPosition;", "dismissHint", "hintId", "onDestroy", "owner", "Landroidx/lifecycle/LifecycleOwner;", "positionHintView", "hintView", "targetView", "rootView", "Landroid/view/ViewGroup;", "showHint", "HintPosition", "app_debug"})
public final class GuideOverlayManager implements androidx.lifecycle.DefaultLifecycleObserver {
    @org.jetbrains.annotations.NotNull()
    private final com.extrotarget.extropos.ui.main.DashboardPrefs prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private boolean isGuideEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, android.view.View> activeOverlays = null;
    @org.jetbrains.annotations.Nullable()
    private android.app.Activity currentActivity;
    
    @javax.inject.Inject()
    public GuideOverlayManager(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.ui.main.DashboardPrefs prefs) {
        super();
    }
    
    public final void attachToActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onDestroy(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner owner) {
    }
    
    /**
     * Show a contextual hint for a specific view element.
     */
    public final void showHint(@org.jetbrains.annotations.NotNull()
    android.view.View targetView, @org.jetbrains.annotations.NotNull()
    java.lang.String hintId, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.ui.guide.GuideOverlayManager.HintPosition position) {
    }
    
    /**
     * Dismiss a specific hint by ID.
     */
    public final void dismissHint(@org.jetbrains.annotations.NotNull()
    java.lang.String hintId) {
    }
    
    /**
     * Clear all active overlays.
     */
    public final void clearAllOverlays() {
    }
    
    private final android.widget.TextView createHintView(android.content.Context context, java.lang.String message, com.extrotarget.extropos.ui.guide.GuideOverlayManager.HintPosition position) {
        return null;
    }
    
    private final void positionHintView(android.widget.TextView hintView, android.view.View targetView, android.view.ViewGroup rootView, com.extrotarget.extropos.ui.guide.GuideOverlayManager.HintPosition position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/extrotarget/extropos/ui/guide/GuideOverlayManager$HintPosition;", "", "(Ljava/lang/String;I)V", "TOP", "BOTTOM", "LEFT", "RIGHT", "app_debug"})
    public static enum HintPosition {
        /*public static final*/ TOP /* = new TOP() */,
        /*public static final*/ BOTTOM /* = new BOTTOM() */,
        /*public static final*/ LEFT /* = new LEFT() */,
        /*public static final*/ RIGHT /* = new RIGHT() */;
        
        HintPosition() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.extrotarget.extropos.ui.guide.GuideOverlayManager.HintPosition> getEntries() {
            return null;
        }
    }
}
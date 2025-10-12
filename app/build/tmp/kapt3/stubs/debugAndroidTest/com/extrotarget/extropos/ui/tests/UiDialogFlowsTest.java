package com.extrotarget.extropos.ui.tests;

@dagger.hilt.android.testing.HiltAndroidTest()
@org.junit.runner.RunWith(value = androidx.test.ext.junit.runners.AndroidJUnit4.class)
@androidx.test.filters.LargeTest()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u0010H\u0007J\b\u0010\u0012\u001a\u00020\u0010H\u0007J\b\u0010\u0013\u001a\u00020\u0010H\u0007R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u00020\n8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/extrotarget/extropos/ui/tests/UiDialogFlowsTest;", "Lcom/extrotarget/extropos/ui/tests/BaseUiTest;", "()V", "db", "Lcom/extrotarget/extropos/data/local/AppDatabase;", "getDb", "()Lcom/extrotarget/extropos/data/local/AppDatabase;", "setDb", "(Lcom/extrotarget/extropos/data/local/AppDatabase;)V", "hiltRule", "Ldagger/hilt/android/testing/HiltAndroidRule;", "getHiltRule", "()Ldagger/hilt/android/testing/HiltAndroidRule;", "seeder", "Lcom/extrotarget/extropos/ui/tests/TestDataSeeder;", "addCategory_dialogAdds_thenRecyclerShowsItem", "", "addProduct_dialogAdds_thenRecyclerShowsItem", "seedCategoryAndProduct_thenRecyclerShowsItems", "setup", "app_debugAndroidTest"})
public final class UiDialogFlowsTest extends com.extrotarget.extropos.ui.tests.BaseUiTest {
    @org.jetbrains.annotations.NotNull()
    private final dagger.hilt.android.testing.HiltAndroidRule hiltRule = null;
    @javax.inject.Inject()
    public com.extrotarget.extropos.data.local.AppDatabase db;
    private com.extrotarget.extropos.ui.tests.TestDataSeeder seeder;
    
    public UiDialogFlowsTest() {
        super();
    }
    
    @org.junit.Rule()
    @org.jetbrains.annotations.NotNull()
    public final dagger.hilt.android.testing.HiltAndroidRule getHiltRule() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.AppDatabase getDb() {
        return null;
    }
    
    public final void setDb(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.AppDatabase p0) {
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    @org.junit.Test()
    public final void seedCategoryAndProduct_thenRecyclerShowsItems() {
    }
    
    @org.junit.Test()
    public final void addCategory_dialogAdds_thenRecyclerShowsItem() {
    }
    
    @org.junit.Test()
    public final void addProduct_dialogAdds_thenRecyclerShowsItem() {
    }
}
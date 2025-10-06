package com.extrotarget.extropos.databinding;
import com.extrotarget.extropos.R;
import com.extrotarget.extropos.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMainBindingImpl extends FragmentMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.btn_cash_register, 2);
        sViewsWithIds.put(R.id.btn_table, 3);
        sViewsWithIds.put(R.id.btn_report, 4);
        sViewsWithIds.put(R.id.btn_customers, 5);
        sViewsWithIds.put(R.id.btn_settings, 6);
        sViewsWithIds.put(R.id.headerBar, 7);
        sViewsWithIds.put(R.id.accountText, 8);
        sViewsWithIds.put(R.id.loggedInRoleText, 9);
        sViewsWithIds.put(R.id.appTitle, 10);
        sViewsWithIds.put(R.id.modeSwitches, 11);
        sViewsWithIds.put(R.id.switchGuide, 12);
        sViewsWithIds.put(R.id.switchTraining, 13);
        sViewsWithIds.put(R.id.fabLogout, 14);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private FragmentMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[10]
            , (bindings[2] != null) ? com.extrotarget.extropos.databinding.ItemDashboardButtonBinding.bind((android.view.View) bindings[2]) : null
            , (bindings[5] != null) ? com.extrotarget.extropos.databinding.ItemDashboardButtonBinding.bind((android.view.View) bindings[5]) : null
            , (bindings[4] != null) ? com.extrotarget.extropos.databinding.ItemDashboardButtonBinding.bind((android.view.View) bindings[4]) : null
            , (bindings[6] != null) ? com.extrotarget.extropos.databinding.ItemDashboardButtonBinding.bind((android.view.View) bindings[6]) : null
            , (bindings[3] != null) ? com.extrotarget.extropos.databinding.ItemDashboardButtonBinding.bind((android.view.View) bindings[3]) : null
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[14]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (androidx.gridlayout.widget.GridLayout) bindings[1]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.Switch) bindings[12]
            , (android.widget.Switch) bindings[13]
            );
        this.mainButtonGrid.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}
package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class HomeScreenFragBindingImpl extends HomeScreenFragBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(9);
        sIncludes.setIncludes(1, 
            new String[] {"feature_details_chrome"},
            new int[] {2},
            new int[] {com.google.android.gnd.R.layout.feature_details_chrome});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.map_container_fragment, 3);
        sViewsWithIds.put(R.id.map_scrim, 4);
        sViewsWithIds.put(R.id.bottom_sheet_layout, 5);
        sViewsWithIds.put(R.id.feature_details_frag, 6);
        sViewsWithIds.put(R.id.nav_view, 7);
        sViewsWithIds.put(R.id.version_text, 8);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public HomeScreenFragBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private HomeScreenFragBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.FrameLayout) bindings[5]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[1]
            , (androidx.drawerlayout.widget.DrawerLayout) bindings[0]
            , (com.google.android.gnd.databinding.FeatureDetailsChromeBinding) bindings[2]
            , (androidx.fragment.app.FragmentContainerView) bindings[6]
            , (android.widget.FrameLayout) bindings[3]
            , (android.widget.FrameLayout) bindings[4]
            , (com.google.android.material.navigation.NavigationView) bindings[7]
            , (android.widget.TextView) bindings[8]
            );
        this.coordinatorLayout.setTag(null);
        this.drawerLayout.setTag(null);
        setContainedBinding(this.featureDetailsChrome);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        featureDetailsChrome.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (featureDetailsChrome.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        featureDetailsChrome.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeFeatureDetailsChrome((com.google.android.gnd.databinding.FeatureDetailsChromeBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeFeatureDetailsChrome(com.google.android.gnd.databinding.FeatureDetailsChromeBinding FeatureDetailsChrome, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        executeBindingsOn(featureDetailsChrome);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): featureDetailsChrome
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}
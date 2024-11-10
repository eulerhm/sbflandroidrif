package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FeatureDetailsFragBindingImpl extends FeatureDetailsFragBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(4);
        sIncludes.setIncludes(1, 
            new String[] {"feature_details_header"},
            new int[] {2},
            new int[] {com.google.android.gnd.R.layout.feature_details_header});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.submission_list_frag, 3);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @Nullable
    private final com.google.android.gnd.databinding.FeatureDetailsHeaderBinding mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FeatureDetailsFragBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private FeatureDetailsFragBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[1]
            , (androidx.fragment.app.FragmentContainerView) bindings[3]
            );
        this.featureDetailsHeaderLayout.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (com.google.android.gnd.databinding.FeatureDetailsHeaderBinding) bindings[2];
        setContainedBinding(this.mboundView1);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        mboundView1.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView1.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.fragment == variableId) {
            setFragment((com.google.android.gnd.ui.home.featuredetails.FeatureDetailsFragment) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.google.android.gnd.ui.home.featuredetails.FeatureDetailsViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFragment(@Nullable com.google.android.gnd.ui.home.featuredetails.FeatureDetailsFragment Fragment) {
        this.mFragment = Fragment;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.google.android.gnd.ui.home.featuredetails.FeatureDetailsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        mboundView1.setLifecycleOwner(lifecycleOwner);
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
        com.google.android.gnd.ui.home.featuredetails.FeatureDetailsFragment fragment = mFragment;
        com.google.android.gnd.ui.home.featuredetails.FeatureDetailsViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x5L) != 0) {
        }
        if ((dirtyFlags & 0x6L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            this.mboundView1.setFragment(fragment);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.mboundView1.setViewModel(viewModel);
        }
        executeBindingsOn(mboundView1);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): fragment
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}
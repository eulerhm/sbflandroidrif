package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class OfflineBaseMapsFragBindingImpl extends OfflineBaseMapsFragBinding implements com.google.android.gnd.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.offline_areas_toolbar, 3);
        sViewsWithIds.put(R.id.offline_areas_list, 4);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public OfflineBaseMapsFragBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private OfflineBaseMapsFragBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.chip.Chip) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[4]
            , (com.google.android.gnd.ui.common.TwoLineToolbar) bindings[3]
            );
        this.floatingActionButton.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.noAreasDownloadedMessage.setTag(null);
        setRootTag(root);
        // listeners
        mCallback5 = new com.google.android.gnd.generated.callback.OnClickListener(this, 2);
        mCallback4 = new com.google.android.gnd.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.viewModel == variableId) {
            setViewModel((com.google.android.gnd.ui.offlinebasemap.OfflineAreasViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.google.android.gnd.ui.offlinebasemap.OfflineAreasViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelNoAreasMessageVisibility((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelNoAreasMessageVisibility(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelNoAreasMessageVisibility, int fieldId) {
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
        int androidxDatabindingViewDataBindingSafeUnboxViewModelNoAreasMessageVisibilityGetValue = 0;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelNoAreasMessageVisibility = null;
        com.google.android.gnd.ui.offlinebasemap.OfflineAreasViewModel viewModel = mViewModel;
        java.lang.Integer viewModelNoAreasMessageVisibilityGetValue = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.noAreasMessageVisibility
                    viewModelNoAreasMessageVisibility = viewModel.getNoAreasMessageVisibility();
                }
                updateLiveDataRegistration(0, viewModelNoAreasMessageVisibility);


                if (viewModelNoAreasMessageVisibility != null) {
                    // read viewModel.noAreasMessageVisibility.getValue()
                    viewModelNoAreasMessageVisibilityGetValue = viewModelNoAreasMessageVisibility.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.noAreasMessageVisibility.getValue())
                androidxDatabindingViewDataBindingSafeUnboxViewModelNoAreasMessageVisibilityGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelNoAreasMessageVisibilityGetValue);
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.floatingActionButton.setOnClickListener(mCallback4);
            this.noAreasDownloadedMessage.setOnClickListener(mCallback5);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.noAreasDownloadedMessage.setVisibility(androidxDatabindingViewDataBindingSafeUnboxViewModelNoAreasMessageVisibilityGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // viewModel
                com.google.android.gnd.ui.offlinebasemap.OfflineAreasViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.showOfflineAreaSelector();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // viewModel
                com.google.android.gnd.ui.offlinebasemap.OfflineAreasViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.showOfflineAreaSelector();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.noAreasMessageVisibility
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}
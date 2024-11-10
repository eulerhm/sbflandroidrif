package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FeatureDetailsChromeBindingImpl extends FeatureDetailsChromeBinding implements com.google.android.gnd.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar_wrapper, 2);
        sViewsWithIds.put(R.id.toolbar, 3);
        sViewsWithIds.put(R.id.bottom_sheet_bottom_inset_scrim, 4);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback9;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FeatureDetailsChromeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private FeatureDetailsChromeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.chip.Chip) bindings[1]
            , (android.widget.FrameLayout) bindings[4]
            , (android.widget.RelativeLayout) bindings[0]
            , (com.google.android.gnd.ui.common.TwoLineToolbar) bindings[3]
            , (android.widget.FrameLayout) bindings[2]
            );
        this.addSubmissionBtn.setTag(null);
        this.bottomSheetChrome.setTag(null);
        setRootTag(root);
        // listeners
        mCallback9 = new com.google.android.gnd.generated.callback.OnClickListener(this, 1);
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
            setViewModel((com.google.android.gnd.ui.home.HomeScreenViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.google.android.gnd.ui.home.HomeScreenViewModel ViewModel) {
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
                return onChangeViewModelIsSubmissionButtonVisible((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelIsSubmissionButtonVisible(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ViewModelIsSubmissionButtonVisible, int fieldId) {
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
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> viewModelIsSubmissionButtonVisible = null;
        java.lang.Boolean viewModelIsSubmissionButtonVisibleGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsSubmissionButtonVisibleGetValue = false;
        int viewModelIsSubmissionButtonVisibleViewVISIBLEViewGONE = 0;
        com.google.android.gnd.ui.home.HomeScreenViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.isSubmissionButtonVisible
                    viewModelIsSubmissionButtonVisible = viewModel.isSubmissionButtonVisible;
                }
                updateLiveDataRegistration(0, viewModelIsSubmissionButtonVisible);


                if (viewModelIsSubmissionButtonVisible != null) {
                    // read viewModel.isSubmissionButtonVisible.getValue()
                    viewModelIsSubmissionButtonVisibleGetValue = viewModelIsSubmissionButtonVisible.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSubmissionButtonVisible.getValue())
                androidxDatabindingViewDataBindingSafeUnboxViewModelIsSubmissionButtonVisibleGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsSubmissionButtonVisibleGetValue);
            if((dirtyFlags & 0x7L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsSubmissionButtonVisibleGetValue) {
                        dirtyFlags |= 0x10L;
                }
                else {
                        dirtyFlags |= 0x8L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSubmissionButtonVisible.getValue()) ? View.VISIBLE : View.GONE
                viewModelIsSubmissionButtonVisibleViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsSubmissionButtonVisibleGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.addSubmissionBtn.setOnClickListener(mCallback9);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.addSubmissionBtn.setVisibility(viewModelIsSubmissionButtonVisibleViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.google.android.gnd.ui.home.HomeScreenViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {


            viewModel.addSubmission();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.isSubmissionButtonVisible
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
        flag 3 (0x4L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSubmissionButtonVisible.getValue()) ? View.VISIBLE : View.GONE
        flag 4 (0x5L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSubmissionButtonVisible.getValue()) ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}
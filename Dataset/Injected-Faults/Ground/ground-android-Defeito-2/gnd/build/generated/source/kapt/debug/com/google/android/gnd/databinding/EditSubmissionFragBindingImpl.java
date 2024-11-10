package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class EditSubmissionFragBindingImpl extends EditSubmissionFragBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.edit_submission_header, 5);
        sViewsWithIds.put(R.id.edit_submission_layout, 6);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final androidx.cardview.widget.CardView mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public EditSubmissionFragBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private EditSubmissionFragBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (androidx.cardview.widget.CardView) bindings[5]
            , (android.widget.LinearLayout) bindings[6]
            , (com.google.android.gnd.ui.common.TwoLineToolbar) bindings[1]
            , (android.widget.ProgressBar) bindings[2]
            , (com.google.android.material.chip.Chip) bindings[3]
            );
        this.editSubmissionToolbar.setTag(null);
        this.loadingSpinner.setTag(null);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView4 = (androidx.cardview.widget.CardView) bindings[4];
        this.mboundView4.setTag(null);
        this.saveSubmissionBtn.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
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
        if (BR.fragment == variableId) {
            setFragment((com.google.android.gnd.ui.editsubmission.EditSubmissionFragment) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFragment(@Nullable com.google.android.gnd.ui.editsubmission.EditSubmissionFragment Fragment) {
        this.mFragment = Fragment;
    }
    public void setViewModel(@Nullable com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelToolbarTitle((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelIsSaving((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeViewModelIsLoading((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelToolbarTitle(androidx.lifecycle.LiveData<java.lang.String> ViewModelToolbarTitle, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsSaving(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ViewModelIsSaving, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsLoading(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
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
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsSavingGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue = false;
        androidx.lifecycle.LiveData<java.lang.String> viewModelToolbarTitle = null;
        int viewModelIsLoadingViewVISIBLEViewGONE = 0;
        java.lang.Boolean viewModelIsLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> viewModelIsSaving = null;
        int viewModelIsSavingViewGONEViewVISIBLE = 0;
        java.lang.Boolean viewModelIsSavingGetValue = null;
        java.lang.String viewModelToolbarTitleGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> viewModelIsLoading = null;
        com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel viewModel = mViewModel;
        int viewModelIsSavingViewVISIBLEViewGONE = 0;

        if ((dirtyFlags & 0x37L) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.toolbarTitle
                        viewModelToolbarTitle = viewModel.getToolbarTitle();
                    }
                    updateLiveDataRegistration(0, viewModelToolbarTitle);


                    if (viewModelToolbarTitle != null) {
                        // read viewModel.toolbarTitle.getValue()
                        viewModelToolbarTitleGetValue = viewModelToolbarTitle.getValue();
                    }
            }
            if ((dirtyFlags & 0x32L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isSaving
                        viewModelIsSaving = viewModel.isSaving;
                    }
                    updateLiveDataRegistration(1, viewModelIsSaving);


                    if (viewModelIsSaving != null) {
                        // read viewModel.isSaving.getValue()
                        viewModelIsSavingGetValue = viewModelIsSaving.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSaving.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsSavingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsSavingGetValue);
                if((dirtyFlags & 0x32L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsSavingGetValue) {
                            dirtyFlags |= 0x200L;
                            dirtyFlags |= 0x800L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                            dirtyFlags |= 0x400L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSaving.getValue()) ? View.GONE : View.VISIBLE
                    viewModelIsSavingViewGONEViewVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsSavingGetValue) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSaving.getValue()) ? View.VISIBLE : View.GONE
                    viewModelIsSavingViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsSavingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x34L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isLoading
                        viewModelIsLoading = viewModel.isLoading;
                    }
                    updateLiveDataRegistration(2, viewModelIsLoading);


                    if (viewModelIsLoading != null) {
                        // read viewModel.isLoading.getValue()
                        viewModelIsLoadingGetValue = viewModelIsLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsLoadingGetValue);
                if((dirtyFlags & 0x34L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? View.VISIBLE : View.GONE
                    viewModelIsLoadingViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            this.editSubmissionToolbar.setTitle(viewModelToolbarTitleGetValue);
        }
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            this.loadingSpinner.setVisibility(viewModelIsLoadingViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            this.mboundView4.setVisibility(viewModelIsSavingViewVISIBLEViewGONE);
            this.saveSubmissionBtn.setVisibility(viewModelIsSavingViewGONEViewVISIBLE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.toolbarTitle
        flag 1 (0x2L): viewModel.isSaving
        flag 2 (0x3L): viewModel.isLoading
        flag 3 (0x4L): fragment
        flag 4 (0x5L): viewModel
        flag 5 (0x6L): null
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? View.VISIBLE : View.GONE
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? View.VISIBLE : View.GONE
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSaving.getValue()) ? View.GONE : View.VISIBLE
        flag 9 (0xaL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSaving.getValue()) ? View.GONE : View.VISIBLE
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSaving.getValue()) ? View.VISIBLE : View.GONE
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isSaving.getValue()) ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}
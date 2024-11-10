package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class PhotoInputFieldBindingImpl extends PhotoInputFieldBinding implements com.google.android.gnd.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(5);
        sIncludes.setIncludes(0, 
            new String[] {"photo_field"},
            new int[] {4},
            new int[] {com.google.android.gnd.R.layout.photo_field});
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @Nullable
    private final com.google.android.gnd.databinding.PhotoFieldBinding mboundView01;
    @NonNull
    private final android.widget.TextView mboundView1;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public PhotoInputFieldBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private PhotoInputFieldBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.Button) bindings[2]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[3]
            );
        this.btnSelectPhoto.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView01 = (com.google.android.gnd.databinding.PhotoFieldBinding) bindings[4];
        setContainedBinding(this.mboundView01);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.photoValidationError.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new com.google.android.gnd.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
        }
        mboundView01.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView01.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.editSubmissionViewModel == variableId) {
            setEditSubmissionViewModel((com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setEditSubmissionViewModel(@Nullable com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel EditSubmissionViewModel) {
        this.mEditSubmissionViewModel = EditSubmissionViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.editSubmissionViewModel);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        mboundView01.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelError((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelIsPhotoPresent((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelError(androidx.lifecycle.LiveData<java.lang.String> ViewModelError, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsPhotoPresent(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsPhotoPresent, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        java.lang.String viewModelFieldLabel = null;
        com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel editSubmissionViewModel = mEditSubmissionViewModel;
        int viewModelIsPhotoPresentViewGONEViewVISIBLE = 0;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsPhotoPresentGetValue = false;
        java.lang.String viewModelErrorGetValue = null;
        java.lang.Boolean viewModelIsPhotoPresentGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelError = null;
        com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsPhotoPresent = null;

        if ((dirtyFlags & 0x14L) != 0) {
        }
        if ((dirtyFlags & 0x1bL) != 0) {


            if ((dirtyFlags & 0x18L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.fieldLabel()
                        viewModelFieldLabel = viewModel.fieldLabel();
                    }
            }
            if ((dirtyFlags & 0x19L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.error
                        viewModelError = viewModel.getError();
                    }
                    updateLiveDataRegistration(0, viewModelError);


                    if (viewModelError != null) {
                        // read viewModel.error.getValue()
                        viewModelErrorGetValue = viewModelError.getValue();
                    }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isPhotoPresent()
                        viewModelIsPhotoPresent = viewModel.isPhotoPresent();
                    }
                    updateLiveDataRegistration(1, viewModelIsPhotoPresent);


                    if (viewModelIsPhotoPresent != null) {
                        // read viewModel.isPhotoPresent().getValue()
                        viewModelIsPhotoPresentGetValue = viewModelIsPhotoPresent.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPhotoPresent().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsPhotoPresentGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsPhotoPresentGetValue);
                if((dirtyFlags & 0x1aL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsPhotoPresentGetValue) {
                            dirtyFlags |= 0x40L;
                    }
                    else {
                            dirtyFlags |= 0x20L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPhotoPresent().getValue()) ? View.GONE : View.VISIBLE
                    viewModelIsPhotoPresentViewGONEViewVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsPhotoPresentGetValue) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            this.btnSelectPhoto.setOnClickListener(mCallback2);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            this.btnSelectPhoto.setVisibility(viewModelIsPhotoPresentViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 0x18L) != 0) {
            // api target 1

            this.mboundView01.setViewModel(viewModel);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, viewModelFieldLabel);
        }
        if ((dirtyFlags & 0x14L) != 0) {
            // api target 1

            this.mboundView01.setEditSubmissionViewModel(editSubmissionViewModel);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.photoValidationError.setError(viewModelErrorGetValue);
        }
        executeBindingsOn(mboundView01);
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {


            viewModel.onShowPhotoSelectorDialog();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.error
        flag 1 (0x2L): viewModel.isPhotoPresent()
        flag 2 (0x3L): editSubmissionViewModel
        flag 3 (0x4L): viewModel
        flag 4 (0x5L): null
        flag 5 (0x6L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPhotoPresent().getValue()) ? View.GONE : View.VISIBLE
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPhotoPresent().getValue()) ? View.GONE : View.VISIBLE
    flag mapping end*/
    //end
}
package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class PhotoFieldBindingImpl extends PhotoFieldBinding implements com.google.android.gnd.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    @NonNull
    private final android.widget.ImageButton mboundView2;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public PhotoFieldBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private PhotoFieldBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.ImageView) bindings[1]
            );
        this.imageThumbnailPreview.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.ImageButton) bindings[2];
        this.mboundView2.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.google.android.gnd.generated.callback.OnClickListener(this, 1);
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
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.editSubmissionViewModel);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel ViewModel) {
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
                return onChangeViewModelUri((androidx.lifecycle.LiveData<android.net.Uri>) object, fieldId);
            case 1 :
                return onChangeViewModelIsEditable((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeViewModelIsPhotoPresent((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelUri(androidx.lifecycle.LiveData<android.net.Uri> ViewModelUri, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsEditable(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsEditable, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsPhotoPresent(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsPhotoPresent, int fieldId) {
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
        int viewModelIsPhotoPresentViewVISIBLEViewGONE = 0;
        com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel editSubmissionViewModel = mEditSubmissionViewModel;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditableGetValue = false;
        androidx.lifecycle.LiveData<android.net.Uri> viewModelUri = null;
        android.net.Uri viewModelUriGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsPhotoPresentGetValue = false;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsEditable = null;
        int viewModelIsEditableViewVISIBLEViewGONE = 0;
        java.lang.Boolean viewModelIsPhotoPresentGetValue = null;
        java.lang.Boolean viewModelIsEditableGetValue = null;
        com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsPhotoPresent = null;

        if ((dirtyFlags & 0x37L) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.uri
                        viewModelUri = viewModel.getUri();
                    }
                    updateLiveDataRegistration(0, viewModelUri);


                    if (viewModelUri != null) {
                        // read viewModel.uri.getValue()
                        viewModelUriGetValue = viewModelUri.getValue();
                    }
            }
            if ((dirtyFlags & 0x32L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isEditable()
                        viewModelIsEditable = viewModel.isEditable();
                    }
                    updateLiveDataRegistration(1, viewModelIsEditable);


                    if (viewModelIsEditable != null) {
                        // read viewModel.isEditable().getValue()
                        viewModelIsEditableGetValue = viewModelIsEditable.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditable().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditableGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsEditableGetValue);
                if((dirtyFlags & 0x32L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditableGetValue) {
                            dirtyFlags |= 0x200L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditable().getValue()) ? View.VISIBLE : View.GONE
                    viewModelIsEditableViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsEditableGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x34L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isPhotoPresent()
                        viewModelIsPhotoPresent = viewModel.isPhotoPresent();
                    }
                    updateLiveDataRegistration(2, viewModelIsPhotoPresent);


                    if (viewModelIsPhotoPresent != null) {
                        // read viewModel.isPhotoPresent().getValue()
                        viewModelIsPhotoPresentGetValue = viewModelIsPhotoPresent.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPhotoPresent().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsPhotoPresentGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsPhotoPresentGetValue);
                if((dirtyFlags & 0x34L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsPhotoPresentGetValue) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPhotoPresent().getValue()) ? View.VISIBLE : View.GONE
                    viewModelIsPhotoPresentViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsPhotoPresentGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            com.google.android.gnd.ui.common.BindingAdapters.bindUri(this.imageThumbnailPreview, viewModelUriGetValue);
        }
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            this.mboundView0.setVisibility(viewModelIsPhotoPresentViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x20L) != 0) {
            // api target 1

            this.mboundView2.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            this.mboundView2.setVisibility(viewModelIsEditableViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel.getField()
        com.google.android.gnd.model.task.Field viewModelGetField = null;
        // editSubmissionViewModel
        com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel editSubmissionViewModel = mEditSubmissionViewModel;
        // viewModel.getField() != null
        boolean viewModelGetFieldJavaLangObjectNull = false;
        // viewModel
        com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel viewModel = mViewModel;
        // editSubmissionViewModel != null
        boolean editSubmissionViewModelJavaLangObjectNull = false;
        // viewModel.getField().getId()
        java.lang.String viewModelGetFieldGetId = null;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        editSubmissionViewModelJavaLangObjectNull = (editSubmissionViewModel) != (null);
        if (editSubmissionViewModelJavaLangObjectNull) {



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelGetField = viewModel.getField();

                viewModelGetFieldJavaLangObjectNull = (viewModelGetField) != (null);
                if (viewModelGetFieldJavaLangObjectNull) {


                    viewModelGetFieldGetId = viewModelGetField.getId();

                    editSubmissionViewModel.clearPhoto(viewModelGetFieldGetId);
                }
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.uri
        flag 1 (0x2L): viewModel.isEditable()
        flag 2 (0x3L): viewModel.isPhotoPresent()
        flag 3 (0x4L): editSubmissionViewModel
        flag 4 (0x5L): viewModel
        flag 5 (0x6L): null
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPhotoPresent().getValue()) ? View.VISIBLE : View.GONE
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isPhotoPresent().getValue()) ? View.VISIBLE : View.GONE
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditable().getValue()) ? View.VISIBLE : View.GONE
        flag 9 (0xaL): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isEditable().getValue()) ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}
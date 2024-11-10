package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class MultipleChoiceInputFieldBindingImpl extends MultipleChoiceInputFieldBinding implements com.google.android.gnd.generated.callback.Runnable.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.multiple_choice_input_layout, 4);
        sViewsWithIds.put(R.id.multiple_choice_dropdown_btn, 5);
        sViewsWithIds.put(R.id.multiple_choice_tap_target, 6);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldLayout mboundView2;
    // variables
    @Nullable
    private final java.lang.Runnable mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public MultipleChoiceInputFieldBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private MultipleChoiceInputFieldBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.ImageButton) bindings[5]
            , (com.google.android.material.textfield.TextInputEditText) bindings[3]
            , (com.google.android.material.textfield.TextInputLayout) bindings[4]
            , (android.view.View) bindings[6]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.multipleChoiceInputEditText.setTag(null);
        setRootTag(root);
        // listeners
        mCallback3 = new com.google.android.gnd.generated.callback.Runnable(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
            setViewModel((com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelResponseText((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelError((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelResponseText(androidx.lifecycle.LiveData<java.lang.String> ViewModelResponseText, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelError(androidx.lifecycle.LiveData<java.lang.String> ViewModelError, int fieldId) {
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
        androidx.lifecycle.LiveData<java.lang.String> viewModelResponseText = null;
        java.lang.String viewModelFieldLabel = null;
        java.lang.String viewModelErrorGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelError = null;
        com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldViewModel viewModel = mViewModel;
        java.lang.String viewModelResponseTextGetValue = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.responseText
                        viewModelResponseText = viewModel.getResponseText();
                    }
                    updateLiveDataRegistration(0, viewModelResponseText);


                    if (viewModelResponseText != null) {
                        // read viewModel.responseText.getValue()
                        viewModelResponseTextGetValue = viewModelResponseText.getValue();
                    }
            }
            if ((dirtyFlags & 0xcL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.fieldLabel()
                        viewModelFieldLabel = viewModel.fieldLabel();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.error
                        viewModelError = viewModel.getError();
                    }
                    updateLiveDataRegistration(1, viewModelError);


                    if (viewModelError != null) {
                        // read viewModel.error.getValue()
                        viewModelErrorGetValue = viewModelError.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xcL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, viewModelFieldLabel);
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            com.google.android.gnd.ui.common.BindingAdapters.setOnShowDialogListener(this.mboundView2, mCallback3);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.multipleChoiceInputEditText, viewModelResponseTextGetValue);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            this.multipleChoiceInputEditText.setError(viewModelErrorGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackRun(int sourceId ) {
        // localize variables for thread safety
        // viewModel
        com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {


            viewModel.onShowDialog();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.responseText
        flag 1 (0x2L): viewModel.error
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}
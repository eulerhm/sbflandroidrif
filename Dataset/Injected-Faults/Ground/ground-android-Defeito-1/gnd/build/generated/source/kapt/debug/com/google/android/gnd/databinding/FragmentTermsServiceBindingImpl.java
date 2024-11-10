package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentTermsServiceBindingImpl extends FragmentTermsServiceBinding implements com.google.android.gnd.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.terms_header, 4);
        sViewsWithIds.put(R.id.terms_toolbar, 5);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback6;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener agreeCheckBoxandroidCheckedAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.agreeCheckboxChecked.getValue()
            //         is viewModel.agreeCheckboxChecked.setValue((java.lang.Boolean) callbackArg_0)
            boolean callbackArg_0 = agreeCheckBox.isChecked();
            // localize variables for thread safety
            // viewModel.agreeCheckboxChecked != null
            boolean viewModelAgreeCheckboxCheckedJavaLangObjectNull = false;
            // viewModel.agreeCheckboxChecked.getValue()
            java.lang.Boolean viewModelAgreeCheckboxCheckedGetValue = null;
            // viewModel.agreeCheckboxChecked
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> viewModelAgreeCheckboxChecked = null;
            // viewModel
            com.google.android.gnd.ui.tos.TermsOfServiceViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelAgreeCheckboxChecked = viewModel.agreeCheckboxChecked;

                viewModelAgreeCheckboxCheckedJavaLangObjectNull = (viewModelAgreeCheckboxChecked) != (null);
                if (viewModelAgreeCheckboxCheckedJavaLangObjectNull) {




                    viewModelAgreeCheckboxChecked.setValue(((java.lang.Boolean) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentTermsServiceBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private FragmentTermsServiceBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[2]
            , (android.widget.CheckBox) bindings[3]
            , (androidx.cardview.widget.CardView) bindings[4]
            , (android.widget.TextView) bindings[1]
            , (com.google.android.gnd.ui.common.TwoLineToolbar) bindings[5]
            );
        this.agreeButton.setTag(null);
        this.agreeCheckBox.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.termsText.setTag(null);
        setRootTag(root);
        // listeners
        mCallback6 = new com.google.android.gnd.generated.callback.OnClickListener(this, 1);
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
            setViewModel((com.google.android.gnd.ui.tos.TermsOfServiceViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.google.android.gnd.ui.tos.TermsOfServiceViewModel ViewModel) {
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
                return onChangeViewModelAgreeCheckboxChecked((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelAgreeCheckboxChecked(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ViewModelAgreeCheckboxChecked, int fieldId) {
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
        java.lang.Boolean viewModelAgreeCheckboxCheckedGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelAgreeCheckboxCheckedGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> viewModelAgreeCheckboxChecked = null;
        java.lang.String viewModelTermsOfServiceText = null;
        com.google.android.gnd.ui.tos.TermsOfServiceViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.agreeCheckboxChecked
                    viewModelAgreeCheckboxChecked = viewModel.agreeCheckboxChecked;
                }
                updateLiveDataRegistration(0, viewModelAgreeCheckboxChecked);


                if (viewModelAgreeCheckboxChecked != null) {
                    // read viewModel.agreeCheckboxChecked.getValue()
                    viewModelAgreeCheckboxCheckedGetValue = viewModelAgreeCheckboxChecked.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.agreeCheckboxChecked.getValue())
                androidxDatabindingViewDataBindingSafeUnboxViewModelAgreeCheckboxCheckedGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelAgreeCheckboxCheckedGetValue);
            if ((dirtyFlags & 0x6L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.termsOfServiceText
                        viewModelTermsOfServiceText = viewModel.getTermsOfServiceText();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.agreeButton.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelAgreeCheckboxCheckedGetValue);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.agreeCheckBox, androidxDatabindingViewDataBindingSafeUnboxViewModelAgreeCheckboxCheckedGetValue);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.agreeButton.setOnClickListener(mCallback6);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(this.agreeCheckBox, (android.widget.CompoundButton.OnCheckedChangeListener)null, agreeCheckBoxandroidCheckedAttrChanged);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.termsText, viewModelTermsOfServiceText);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.google.android.gnd.ui.tos.TermsOfServiceViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {


            viewModel.onButtonClicked();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.agreeCheckboxChecked
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}
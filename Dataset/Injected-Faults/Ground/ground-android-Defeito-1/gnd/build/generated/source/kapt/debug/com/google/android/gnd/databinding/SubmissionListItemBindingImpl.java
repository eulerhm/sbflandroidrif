package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SubmissionListItemBindingImpl extends SubmissionListItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.separator_dot1, 5);
        sViewsWithIds.put(R.id.separator_dot2, 6);
        sViewsWithIds.put(R.id.field_label_row, 7);
        sViewsWithIds.put(R.id.field_value_row, 8);
        sViewsWithIds.put(R.id.submission_item_divider, 9);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mViewModelOnClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public SubmissionListItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private SubmissionListItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.TableRow) bindings[7]
            , (android.widget.TableRow) bindings[8]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.ImageButton) bindings[4]
            , (android.view.View) bindings[9]
            , (android.widget.TextView) bindings[1]
            );
        this.lastModifiedDate.setTag(null);
        this.lastModifiedTime.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.submissionDetailsBtn.setTag(null);
        this.userName.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
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
            setViewModel((com.google.android.gnd.ui.home.featuredetails.SubmissionListItemViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.google.android.gnd.ui.home.featuredetails.SubmissionListItemViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelUserName((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelModifiedTime((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelModifiedDate((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelUserName(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelUserName, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelModifiedTime(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelModifiedTime, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelModifiedDate(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelModifiedDate, int fieldId) {
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
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelUserName = null;
        android.view.View.OnClickListener viewModelOnClickAndroidViewViewOnClickListener = null;
        java.lang.String viewModelUserNameGetValue = null;
        java.lang.String viewModelModifiedDateGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelModifiedTime = null;
        com.google.android.gnd.ui.home.featuredetails.SubmissionListItemViewModel viewModel = mViewModel;
        java.lang.String viewModelModifiedTimeGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelModifiedDate = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.userName
                        viewModelUserName = viewModel.userName;
                    }
                    updateLiveDataRegistration(0, viewModelUserName);


                    if (viewModelUserName != null) {
                        // read viewModel.userName.getValue()
                        viewModelUserNameGetValue = viewModelUserName.getValue();
                    }
            }
            if ((dirtyFlags & 0x18L) != 0) {

                    if (viewModel != null) {
                        // read viewModel::onClick
                        viewModelOnClickAndroidViewViewOnClickListener = (((mViewModelOnClickAndroidViewViewOnClickListener == null) ? (mViewModelOnClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mViewModelOnClickAndroidViewViewOnClickListener).setValue(viewModel));
                    }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.modifiedTime
                        viewModelModifiedTime = viewModel.modifiedTime;
                    }
                    updateLiveDataRegistration(1, viewModelModifiedTime);


                    if (viewModelModifiedTime != null) {
                        // read viewModel.modifiedTime.getValue()
                        viewModelModifiedTimeGetValue = viewModelModifiedTime.getValue();
                    }
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.modifiedDate
                        viewModelModifiedDate = viewModel.modifiedDate;
                    }
                    updateLiveDataRegistration(2, viewModelModifiedDate);


                    if (viewModelModifiedDate != null) {
                        // read viewModel.modifiedDate.getValue()
                        viewModelModifiedDateGetValue = viewModelModifiedDate.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.lastModifiedDate, viewModelModifiedDateGetValue);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.lastModifiedTime, viewModelModifiedTimeGetValue);
        }
        if ((dirtyFlags & 0x18L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
            this.submissionDetailsBtn.setOnClickListener(viewModelOnClickAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.userName, viewModelUserNameGetValue);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.google.android.gnd.ui.home.featuredetails.SubmissionListItemViewModel value;
        public OnClickListenerImpl setValue(com.google.android.gnd.ui.home.featuredetails.SubmissionListItemViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onClick(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.userName
        flag 1 (0x2L): viewModel.modifiedTime
        flag 2 (0x3L): viewModel.modifiedDate
        flag 3 (0x4L): viewModel
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}
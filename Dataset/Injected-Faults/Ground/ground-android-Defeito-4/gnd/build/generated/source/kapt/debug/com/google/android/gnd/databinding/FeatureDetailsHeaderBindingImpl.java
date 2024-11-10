package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FeatureDetailsHeaderBindingImpl extends FeatureDetailsHeaderBinding  {

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
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.ImageView mboundView3;
    @NonNull
    private final android.widget.ImageView mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FeatureDetailsHeaderBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private FeatureDetailsHeaderBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.FrameLayout) bindings[0]
            , (android.widget.TextView) bindings[1]
            );
        this.bottomSheetHeader.setTag(null);
        this.featureTitle.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.ImageView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.ImageView) bindings[4];
        this.mboundView4.setTag(null);
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
    }
    public void setViewModel(@Nullable com.google.android.gnd.ui.home.featuredetails.FeatureDetailsViewModel ViewModel) {
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
                return onChangeViewModelIsUploadPendingIconVisible((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeViewModelTitle((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelSubtitle((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelIsUploadPendingIconVisible(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsUploadPendingIconVisible, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelTitle(androidx.lifecycle.LiveData<java.lang.String> ViewModelTitle, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelSubtitle(androidx.lifecycle.LiveData<java.lang.String> ViewModelSubtitle, int fieldId) {
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
        java.lang.Boolean viewModelIsUploadPendingIconVisibleGetValue = null;
        java.lang.String viewModelTitleGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsUploadPendingIconVisible = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelTitle = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelSubtitle = null;
        android.graphics.Bitmap viewModelGetMarkerBitmap = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsUploadPendingIconVisibleGetValue = false;
        com.google.android.gnd.ui.home.featuredetails.FeatureDetailsViewModel viewModel = mViewModel;
        java.lang.String viewModelSubtitleGetValue = null;

        if ((dirtyFlags & 0x37L) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isUploadPendingIconVisible()
                        viewModelIsUploadPendingIconVisible = viewModel.isUploadPendingIconVisible();
                    }
                    updateLiveDataRegistration(0, viewModelIsUploadPendingIconVisible);


                    if (viewModelIsUploadPendingIconVisible != null) {
                        // read viewModel.isUploadPendingIconVisible().getValue()
                        viewModelIsUploadPendingIconVisibleGetValue = viewModelIsUploadPendingIconVisible.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isUploadPendingIconVisible().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsUploadPendingIconVisibleGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsUploadPendingIconVisibleGetValue);
            }
            if ((dirtyFlags & 0x32L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.title
                        viewModelTitle = viewModel.getTitle();
                    }
                    updateLiveDataRegistration(1, viewModelTitle);


                    if (viewModelTitle != null) {
                        // read viewModel.title.getValue()
                        viewModelTitleGetValue = viewModelTitle.getValue();
                    }
            }
            if ((dirtyFlags & 0x34L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.subtitle
                        viewModelSubtitle = viewModel.getSubtitle();
                    }
                    updateLiveDataRegistration(2, viewModelSubtitle);


                    if (viewModelSubtitle != null) {
                        // read viewModel.subtitle.getValue()
                        viewModelSubtitleGetValue = viewModelSubtitle.getValue();
                    }
            }
            if ((dirtyFlags & 0x30L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.getMarkerBitmap()
                        viewModelGetMarkerBitmap = viewModel.getMarkerBitmap();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.featureTitle, viewModelTitleGetValue);
        }
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, viewModelSubtitleGetValue);
        }
        if ((dirtyFlags & 0x30L) != 0) {
            // api target 1

            com.google.android.gnd.ui.common.BindingAdapters.bindImageBitmap(this.mboundView3, viewModelGetMarkerBitmap);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            com.google.android.gnd.ui.common.BindingAdapters.bindVisible(this.mboundView4, androidxDatabindingViewDataBindingSafeUnboxViewModelIsUploadPendingIconVisibleGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.isUploadPendingIconVisible()
        flag 1 (0x2L): viewModel.title
        flag 2 (0x3L): viewModel.subtitle
        flag 3 (0x4L): fragment
        flag 4 (0x5L): viewModel
        flag 5 (0x6L): null
    flag mapping end*/
    //end
}
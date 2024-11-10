package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class MapHomeLayoutBindingImpl extends MapHomeLayoutBinding implements com.google.android.gnd.generated.callback.OnClickListener.Listener {

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
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback16;
    @Nullable
    private final android.view.View.OnClickListener mCallback14;
    @Nullable
    private final android.view.View.OnClickListener mCallback15;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public MapHomeLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private MapHomeLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[3]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[2]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[1]
            );
        this.hamburgerBtn.setTag(null);
        this.locationLockBtn.setTag(null);
        this.mapTypeBtn.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback16 = new com.google.android.gnd.generated.callback.OnClickListener(this, 3);
        mCallback14 = new com.google.android.gnd.generated.callback.OnClickListener(this, 1);
        mCallback15 = new com.google.android.gnd.generated.callback.OnClickListener(this, 2);
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
        if (BR.homeScreenViewModel == variableId) {
            setHomeScreenViewModel((com.google.android.gnd.ui.home.HomeScreenViewModel) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setHomeScreenViewModel(@Nullable com.google.android.gnd.ui.home.HomeScreenViewModel HomeScreenViewModel) {
        this.mHomeScreenViewModel = HomeScreenViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.homeScreenViewModel);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel ViewModel) {
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
                return onChangeViewModelGetIconTint((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 1 :
                return onChangeViewModelGetLocationLockEnabled((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelGetIconTint(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelGetIconTint, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelGetLocationLockEnabled(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelGetLocationLockEnabled, int fieldId) {
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
        java.lang.Integer viewModelGetIconTintGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelGetIconTint = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelGetIconTintGetValue = 0;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelGetLocationLockEnabledGetValue = false;
        com.google.android.gnd.ui.home.HomeScreenViewModel homeScreenViewModel = mHomeScreenViewModel;
        java.lang.Boolean viewModelGetLocationLockEnabledGetValue = null;
        com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelGetLocationLockEnabled = null;

        if ((dirtyFlags & 0x1bL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.getIconTint()
                        viewModelGetIconTint = viewModel.getIconTint();
                    }
                    updateLiveDataRegistration(0, viewModelGetIconTint);


                    if (viewModelGetIconTint != null) {
                        // read viewModel.getIconTint().getValue()
                        viewModelGetIconTintGetValue = viewModelGetIconTint.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.getIconTint().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelGetIconTintGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelGetIconTintGetValue);
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.getLocationLockEnabled()
                        viewModelGetLocationLockEnabled = viewModel.getLocationLockEnabled();
                    }
                    updateLiveDataRegistration(1, viewModelGetLocationLockEnabled);


                    if (viewModelGetLocationLockEnabled != null) {
                        // read viewModel.getLocationLockEnabled().getValue()
                        viewModelGetLocationLockEnabledGetValue = viewModelGetLocationLockEnabled.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.getLocationLockEnabled().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelGetLocationLockEnabledGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelGetLocationLockEnabledGetValue);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            this.hamburgerBtn.setOnClickListener(mCallback16);
            this.locationLockBtn.setOnClickListener(mCallback15);
            this.mapTypeBtn.setOnClickListener(mCallback14);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            com.google.android.gnd.ui.common.BindingAdapters.bindImageTint(this.hamburgerBtn, androidxDatabindingViewDataBindingSafeUnboxViewModelGetIconTintGetValue);
            com.google.android.gnd.ui.common.BindingAdapters.bindImageTint(this.locationLockBtn, androidxDatabindingViewDataBindingSafeUnboxViewModelGetIconTintGetValue);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            this.locationLockBtn.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelGetLocationLockEnabledGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 3: {
                // localize variables for thread safety
                // homeScreenViewModel != null
                boolean homeScreenViewModelJavaLangObjectNull = false;
                // homeScreenViewModel
                com.google.android.gnd.ui.home.HomeScreenViewModel homeScreenViewModel = mHomeScreenViewModel;



                homeScreenViewModelJavaLangObjectNull = (homeScreenViewModel) != (null);
                if (homeScreenViewModelJavaLangObjectNull) {


                    homeScreenViewModel.openNavDrawer();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // viewModel
                com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.onMapTypeButtonClicked();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // viewModel
                com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.onLocationLockClick();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.getIconTint()
        flag 1 (0x2L): viewModel.getLocationLockEnabled()
        flag 2 (0x3L): homeScreenViewModel
        flag 3 (0x4L): viewModel
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}
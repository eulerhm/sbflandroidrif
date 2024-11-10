package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class MapContainerFragBindingImpl extends MapContainerFragBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(6);
        sIncludes.setIncludes(1, 
            new String[] {"map_home_layout"},
            new int[] {3},
            new int[] {com.google.android.gnd.R.layout.map_home_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.map, 4);
        sViewsWithIds.put(R.id.map_crosshairs_img, 5);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public MapContainerFragBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private MapContainerFragBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (android.widget.TextView) bindings[2]
            , (android.widget.FrameLayout) bindings[4]
            , (com.google.android.gnd.databinding.MapHomeLayoutBinding) bindings[3]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.FrameLayout) bindings[1]
            );
        this.locationAccuracy.setTag(null);
        setContainedBinding(this.mapControls);
        this.mapOverlay.setTag(null);
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
        }
        mapControls.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mapControls.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.homeScreenViewModel == variableId) {
            setHomeScreenViewModel((com.google.android.gnd.ui.home.HomeScreenViewModel) variable);
        }
        else if (BR.polygonDrawingViewModel == variableId) {
            setPolygonDrawingViewModel((com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel) variable);
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
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.homeScreenViewModel);
        super.requestRebind();
    }
    public void setPolygonDrawingViewModel(@Nullable com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel PolygonDrawingViewModel) {
        this.mPolygonDrawingViewModel = PolygonDrawingViewModel;
    }
    public void setViewModel(@Nullable com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        mapControls.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeMapControls((com.google.android.gnd.databinding.MapHomeLayoutBinding) object, fieldId);
            case 1 :
                return onChangeViewModelIsLocationUpdatesEnabled((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeViewModelMapControlsVisibility((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 3 :
                return onChangeViewModelGetLocationAccuracy((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeMapControls(com.google.android.gnd.databinding.MapHomeLayoutBinding MapControls, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsLocationUpdatesEnabled(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsLocationUpdatesEnabled, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelMapControlsVisibility(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelMapControlsVisibility, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelGetLocationAccuracy(androidx.lifecycle.LiveData<java.lang.String> ViewModelGetLocationAccuracy, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
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
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsLocationUpdatesEnabled = null;
        java.lang.Boolean viewModelIsLocationUpdatesEnabledGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelMapControlsVisibility = null;
        java.lang.String viewModelGetLocationAccuracyGetValue = null;
        int androidxDatabindingViewDataBindingSafeUnboxViewModelMapControlsVisibilityGetValue = 0;
        androidx.lifecycle.LiveData<java.lang.String> viewModelGetLocationAccuracy = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsLocationUpdatesEnabledGetValue = false;
        java.lang.Integer viewModelMapControlsVisibilityGetValue = null;
        com.google.android.gnd.ui.home.HomeScreenViewModel homeScreenViewModel = mHomeScreenViewModel;
        com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x90L) != 0) {
        }
        if ((dirtyFlags & 0xceL) != 0) {


            if ((dirtyFlags & 0xc2L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isLocationUpdatesEnabled()
                        viewModelIsLocationUpdatesEnabled = viewModel.isLocationUpdatesEnabled();
                    }
                    updateLiveDataRegistration(1, viewModelIsLocationUpdatesEnabled);


                    if (viewModelIsLocationUpdatesEnabled != null) {
                        // read viewModel.isLocationUpdatesEnabled().getValue()
                        viewModelIsLocationUpdatesEnabledGetValue = viewModelIsLocationUpdatesEnabled.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLocationUpdatesEnabled().getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsLocationUpdatesEnabledGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsLocationUpdatesEnabledGetValue);
            }
            if ((dirtyFlags & 0xc4L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.mapControlsVisibility
                        viewModelMapControlsVisibility = viewModel.getMapControlsVisibility();
                    }
                    updateLiveDataRegistration(2, viewModelMapControlsVisibility);


                    if (viewModelMapControlsVisibility != null) {
                        // read viewModel.mapControlsVisibility.getValue()
                        viewModelMapControlsVisibilityGetValue = viewModelMapControlsVisibility.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.mapControlsVisibility.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelMapControlsVisibilityGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelMapControlsVisibilityGetValue);
            }
            if ((dirtyFlags & 0xc8L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.getLocationAccuracy()
                        viewModelGetLocationAccuracy = viewModel.getLocationAccuracy();
                    }
                    updateLiveDataRegistration(3, viewModelGetLocationAccuracy);


                    if (viewModelGetLocationAccuracy != null) {
                        // read viewModel.getLocationAccuracy().getValue()
                        viewModelGetLocationAccuracyGetValue = viewModelGetLocationAccuracy.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xc8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.locationAccuracy, viewModelGetLocationAccuracyGetValue);
        }
        if ((dirtyFlags & 0xc2L) != 0) {
            // api target 1

            com.google.android.gnd.ui.common.BindingAdapters.bindVisible(this.locationAccuracy, androidxDatabindingViewDataBindingSafeUnboxViewModelIsLocationUpdatesEnabledGetValue);
        }
        if ((dirtyFlags & 0xc4L) != 0) {
            // api target 1

            this.mapControls.getRoot().setVisibility(androidxDatabindingViewDataBindingSafeUnboxViewModelMapControlsVisibilityGetValue);
        }
        if ((dirtyFlags & 0x90L) != 0) {
            // api target 1

            this.mapControls.setHomeScreenViewModel(homeScreenViewModel);
        }
        if ((dirtyFlags & 0xc0L) != 0) {
            // api target 1

            this.mapControls.setViewModel(viewModel);
        }
        executeBindingsOn(mapControls);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): mapControls
        flag 1 (0x2L): viewModel.isLocationUpdatesEnabled()
        flag 2 (0x3L): viewModel.mapControlsVisibility
        flag 3 (0x4L): viewModel.getLocationAccuracy()
        flag 4 (0x5L): homeScreenViewModel
        flag 5 (0x6L): polygonDrawingViewModel
        flag 6 (0x7L): viewModel
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}
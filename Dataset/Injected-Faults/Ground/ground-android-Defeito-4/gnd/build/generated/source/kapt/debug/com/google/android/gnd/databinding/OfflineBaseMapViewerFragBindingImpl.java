package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class OfflineBaseMapViewerFragBindingImpl extends OfflineBaseMapViewerFragBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.offline_area_viewer_toolbar, 3);
        sViewsWithIds.put(R.id.offline_area_viewer_map_frame, 4);
        sViewsWithIds.put(R.id.map, 5);
        sViewsWithIds.put(R.id.remove_button, 6);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public OfflineBaseMapViewerFragBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private OfflineBaseMapViewerFragBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.TextView) bindings[1]
            , (androidx.fragment.app.FragmentContainerView) bindings[5]
            , (android.widget.FrameLayout) bindings[4]
            , (com.google.android.gnd.ui.common.TwoLineToolbar) bindings[3]
            , (com.google.android.material.chip.Chip) bindings[6]
            , (android.widget.TextView) bindings[2]
            );
        this.baseMapName.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.textView.setTag(null);
        setRootTag(root);
        // listeners
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
            setViewModel((com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerViewModel ViewModel) {
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
                return onChangeViewModelAreaStorageSize((androidx.lifecycle.LiveData<java.lang.Double>) object, fieldId);
            case 1 :
                return onChangeViewModelAreaName((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelAreaStorageSize(androidx.lifecycle.LiveData<java.lang.Double> ViewModelAreaStorageSize, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelAreaName(androidx.lifecycle.LiveData<java.lang.String> ViewModelAreaName, int fieldId) {
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
        java.lang.String viewModelAreaNameGetValue = null;
        androidx.lifecycle.LiveData<java.lang.Double> viewModelAreaStorageSize = null;
        java.lang.Double viewModelAreaStorageSizeGetValue = null;
        java.lang.String stringFormatBaseMapNameAndroidStringOfflineBaseMapNameViewModelAreaName = null;
        java.lang.String stringFormatTextViewAndroidStringOfflineBaseMapViewerStorageViewModelAreaStorageSize = null;
        com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.lang.String> viewModelAreaName = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.areaStorageSize
                        viewModelAreaStorageSize = viewModel.areaStorageSize;
                    }
                    updateLiveDataRegistration(0, viewModelAreaStorageSize);


                    if (viewModelAreaStorageSize != null) {
                        // read viewModel.areaStorageSize.getValue()
                        viewModelAreaStorageSizeGetValue = viewModelAreaStorageSize.getValue();
                    }


                    // read String.format(@android:string/offline_base_map_viewer_storage, viewModel.areaStorageSize.getValue())
                    stringFormatTextViewAndroidStringOfflineBaseMapViewerStorageViewModelAreaStorageSize = java.lang.String.format(textView.getResources().getString(R.string.offline_base_map_viewer_storage), viewModelAreaStorageSizeGetValue);
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.areaName
                        viewModelAreaName = viewModel.areaName;
                    }
                    updateLiveDataRegistration(1, viewModelAreaName);


                    if (viewModelAreaName != null) {
                        // read viewModel.areaName.getValue()
                        viewModelAreaNameGetValue = viewModelAreaName.getValue();
                    }


                    // read String.format(@android:string/offline_base_map_name, viewModel.areaName.getValue())
                    stringFormatBaseMapNameAndroidStringOfflineBaseMapNameViewModelAreaName = java.lang.String.format(baseMapName.getResources().getString(R.string.offline_base_map_name), viewModelAreaNameGetValue);
            }
        }
        // batch finished
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.baseMapName, stringFormatBaseMapNameAndroidStringOfflineBaseMapNameViewModelAreaName);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textView, stringFormatTextViewAndroidStringOfflineBaseMapViewerStorageViewModelAreaStorageSize);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.areaStorageSize
        flag 1 (0x2L): viewModel.areaName
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}
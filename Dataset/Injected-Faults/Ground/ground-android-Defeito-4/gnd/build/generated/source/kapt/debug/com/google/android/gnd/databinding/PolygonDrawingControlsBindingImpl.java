package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class PolygonDrawingControlsBindingImpl extends PolygonDrawingControlsBinding implements com.google.android.gnd.generated.callback.OnClickListener.Listener {

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
    private final android.view.View.OnClickListener mCallback17;
    @Nullable
    private final android.view.View.OnClickListener mCallback18;
    @Nullable
    private final android.view.View.OnClickListener mCallback19;
    @Nullable
    private final android.view.View.OnClickListener mCallback20;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public PolygonDrawingControlsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private PolygonDrawingControlsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.Button) bindings[2]
            , (android.widget.Button) bindings[3]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[4]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[1]
            );
        this.addPolygonButton.setTag(null);
        this.completePolygonButton.setTag(null);
        this.currentLocationButton.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.undoButton.setTag(null);
        setRootTag(root);
        // listeners
        mCallback17 = new com.google.android.gnd.generated.callback.OnClickListener(this, 1);
        mCallback18 = new com.google.android.gnd.generated.callback.OnClickListener(this, 2);
        mCallback19 = new com.google.android.gnd.generated.callback.OnClickListener(this, 3);
        mCallback20 = new com.google.android.gnd.generated.callback.OnClickListener(this, 4);
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
            setViewModel((com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel ViewModel) {
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
        java.lang.Boolean viewModelGetLocationLockEnabledGetValue = null;
        com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelGetLocationLockEnabled = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

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
            if ((dirtyFlags & 0xeL) != 0) {

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
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.addPolygonButton.setOnClickListener(mCallback18);
            this.completePolygonButton.setOnClickListener(mCallback19);
            this.currentLocationButton.setOnClickListener(mCallback20);
            this.undoButton.setOnClickListener(mCallback17);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            this.currentLocationButton.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelGetLocationLockEnabledGetValue);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            com.google.android.gnd.ui.common.BindingAdapters.bindImageTint(this.currentLocationButton, androidxDatabindingViewDataBindingSafeUnboxViewModelGetIconTintGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 1: {
                // localize variables for thread safety
                // viewModel
                com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.removeLastVertex();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // viewModel.selectCurrentVertex()
                kotlin.Unit viewModelSelectCurrentVertex = null;
                // viewModel
                com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModelSelectCurrentVertex = viewModel.selectCurrentVertex();
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // viewModel
                com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModel.onCompletePolygonButtonClick();
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // viewModel
                com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel viewModel = mViewModel;
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
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}
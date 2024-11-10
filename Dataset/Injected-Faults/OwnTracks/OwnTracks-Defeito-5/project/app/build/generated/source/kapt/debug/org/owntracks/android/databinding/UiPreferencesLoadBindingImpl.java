package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiPreferencesLoadBindingImpl extends UiPreferencesLoadBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 3);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiPreferencesLoadBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private UiPreferencesLoadBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[2]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (android.widget.RelativeLayout) bindings[1]
            , (androidx.appcompat.widget.Toolbar) bindings[3]
            );
        this.effectiveConfiguration.setTag(null);
        this.frame.setTag(null);
        this.spinner.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.vm == variableId) {
            setVm((org.owntracks.android.ui.preferences.load.LoadMvvm.ViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.preferences.load.LoadMvvm.ViewModel Vm) {
        updateRegistration(0, Vm);
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVm((org.owntracks.android.ui.preferences.load.LoadMvvm.ViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.preferences.load.LoadMvvm.ViewModel Vm, int fieldId) {
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
        int vmConfigurationImportStatusImportStatusLOADINGViewVISIBLEViewGONE = 0;
        java.lang.String vmDisplayedConfiguration = null;
        org.owntracks.android.ui.preferences.load.ImportStatus vmConfigurationImportStatus = null;
        org.owntracks.android.ui.preferences.load.LoadMvvm.ViewModel<? extends org.owntracks.android.ui.base.view.MvvmView> vm = mVm;
        boolean vmConfigurationImportStatusImportStatusLOADING = false;

        if ((dirtyFlags & 0x3L) != 0) {



                if (vm != null) {
                    // read vm.displayedConfiguration
                    vmDisplayedConfiguration = vm.getDisplayedConfiguration();
                    // read vm.configurationImportStatus
                    vmConfigurationImportStatus = vm.getConfigurationImportStatus();
                }


                // read vm.configurationImportStatus == ImportStatus.LOADING
                vmConfigurationImportStatusImportStatusLOADING = (vmConfigurationImportStatus) == (org.owntracks.android.ui.preferences.load.ImportStatus.LOADING);
            if((dirtyFlags & 0x3L) != 0) {
                if(vmConfigurationImportStatusImportStatusLOADING) {
                        dirtyFlags |= 0x8L;
                }
                else {
                        dirtyFlags |= 0x4L;
                }
            }


                // read vm.configurationImportStatus == ImportStatus.LOADING ? View.VISIBLE : View.GONE
                vmConfigurationImportStatusImportStatusLOADINGViewVISIBLEViewGONE = ((vmConfigurationImportStatusImportStatusLOADING) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.effectiveConfiguration, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmDisplayedConfiguration));
            this.spinner.setVisibility(vmConfigurationImportStatusImportStatusLOADINGViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): null
        flag 2 (0x3L): vm.configurationImportStatus == ImportStatus.LOADING ? View.VISIBLE : View.GONE
        flag 3 (0x4L): vm.configurationImportStatus == ImportStatus.LOADING ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}
package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiMapBindingImpl extends UiMapBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(15);
        sIncludes.setIncludes(1, 
            new String[] {"ui_row_contact"},
            new int[] {2},
            new int[] {org.owntracks.android.R.layout.ui_row_contact});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appBarLayout, 3);
        sViewsWithIds.put(R.id.toolbar, 4);
        sViewsWithIds.put(R.id.mapView, 5);
        sViewsWithIds.put(R.id.acc, 6);
        sViewsWithIds.put(R.id.accLabel, 7);
        sViewsWithIds.put(R.id.distance, 8);
        sViewsWithIds.put(R.id.distanceLabel, 9);
        sViewsWithIds.put(R.id.tid, 10);
        sViewsWithIds.put(R.id.tidLabel, 11);
        sViewsWithIds.put(R.id.id, 12);
        sViewsWithIds.put(R.id.topicLabel, 13);
        sViewsWithIds.put(R.id.moreButton, 14);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiMapBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private UiMapBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (com.google.android.material.appbar.AppBarLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[1]
            , (org.owntracks.android.databinding.UiRowContactBinding) bindings[2]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[12]
            , (com.google.android.gms.maps.MapView) bindings[5]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[11]
            , (androidx.appcompat.widget.Toolbar) bindings[4]
            , (android.widget.TextView) bindings[13]
            );
        this.bottomSheetLayout.setTag(null);
        setContainedBinding(this.contactPeek);
        this.coordinatorLayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        contactPeek.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (contactPeek.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.vm == variableId) {
            setVm((org.owntracks.android.ui.map.MapViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.map.MapViewModel Vm) {
        this.mVm = Vm;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        contactPeek.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVm((org.owntracks.android.ui.map.MapViewModel) object, fieldId);
            case 1 :
                return onChangeContactPeek((org.owntracks.android.databinding.UiRowContactBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.map.MapViewModel Vm, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeContactPeek(org.owntracks.android.databinding.UiRowContactBinding ContactPeek, int fieldId) {
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
        // batch finished
        executeBindingsOn(contactPeek);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): contactPeek
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}
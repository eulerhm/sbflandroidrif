package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiRegionBindingImpl extends UiRegionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 5);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener descriptionandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.waypoint.description
            //         is vm.waypoint.setDescription((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(description);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.region.RegionViewModel vm = mVm;
            // vm.waypoint != null
            boolean vmWaypointJavaLangObjectNull = false;
            // vm.waypoint.description
            java.lang.String vmWaypointDescription = null;
            // vm.waypoint
            org.owntracks.android.data.WaypointModel vmWaypoint = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {


                vmWaypoint = vm.getWaypoint();

                vmWaypointJavaLangObjectNull = (vmWaypoint) != (null);
                if (vmWaypointJavaLangObjectNull) {




                    vmWaypoint.setDescription(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener latitudeandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.waypoint.geofenceLatitudeAsStr
            //         is vm.waypoint.setGeofenceLatitudeAsStr((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(latitude);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.region.RegionViewModel vm = mVm;
            // vm.waypoint.geofenceLatitudeAsStr
            java.lang.String vmWaypointGeofenceLatitudeAsStr = null;
            // vm.waypoint != null
            boolean vmWaypointJavaLangObjectNull = false;
            // vm.waypoint
            org.owntracks.android.data.WaypointModel vmWaypoint = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {


                vmWaypoint = vm.getWaypoint();

                vmWaypointJavaLangObjectNull = (vmWaypoint) != (null);
                if (vmWaypointJavaLangObjectNull) {




                    vmWaypoint.setGeofenceLatitudeAsStr(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener longitudeandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.waypoint.geofenceLongitudeAsStr
            //         is vm.waypoint.setGeofenceLongitudeAsStr((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(longitude);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.region.RegionViewModel vm = mVm;
            // vm.waypoint != null
            boolean vmWaypointJavaLangObjectNull = false;
            // vm.waypoint.geofenceLongitudeAsStr
            java.lang.String vmWaypointGeofenceLongitudeAsStr = null;
            // vm.waypoint
            org.owntracks.android.data.WaypointModel vmWaypoint = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {


                vmWaypoint = vm.getWaypoint();

                vmWaypointJavaLangObjectNull = (vmWaypoint) != (null);
                if (vmWaypointJavaLangObjectNull) {




                    vmWaypoint.setGeofenceLongitudeAsStr(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener radiusandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of org.owntracks.android.support.widgets.BindingConversions.convertToStringZeroIsEmpty(vm.waypoint.geofenceRadius)
            //         is vm.waypoint.setGeofenceRadius((int) org.owntracks.android.support.widgets.BindingConversions.convertToIntegerZeroIsEmpty(callbackArg_0))
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(radius);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.region.RegionViewModel vm = mVm;
            // vm.waypoint != null
            boolean vmWaypointJavaLangObjectNull = false;
            // vm.waypoint.geofenceRadius
            int vmWaypointGeofenceRadius = 0;
            // vm.waypoint
            org.owntracks.android.data.WaypointModel vmWaypoint = null;
            // org.owntracks.android.support.widgets.BindingConversions.convertToStringZeroIsEmpty(vm.waypoint.geofenceRadius)
            java.lang.String orgOwntracksAndroidSupportWidgetsBindingConversionsConvertToStringZeroIsEmptyVmWaypointGeofenceRadius = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {


                vmWaypoint = vm.getWaypoint();

                vmWaypointJavaLangObjectNull = (vmWaypoint) != (null);
                if (vmWaypointJavaLangObjectNull) {



                    org.owntracks.android.support.widgets.BindingConversions.convertToIntegerZeroIsEmpty(callbackArg_0);


                    vmWaypoint.setGeofenceRadius(((int) (org.owntracks.android.support.widgets.BindingConversions.convertToIntegerZeroIsEmpty(callbackArg_0))));
                }
            }
        }
    };

    public UiRegionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private UiRegionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[1]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[2]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[3]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[4]
            , (androidx.appcompat.widget.Toolbar) bindings[5]
            );
        this.description.setTag(null);
        this.latitude.setTag(null);
        this.longitude.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.radius.setTag(null);
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
        if (BR.vm == variableId) {
            setVm((org.owntracks.android.ui.region.RegionViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.region.RegionViewModel Vm) {
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
                return onChangeVm((org.owntracks.android.ui.region.RegionViewModel) object, fieldId);
            case 1 :
                return onChangeVmWaypoint((org.owntracks.android.data.WaypointModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.region.RegionViewModel Vm, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.waypoint) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeVmWaypoint(org.owntracks.android.data.WaypointModel VmWaypoint, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.geofenceLatitudeAsStr) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.geofenceLongitudeAsStr) {
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
        java.lang.String vmWaypointGeofenceLatitudeAsStr = null;
        java.lang.String vmWaypointGeofenceLongitudeAsStr = null;
        java.lang.String vmWaypointDescription = null;
        java.lang.String orgOwntracksAndroidSupportWidgetsBindingConversionsConvertToStringZeroIsEmptyVmWaypointGeofenceRadius = null;
        org.owntracks.android.ui.region.RegionViewModel vm = mVm;
        int vmWaypointGeofenceRadius = 0;
        org.owntracks.android.data.WaypointModel vmWaypoint = null;

        if ((dirtyFlags & 0x1fL) != 0) {



                if (vm != null) {
                    // read vm.waypoint
                    vmWaypoint = vm.getWaypoint();
                }
                updateRegistration(1, vmWaypoint);

            if ((dirtyFlags & 0x17L) != 0) {

                    if (vmWaypoint != null) {
                        // read vm.waypoint.geofenceLatitudeAsStr
                        vmWaypointGeofenceLatitudeAsStr = vmWaypoint.getGeofenceLatitudeAsStr();
                    }
            }
            if ((dirtyFlags & 0x1bL) != 0) {

                    if (vmWaypoint != null) {
                        // read vm.waypoint.geofenceLongitudeAsStr
                        vmWaypointGeofenceLongitudeAsStr = vmWaypoint.getGeofenceLongitudeAsStr();
                    }
            }
            if ((dirtyFlags & 0x13L) != 0) {

                    if (vmWaypoint != null) {
                        // read vm.waypoint.description
                        vmWaypointDescription = vmWaypoint.getDescription();
                        // read vm.waypoint.geofenceRadius
                        vmWaypointGeofenceRadius = vmWaypoint.getGeofenceRadius();
                    }


                    // read org.owntracks.android.support.widgets.BindingConversions.convertToStringZeroIsEmpty(vm.waypoint.geofenceRadius)
                    orgOwntracksAndroidSupportWidgetsBindingConversionsConvertToStringZeroIsEmptyVmWaypointGeofenceRadius = org.owntracks.android.support.widgets.BindingConversions.convertToStringZeroIsEmpty(vmWaypointGeofenceRadius);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x13L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.description, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmWaypointDescription));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.radius, org.owntracks.android.support.widgets.BindingConversions.convertToString(orgOwntracksAndroidSupportWidgetsBindingConversionsConvertToStringZeroIsEmptyVmWaypointGeofenceRadius));
        }
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.description, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, descriptionandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.latitude, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, latitudeandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.longitude, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, longitudeandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.radius, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, radiusandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x17L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.latitude, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmWaypointGeofenceLatitudeAsStr));
        }
        if ((dirtyFlags & 0x1bL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.longitude, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmWaypointGeofenceLongitudeAsStr));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): vm.waypoint
        flag 2 (0x3L): vm.waypoint.geofenceLatitudeAsStr
        flag 3 (0x4L): vm.waypoint.geofenceLongitudeAsStr
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}
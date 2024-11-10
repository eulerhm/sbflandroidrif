package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiStatusBindingImpl extends UiStatusBinding implements org.owntracks.android.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 9);
        sViewsWithIds.put(R.id.dozeWhiteListed, 10);
    }
    // views
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.TextView mboundView6;
    @NonNull
    private final android.widget.TextView mboundView7;
    @NonNull
    private final android.widget.TextView mboundView8;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback11;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiStatusBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private UiStatusBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[1]
            , (android.widget.LinearLayout) bindings[10]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (androidx.appcompat.widget.Toolbar) bindings[9]
            );
        this.connectedStatus.setTag(null);
        this.frame.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.TextView) bindings[8];
        this.mboundView8.setTag(null);
        setRootTag(root);
        // listeners
        mCallback11 = new org.owntracks.android.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
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
            setVm((org.owntracks.android.ui.status.StatusViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.status.StatusViewModel Vm) {
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
                return onChangeVm((org.owntracks.android.ui.status.StatusViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.status.StatusViewModel Vm, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.endpointState) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.endpointMessage) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.endpointQueue) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.locationUpdated) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.serviceStarted) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
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
        java.lang.String dateFormatterFormatDateVmLocationUpdated = null;
        org.owntracks.android.ui.status.StatusViewModel vm = mVm;
        int vmEndpointQueue = 0;
        boolean vmLocationUpdatedInt0 = false;
        long vmLocationUpdated = 0;
        boolean vmEndpointMessageJavaLangObjectNull = false;
        java.lang.String vmLocationUpdatedInt0DateFormatterFormatDateVmLocationUpdatedMboundView5AndroidStringNa = null;
        java.lang.String vmServiceStartedJavaLangObjectNullDateFormatterFormatDateVmServiceStartedMboundView6AndroidStringNa = null;
        java.lang.String vmEndpointMessage = null;
        java.util.Date vmServiceStarted = null;
        java.lang.String orgOwntracksAndroidSupportWidgetsBindingConversionsConvertToStringVmEndpointQueue = null;
        boolean VmEndpointMessageJavaLangObjectNull1 = false;
        java.lang.String vmEndpointMessageJavaLangObjectNullMboundView2AndroidStringNaVmEndpointMessage = null;
        java.lang.String dateFormatterFormatDateVmServiceStarted = null;
        boolean vmDozeWhitelisted = false;
        boolean vmServiceStartedJavaLangObjectNull = false;
        org.owntracks.android.services.MessageProcessor.EndpointState vmEndpointState = null;

        if ((dirtyFlags & 0x7fL) != 0) {


            if ((dirtyFlags & 0x49L) != 0) {

                    if (vm != null) {
                        // read vm.endpointQueue
                        vmEndpointQueue = vm.getEndpointQueue();
                    }


                    // read org.owntracks.android.support.widgets.BindingConversions.convertToString(vm.endpointQueue)
                    orgOwntracksAndroidSupportWidgetsBindingConversionsConvertToStringVmEndpointQueue = org.owntracks.android.support.widgets.BindingConversions.convertToString(vmEndpointQueue);
            }
            if ((dirtyFlags & 0x51L) != 0) {

                    if (vm != null) {
                        // read vm.locationUpdated
                        vmLocationUpdated = vm.getLocationUpdated();
                    }


                    // read vm.locationUpdated != 0
                    vmLocationUpdatedInt0 = (vmLocationUpdated) != (0);
                if((dirtyFlags & 0x51L) != 0) {
                    if(vmLocationUpdatedInt0) {
                            dirtyFlags |= 0x100L;
                    }
                    else {
                            dirtyFlags |= 0x80L;
                    }
                }
            }
            if ((dirtyFlags & 0x45L) != 0) {

                    if (vm != null) {
                        // read vm.endpointMessage
                        vmEndpointMessage = vm.getEndpointMessage();
                    }


                    // read vm.endpointMessage == null
                    vmEndpointMessageJavaLangObjectNull = (vmEndpointMessage) == (null);
                    // read vm.endpointMessage != null
                    VmEndpointMessageJavaLangObjectNull1 = (vmEndpointMessage) != (null);
                if((dirtyFlags & 0x45L) != 0) {
                    if(vmEndpointMessageJavaLangObjectNull) {
                            dirtyFlags |= 0x1000L;
                    }
                    else {
                            dirtyFlags |= 0x800L;
                    }
                }
            }
            if ((dirtyFlags & 0x61L) != 0) {

                    if (vm != null) {
                        // read vm.serviceStarted
                        vmServiceStarted = vm.getServiceStarted();
                    }


                    // read vm.serviceStarted != null
                    vmServiceStartedJavaLangObjectNull = (vmServiceStarted) != (null);
                if((dirtyFlags & 0x61L) != 0) {
                    if(vmServiceStartedJavaLangObjectNull) {
                            dirtyFlags |= 0x400L;
                    }
                    else {
                            dirtyFlags |= 0x200L;
                    }
                }
            }
            if ((dirtyFlags & 0x41L) != 0) {

                    if (vm != null) {
                        // read vm.dozeWhitelisted
                        vmDozeWhitelisted = vm.getDozeWhitelisted();
                    }
            }
            if ((dirtyFlags & 0x43L) != 0) {

                    if (vm != null) {
                        // read vm.endpointState
                        vmEndpointState = vm.getEndpointState();
                    }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x100L) != 0) {

                // read DateFormatter.formatDate(vm.locationUpdated)
                dateFormatterFormatDateVmLocationUpdated = org.owntracks.android.support.DateFormatter.formatDate(vmLocationUpdated);
        }
        if ((dirtyFlags & 0x45L) != 0) {

                // read vm.endpointMessage == null ? @android:string/na : vm.endpointMessage
                vmEndpointMessageJavaLangObjectNullMboundView2AndroidStringNaVmEndpointMessage = ((vmEndpointMessageJavaLangObjectNull) ? (mboundView2.getResources().getString(R.string.na)) : (vmEndpointMessage));
        }
        if ((dirtyFlags & 0x400L) != 0) {

                // read DateFormatter.formatDate(vm.serviceStarted)
                dateFormatterFormatDateVmServiceStarted = org.owntracks.android.support.DateFormatter.formatDate(vmServiceStarted);
        }

        if ((dirtyFlags & 0x51L) != 0) {

                // read vm.locationUpdated != 0 ? DateFormatter.formatDate(vm.locationUpdated) : @android:string/na
                vmLocationUpdatedInt0DateFormatterFormatDateVmLocationUpdatedMboundView5AndroidStringNa = ((vmLocationUpdatedInt0) ? (dateFormatterFormatDateVmLocationUpdated) : (mboundView5.getResources().getString(R.string.na)));
        }
        if ((dirtyFlags & 0x61L) != 0) {

                // read vm.serviceStarted != null ? DateFormatter.formatDate(vm.serviceStarted) : @android:string/na
                vmServiceStartedJavaLangObjectNullDateFormatterFormatDateVmServiceStartedMboundView6AndroidStringNa = ((vmServiceStartedJavaLangObjectNull) ? (dateFormatterFormatDateVmServiceStarted) : (mboundView6.getResources().getString(R.string.na)));
        }
        // batch finished
        if ((dirtyFlags & 0x43L) != 0) {
            // api target 1

            org.owntracks.android.support.widgets.BindingConversions.setText(this.connectedStatus, vmEndpointState);
        }
        if ((dirtyFlags & 0x45L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmEndpointMessageJavaLangObjectNullMboundView2AndroidStringNaVmEndpointMessage));
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.mboundView2, VmEndpointMessageJavaLangObjectNull1);
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.mboundView3, VmEndpointMessageJavaLangObjectNull1);
        }
        if ((dirtyFlags & 0x49L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, org.owntracks.android.support.widgets.BindingConversions.convertToString(orgOwntracksAndroidSupportWidgetsBindingConversionsConvertToStringVmEndpointQueue));
        }
        if ((dirtyFlags & 0x51L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmLocationUpdatedInt0DateFormatterFormatDateVmLocationUpdatedMboundView5AndroidStringNa));
        }
        if ((dirtyFlags & 0x61L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmServiceStartedJavaLangObjectNullDateFormatterFormatDateVmServiceStartedMboundView6AndroidStringNa));
        }
        if ((dirtyFlags & 0x41L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmDozeWhitelisted));
        }
        if ((dirtyFlags & 0x40L) != 0) {
            // api target 1

            this.mboundView8.setOnClickListener(mCallback11);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // vm != null
        boolean vmJavaLangObjectNull = false;
        // vm
        org.owntracks.android.ui.status.StatusViewModel vm = mVm;



        vmJavaLangObjectNull = (vm) != (null);
        if (vmJavaLangObjectNull) {


            vm.viewLogs();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): vm.endpointState
        flag 2 (0x3L): vm.endpointMessage
        flag 3 (0x4L): vm.endpointQueue
        flag 4 (0x5L): vm.locationUpdated
        flag 5 (0x6L): vm.serviceStarted
        flag 6 (0x7L): null
        flag 7 (0x8L): vm.locationUpdated != 0 ? DateFormatter.formatDate(vm.locationUpdated) : @android:string/na
        flag 8 (0x9L): vm.locationUpdated != 0 ? DateFormatter.formatDate(vm.locationUpdated) : @android:string/na
        flag 9 (0xaL): vm.serviceStarted != null ? DateFormatter.formatDate(vm.serviceStarted) : @android:string/na
        flag 10 (0xbL): vm.serviceStarted != null ? DateFormatter.formatDate(vm.serviceStarted) : @android:string/na
        flag 11 (0xcL): vm.endpointMessage == null ? @android:string/na : vm.endpointMessage
        flag 12 (0xdL): vm.endpointMessage == null ? @android:string/na : vm.endpointMessage
    flag mapping end*/
    //end
}
package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiPreferencesConnectionIdentificationBindingImpl extends UiPreferencesConnectionIdentificationBinding  {

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
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener deviceIdandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.deviceId
            //         is vm.setDeviceId((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(deviceId);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel vm = mVm;
            // vm.deviceId
            java.lang.String vmDeviceId = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setDeviceId(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener passwordandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.password
            //         is vm.setPassword((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(password);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel vm = mVm;
            // vm.password
            java.lang.String vmPassword = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setPassword(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener trackerIdandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.trackerId
            //         is vm.setTrackerId((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(trackerId);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel vm = mVm;
            // vm.trackerId
            java.lang.String vmTrackerId = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setTrackerId(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener usernameandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.username
            //         is vm.setUsername((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(username);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel vm = mVm;
            // vm.username
            java.lang.String vmUsername = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setUsername(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public UiPreferencesConnectionIdentificationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private UiPreferencesConnectionIdentificationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[3]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[2]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[4]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[1]
            );
        this.deviceId.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.password.setTag(null);
        this.trackerId.setTag(null);
        this.username.setTag(null);
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
            setVm((org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel Vm) {
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
                return onChangeVm((org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel Vm, int fieldId) {
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
        org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel vm = mVm;
        java.lang.String vmUsername = null;
        java.lang.String vmPassword = null;
        java.lang.String vmDeviceId = null;
        java.lang.String vmTrackerId = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (vm != null) {
                    // read vm.username
                    vmUsername = vm.getUsername();
                    // read vm.password
                    vmPassword = vm.getPassword();
                    // read vm.deviceId
                    vmDeviceId = vm.getDeviceId();
                    // read vm.trackerId
                    vmTrackerId = vm.getTrackerId();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.deviceId, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmDeviceId));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.password, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmPassword));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.trackerId, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmTrackerId));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.username, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmUsername));
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.deviceId, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, deviceIdandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.password, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, passwordandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.trackerId, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, trackerIdandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.username, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, usernameandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}
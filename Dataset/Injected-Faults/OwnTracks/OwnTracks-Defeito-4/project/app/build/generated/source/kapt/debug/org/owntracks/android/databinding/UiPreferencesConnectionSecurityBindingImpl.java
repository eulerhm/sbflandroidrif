package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiPreferencesConnectionSecurityBindingImpl extends UiPreferencesConnectionSecurityBinding implements org.owntracks.android.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tlsWrapper, 5);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback9;
    // values
    // listeners
    private OnCheckedChangeListenerImpl mVmOnTlsCheckedChangedAndroidWidgetCompoundButtonOnCheckedChangeListener;
    private OnClickListenerImpl mVmOnTlsCaCrtNameClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener tlsClientCrtPasswordandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.tlsClientCrtPassword
            //         is vm.setTlsClientCrtPassword((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(tlsClientCrtPassword);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel vm = mVm;
            // vm.tlsClientCrtPassword
            java.lang.String vmTlsClientCrtPassword = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setTlsClientCrtPassword(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public UiPreferencesConnectionSecurityBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private UiPreferencesConnectionSecurityBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.appcompat.widget.SwitchCompat) bindings[1]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[2]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[3]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[4]
            , (android.widget.RelativeLayout) bindings[5]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tls.setTag(null);
        this.tlsCaCrt.setTag(null);
        this.tlsClientCrt.setTag(null);
        this.tlsClientCrtPassword.setTag(null);
        setRootTag(root);
        // listeners
        mCallback9 = new org.owntracks.android.generated.callback.OnClickListener(this, 1);
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
            setVm((org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel Vm) {
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
                return onChangeVm((org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel Vm, int fieldId) {
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
        android.widget.CompoundButton.OnCheckedChangeListener vmOnTlsCheckedChangedAndroidWidgetCompoundButtonOnCheckedChangeListener = null;
        java.lang.String vmTlsClientCrtPassword = null;
        java.lang.String vmTlsClientCrtName = null;
        boolean vmTls = false;
        org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel vm = mVm;
        android.view.View.OnClickListener vmOnTlsCaCrtNameClickAndroidViewViewOnClickListener = null;
        java.lang.String vmTlsCaCrtName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (vm != null) {
                    // read vm::onTlsCheckedChanged
                    vmOnTlsCheckedChangedAndroidWidgetCompoundButtonOnCheckedChangeListener = (((mVmOnTlsCheckedChangedAndroidWidgetCompoundButtonOnCheckedChangeListener == null) ? (mVmOnTlsCheckedChangedAndroidWidgetCompoundButtonOnCheckedChangeListener = new OnCheckedChangeListenerImpl()) : mVmOnTlsCheckedChangedAndroidWidgetCompoundButtonOnCheckedChangeListener).setValue(vm));
                    // read vm.tlsClientCrtPassword
                    vmTlsClientCrtPassword = vm.getTlsClientCrtPassword();
                    // read vm.tlsClientCrtName
                    vmTlsClientCrtName = vm.getTlsClientCrtName();
                    // read vm.tls
                    vmTls = vm.isTls();
                    // read vm::onTlsCaCrtNameClick
                    vmOnTlsCaCrtNameClickAndroidViewViewOnClickListener = (((mVmOnTlsCaCrtNameClickAndroidViewViewOnClickListener == null) ? (mVmOnTlsCaCrtNameClickAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mVmOnTlsCaCrtNameClickAndroidViewViewOnClickListener).setValue(vm));
                    // read vm.tlsCaCrtName
                    vmTlsCaCrtName = vm.getTlsCaCrtName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.tls, vmTls);
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(this.tls, (android.widget.CompoundButton.OnCheckedChangeListener)vmOnTlsCheckedChangedAndroidWidgetCompoundButtonOnCheckedChangeListener, (androidx.databinding.InverseBindingListener)null);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tlsCaCrt, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmTlsCaCrtName));
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.tlsCaCrt, vmTls);
            this.tlsCaCrt.setOnClickListener(vmOnTlsCaCrtNameClickAndroidViewViewOnClickListener);
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.tlsClientCrt, vmTls);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tlsClientCrt, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmTlsClientCrtName));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tlsClientCrtPassword, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmTlsClientCrtPassword));
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.tlsClientCrtPassword, vmTls);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.tlsClientCrt.setOnClickListener(mCallback9);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.tlsClientCrtPassword, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, tlsClientCrtPasswordandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    public static class OnCheckedChangeListenerImpl implements android.widget.CompoundButton.OnCheckedChangeListener{
        private org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel value;
        public OnCheckedChangeListenerImpl setValue(org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onCheckedChanged(android.widget.CompoundButton arg0, boolean arg1) {
            this.value.onTlsCheckedChanged(arg0, arg1); 
        }
    }
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel value;
        public OnClickListenerImpl setValue(org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onTlsCaCrtNameClick(arg0); 
        }
    }
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // vm != null
        boolean vmJavaLangObjectNull = false;
        // vm
        org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel vm = mVm;



        vmJavaLangObjectNull = (vm) != (null);
        if (vmJavaLangObjectNull) {



            vm.onTlsClientCrtNameClick(callbackArg_0);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}
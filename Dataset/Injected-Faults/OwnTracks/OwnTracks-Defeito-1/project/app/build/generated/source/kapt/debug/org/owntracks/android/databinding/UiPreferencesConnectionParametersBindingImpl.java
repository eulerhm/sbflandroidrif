package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiPreferencesConnectionParametersBindingImpl extends UiPreferencesConnectionParametersBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tlsWrapper, 3);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener cleanSessionandroidCheckedAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.cleanSession
            //         is vm.setCleanSession((boolean) callbackArg_0)
            boolean callbackArg_0 = cleanSession.isChecked();
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel vm = mVm;
            // vm.cleanSession
            boolean vmCleanSession = false;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setCleanSession(((boolean) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener keepaliveandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.keepaliveText
            //         is vm.setKeepaliveText((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(keepalive);
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel vm = mVm;
            // vm.keepaliveText
            java.lang.String vmKeepaliveText = null;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setKeepaliveText(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public UiPreferencesConnectionParametersBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private UiPreferencesConnectionParametersBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.appcompat.widget.SwitchCompat) bindings[1]
            , (com.rengwuxian.materialedittext.MaterialEditText) bindings[2]
            , (android.widget.RelativeLayout) bindings[3]
            );
        this.cleanSession.setTag(null);
        this.keepalive.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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
            setVm((org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel Vm) {
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
                return onChangeVm((org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel Vm, int fieldId) {
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
        org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel vm = mVm;
        boolean vmCleanSession = false;
        java.lang.String vmKeepaliveText = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (vm != null) {
                    // read vm.cleanSession
                    vmCleanSession = vm.isCleanSession();
                    // read vm.keepaliveText
                    vmKeepaliveText = vm.getKeepaliveText();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(this.cleanSession, vmCleanSession);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.keepalive, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmKeepaliveText));
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(this.cleanSession, (android.widget.CompoundButton.OnCheckedChangeListener)null, cleanSessionandroidCheckedAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.keepalive, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, keepaliveandroidTextAttrChanged);
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
package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiPreferencesConnectionModeBindingImpl extends UiPreferencesConnectionModeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.radioModeHttpPrivate, 1);
        sViewsWithIds.put(R.id.radioModeMqttPrivate, 2);
    }
    // views
    @NonNull
    private final android.widget.RadioGroup mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener mboundView0androidCheckedButtonAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of vm.mode
            //         is vm.setMode((int) callbackArg_0)
            int callbackArg_0 = mboundView0.getCheckedRadioButtonId();
            // localize variables for thread safety
            // vm != null
            boolean vmJavaLangObjectNull = false;
            // vm
            org.owntracks.android.ui.preferences.connection.dialog.ConnectionModeDialogViewModel vm = mVm;
            // vm.mode
            int vmMode = 0;



            vmJavaLangObjectNull = (vm) != (null);
            if (vmJavaLangObjectNull) {




                vm.setMode(((int) (callbackArg_0)));
            }
        }
    };

    public UiPreferencesConnectionModeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private UiPreferencesConnectionModeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.appcompat.widget.AppCompatRadioButton) bindings[1]
            , (androidx.appcompat.widget.AppCompatRadioButton) bindings[2]
            );
        this.mboundView0 = (android.widget.RadioGroup) bindings[0];
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
            setVm((org.owntracks.android.ui.preferences.connection.dialog.ConnectionModeDialogViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.preferences.connection.dialog.ConnectionModeDialogViewModel Vm) {
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
                return onChangeVm((org.owntracks.android.ui.preferences.connection.dialog.ConnectionModeDialogViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.preferences.connection.dialog.ConnectionModeDialogViewModel Vm, int fieldId) {
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
        org.owntracks.android.ui.preferences.connection.dialog.ConnectionModeDialogViewModel vm = mVm;
        int vmMode = 0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (vm != null) {
                    // read vm.mode
                    vmMode = vm.getMode();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.RadioGroupBindingAdapter.setCheckedButton(this.mboundView0, vmMode);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.RadioGroupBindingAdapter.setListeners(this.mboundView0, (android.widget.RadioGroup.OnCheckedChangeListener)null, mboundView0androidCheckedButtonAttrChanged);
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
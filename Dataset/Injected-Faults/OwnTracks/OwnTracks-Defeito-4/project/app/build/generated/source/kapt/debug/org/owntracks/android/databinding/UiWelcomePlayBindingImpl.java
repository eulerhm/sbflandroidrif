package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiWelcomePlayBindingImpl extends UiWelcomePlayBinding implements org.owntracks.android.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.img, 3);
        sViewsWithIds.put(R.id.screen_heading, 4);
        sViewsWithIds.put(R.id.screen_desc, 5);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback10;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiWelcomePlayBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private UiWelcomePlayBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageView) bindings[3]
            , (android.widget.TextView) bindings[1]
            , (android.widget.Button) bindings[2]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.RelativeLayout) bindings[0]
            );
        this.message.setTag(null);
        this.recover.setTag(null);
        this.welcomeFragment.setTag(null);
        setRootTag(root);
        // listeners
        mCallback10 = new org.owntracks.android.generated.callback.OnClickListener(this, 1);
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
        if (BR.vm == variableId) {
            setVm((org.owntracks.android.ui.welcome.play.PlayFragmentViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.welcome.play.PlayFragmentViewModel Vm) {
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
                return onChangeVm((org.owntracks.android.ui.welcome.play.PlayFragmentViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.welcome.play.PlayFragmentViewModel Vm, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.message) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.fixAvailable) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
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
        org.owntracks.android.ui.welcome.play.PlayFragmentViewModel vm = mVm;
        java.lang.String vmGetMessage = null;
        boolean vmFixAvailable = false;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xbL) != 0) {

                    if (vm != null) {
                        // read vm.getMessage
                        vmGetMessage = vm.getMessage();
                    }
            }
            if ((dirtyFlags & 0xdL) != 0) {

                    if (vm != null) {
                        // read vm.fixAvailable
                        vmFixAvailable = vm.getFixAvailable();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xbL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.message, org.owntracks.android.support.widgets.BindingConversions.convertToString(vmGetMessage));
            this.message.setText(org.owntracks.android.support.widgets.BindingConversions.convertToString(vmGetMessage));
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.recover.setOnClickListener(mCallback10);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.recover, vmFixAvailable);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // vm != null
        boolean vmJavaLangObjectNull = false;
        // vm
        org.owntracks.android.ui.welcome.play.PlayFragmentViewModel vm = mVm;



        vmJavaLangObjectNull = (vm) != (null);
        if (vmJavaLangObjectNull) {


            vm.onFixClicked();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): vm.getMessage
        flag 2 (0x3L): vm.fixAvailable
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}
package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiWelcomeBindingImpl extends UiWelcomeBinding implements org.owntracks.android.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.viewPager, 3);
        sViewsWithIds.put(R.id.circles, 4);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback7;
    @Nullable
    private final android.view.View.OnClickListener mCallback8;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiWelcomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private UiWelcomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageButton) bindings[1]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.ImageButton) bindings[2]
            , (androidx.viewpager2.widget.ViewPager2) bindings[3]
            );
        this.btnNext.setTag(null);
        this.done.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback7 = new org.owntracks.android.generated.callback.OnClickListener(this, 1);
        mCallback8 = new org.owntracks.android.generated.callback.OnClickListener(this, 2);
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
            setVm((org.owntracks.android.ui.welcome.WelcomeViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.welcome.WelcomeViewModel Vm) {
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
                return onChangeVm((org.owntracks.android.ui.welcome.WelcomeViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.welcome.WelcomeViewModel Vm, int fieldId) {
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
        org.owntracks.android.ui.welcome.WelcomeViewModel vm = mVm;
        boolean vmDoneEnabled = false;
        boolean vmNextEnabled = false;

        if ((dirtyFlags & 0x3L) != 0) {



                if (vm != null) {
                    // read vm.doneEnabled
                    vmDoneEnabled = vm.getDoneEnabled();
                    // read vm.nextEnabled
                    vmNextEnabled = vm.getNextEnabled();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.btnNext.setOnClickListener(mCallback7);
            this.done.setOnClickListener(mCallback8);
        }
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.btnNext, vmNextEnabled);
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.done, vmDoneEnabled);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 1: {
                // localize variables for thread safety
                // vm != null
                boolean vmJavaLangObjectNull = false;
                // vm
                org.owntracks.android.ui.welcome.WelcomeViewModel vm = mVm;



                vmJavaLangObjectNull = (vm) != (null);
                if (vmJavaLangObjectNull) {


                    vm.moveNext();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // vm != null
                boolean vmJavaLangObjectNull = false;
                // vm
                org.owntracks.android.ui.welcome.WelcomeViewModel vm = mVm;



                vmJavaLangObjectNull = (vm) != (null);
                if (vmJavaLangObjectNull) {


                    vm.onDoneClicked();
                }
                break;
            }
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
package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiRowRegionBindingImpl extends UiRowRegionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiRowRegionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private UiRowRegionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.LinearLayout) bindings[0]
            , (android.widget.TextView) bindings[1]
            );
        this.meta.setTag(null);
        this.text.setTag(null);
        this.textviewContainer.setTag(null);
        this.title.setTag(null);
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
        if (BR.waypoint == variableId) {
            setWaypoint((org.owntracks.android.data.WaypointModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setWaypoint(@Nullable org.owntracks.android.data.WaypointModel Waypoint) {
        updateRegistration(0, Waypoint);
        this.mWaypoint = Waypoint;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.waypoint);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeWaypoint((org.owntracks.android.data.WaypointModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeWaypoint(org.owntracks.android.data.WaypointModel Waypoint, int fieldId) {
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
        java.lang.String waypointDescription = null;
        int waypointLastTransition = 0;
        long waypointLastTriggered = 0;
        boolean waypointLastTriggeredInt0 = false;
        org.owntracks.android.data.WaypointModel waypoint = mWaypoint;

        if ((dirtyFlags & 0x3L) != 0) {



                if (waypoint != null) {
                    // read waypoint.description
                    waypointDescription = waypoint.getDescription();
                    // read waypoint.lastTransition
                    waypointLastTransition = waypoint.getLastTransition();
                    // read waypoint.lastTriggered
                    waypointLastTriggered = waypoint.getLastTriggered();
                }


                // read waypoint.lastTriggered > 0
                waypointLastTriggeredInt0 = (waypointLastTriggered) > (0);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            org.owntracks.android.support.widgets.BindingConversions.setRelativeTimeSpanString(this.meta, waypointLastTriggered);
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.meta, waypointLastTriggeredInt0);
            org.owntracks.android.support.widgets.BindingConversions.setLastTransition(this.text, waypointLastTransition);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.title, org.owntracks.android.support.widgets.BindingConversions.convertToString(waypointDescription));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): waypoint
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}
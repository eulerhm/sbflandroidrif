package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiRowContactBindingImpl extends UiRowContactBinding  {

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

    public UiRowContactBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private UiRowContactBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            );
        this.contactRow.setTag(null);
        this.image.setTag(null);
        this.location.setTag(null);
        this.locationDate.setTag(null);
        this.name.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
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
        if (BR.contact == variableId) {
            setContact((org.owntracks.android.model.FusedContact) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setContact(@Nullable org.owntracks.android.model.FusedContact Contact) {
        updateRegistration(0, Contact);
        this.mContact = Contact;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.contact);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeContact((org.owntracks.android.model.FusedContact) object, fieldId);
        }
        return false;
    }
    private boolean onChangeContact(org.owntracks.android.model.FusedContact Contact, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.imageProvider) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.fusedName) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.tst) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.geocodedLocation) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
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
        java.lang.String contactGeocodedLocation = null;
        org.owntracks.android.model.FusedContact contact = mContact;
        java.lang.Integer contactImageProvider = null;
        java.lang.String contactFusedName = null;
        long contactTst = 0;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (contact != null) {
                        // read contact.geocodedLocation
                        contactGeocodedLocation = contact.getGeocodedLocation();
                    }
            }
            if ((dirtyFlags & 0x23L) != 0) {

                    if (contact != null) {
                        // read contact.imageProvider
                        contactImageProvider = contact.getImageProvider();
                    }
            }
            if ((dirtyFlags & 0x25L) != 0) {

                    if (contact != null) {
                        // read contact.fusedName
                        contactFusedName = contact.getFusedName();
                    }
            }
            if ((dirtyFlags & 0x29L) != 0) {

                    if (contact != null) {
                        // read contact.tst
                        contactTst = contact.getTst();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x23L) != 0) {
            // api target 1

            org.owntracks.android.support.ContactImageProvider.displayFaceInViewAsync(this.image, contactImageProvider, contact);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.location, org.owntracks.android.support.widgets.BindingConversions.convertToString(contactGeocodedLocation));
        }
        if ((dirtyFlags & 0x29L) != 0) {
            // api target 1

            org.owntracks.android.support.widgets.BindingConversions.setRelativeTimeSpanString(this.locationDate, contactTst);
        }
        if ((dirtyFlags & 0x25L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.name, org.owntracks.android.support.widgets.BindingConversions.convertToString(contactFusedName));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): contact
        flag 1 (0x2L): contact.imageProvider
        flag 2 (0x3L): contact.fusedName
        flag 3 (0x4L): contact.tst
        flag 4 (0x5L): contact.geocodedLocation
        flag 5 (0x6L): null
    flag mapping end*/
    //end
}
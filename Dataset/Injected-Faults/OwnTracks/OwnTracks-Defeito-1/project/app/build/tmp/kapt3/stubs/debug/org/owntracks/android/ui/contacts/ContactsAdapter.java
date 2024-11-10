package org.owntracks.android.ui.contacts;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import org.owntracks.android.BR;
import org.owntracks.android.R;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.ui.base.BaseAdapter;
import java.util.*;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0014\u0010\u0013\u001a\u00020\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/owntracks/android/ui/contacts/ContactsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/owntracks/android/ui/contacts/FusedContactViewHolder;", "clickListener", "Lorg/owntracks/android/ui/base/BaseAdapter$ClickListener;", "Lorg/owntracks/android/model/FusedContact;", "(Lorg/owntracks/android/ui/base/BaseAdapter$ClickListener;)V", "contactList", "", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setContactList", "contacts", "", "app_debug"})
public final class ContactsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.owntracks.android.ui.contacts.FusedContactViewHolder> {
    private java.util.List<? extends org.owntracks.android.model.FusedContact> contactList;
    private final org.owntracks.android.ui.base.BaseAdapter.ClickListener<org.owntracks.android.model.FusedContact> clickListener = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.owntracks.android.ui.contacts.FusedContactViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.ui.contacts.FusedContactViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void setContactList(@org.jetbrains.annotations.NotNull()
    java.util.Collection<? extends org.owntracks.android.model.FusedContact> contacts) {
    }
    
    public ContactsAdapter(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.ui.base.BaseAdapter.ClickListener<org.owntracks.android.model.FusedContact> clickListener) {
        super();
    }
}
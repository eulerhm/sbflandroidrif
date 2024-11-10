package org.owntracks.android.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiContactsBinding;
import org.owntracks.android.geocoding.GeocoderProvider;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.model.messages.MessageLocation;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.BaseAdapter;
import org.owntracks.android.ui.map.MapActivity;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u00012\u00020\u00042\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005B\u0005\u00a2\u0006\u0002\u0010\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/owntracks/android/ui/contacts/ContactsActivity;", "Lorg/owntracks/android/ui/base/BaseActivity;", "Lorg/owntracks/android/databinding/UiContactsBinding;", "Lorg/owntracks/android/ui/contacts/ContactsMvvm$ViewModel;", "Lorg/owntracks/android/ui/contacts/ContactsMvvm$View;", "Lorg/owntracks/android/ui/base/BaseAdapter$ClickListener;", "Lorg/owntracks/android/model/FusedContact;", "()V", "contactsAdapter", "Lorg/owntracks/android/ui/contacts/ContactsAdapter;", "geocoderProvider", "Lorg/owntracks/android/geocoding/GeocoderProvider;", "onClick", "", "fusedContact", "view", "Landroid/view/View;", "longClick", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class ContactsActivity extends org.owntracks.android.ui.base.BaseActivity<org.owntracks.android.databinding.UiContactsBinding, org.owntracks.android.ui.contacts.ContactsMvvm.ViewModel<?>> implements org.owntracks.android.ui.contacts.ContactsMvvm.View, org.owntracks.android.ui.base.BaseAdapter.ClickListener<org.owntracks.android.model.FusedContact> {
    private final org.owntracks.android.ui.contacts.ContactsAdapter contactsAdapter = null;
    @org.jetbrains.annotations.Nullable()
    @javax.inject.Inject()
    public org.owntracks.android.geocoding.GeocoderProvider geocoderProvider;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.FusedContact fusedContact, @org.jetbrains.annotations.NotNull()
    android.view.View view, boolean longClick) {
    }
    
    public ContactsActivity() {
        super();
    }
}
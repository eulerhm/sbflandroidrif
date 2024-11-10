package org.owntracks.android.ui.contacts;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import org.owntracks.android.data.repos.ContactsRepo;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003B\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016R&\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/owntracks/android/ui/contacts/ContactsViewModel;", "Lorg/owntracks/android/ui/base/viewmodel/BaseViewModel;", "Lorg/owntracks/android/ui/contacts/ContactsMvvm$View;", "Lorg/owntracks/android/ui/contacts/ContactsMvvm$ViewModel;", "contactsRepo", "Lorg/owntracks/android/data/repos/ContactsRepo;", "(Lorg/owntracks/android/data/repos/ContactsRepo;)V", "contacts", "Landroidx/lifecycle/MutableLiveData;", "", "", "Lorg/owntracks/android/model/FusedContact;", "getContacts", "()Landroidx/lifecycle/MutableLiveData;", "attachView", "", "savedInstanceState", "Landroid/os/Bundle;", "view", "app_debug"})
@org.owntracks.android.injection.scopes.PerActivity()
public final class ContactsViewModel extends org.owntracks.android.ui.base.viewmodel.BaseViewModel<org.owntracks.android.ui.contacts.ContactsMvvm.View> implements org.owntracks.android.ui.contacts.ContactsMvvm.ViewModel<org.owntracks.android.ui.contacts.ContactsMvvm.View> {
    private final org.owntracks.android.data.repos.ContactsRepo contactsRepo = null;
    
    @java.lang.Override()
    public void attachView(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.Nullable()
    org.owntracks.android.ui.contacts.ContactsMvvm.View view) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, org.owntracks.android.model.FusedContact>> getContacts() {
        return null;
    }
    
    @javax.inject.Inject()
    public ContactsViewModel(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.data.repos.ContactsRepo contactsRepo) {
        super();
    }
}
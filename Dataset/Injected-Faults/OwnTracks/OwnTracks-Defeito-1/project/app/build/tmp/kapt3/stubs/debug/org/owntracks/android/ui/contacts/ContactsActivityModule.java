package org.owntracks.android.ui.contacts;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;
import org.owntracks.android.injection.scopes.PerActivity;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\u0018\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\'\u00a8\u0006\u000b"}, d2 = {"Lorg/owntracks/android/ui/contacts/ContactsActivityModule;", "", "()V", "bindActivity", "Landroidx/appcompat/app/AppCompatActivity;", "a", "Lorg/owntracks/android/ui/contacts/ContactsActivity;", "bindViewModel", "Lorg/owntracks/android/ui/contacts/ContactsMvvm$ViewModel;", "viewModel", "Lorg/owntracks/android/ui/contacts/ContactsViewModel;", "app_debug"})
@dagger.Module()
public abstract class ContactsActivityModule {
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerActivity()
    @dagger.Binds()
    public abstract androidx.appcompat.app.AppCompatActivity bindActivity(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.ui.contacts.ContactsActivity a);
    
    @org.jetbrains.annotations.Nullable()
    @dagger.Binds()
    public abstract org.owntracks.android.ui.contacts.ContactsMvvm.ViewModel<?> bindViewModel(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.ui.contacts.ContactsViewModel viewModel);
    
    public ContactsActivityModule() {
        super();
    }
}
package org.owntracks.android.ui.contacts;

import androidx.lifecycle.MutableLiveData;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u00a8\u0006\u0004"}, d2 = {"Lorg/owntracks/android/ui/contacts/ContactsMvvm;", "", "View", "ViewModel", "app_debug"})
public abstract interface ContactsMvvm {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001\u00a8\u0006\u0002"}, d2 = {"Lorg/owntracks/android/ui/contacts/ContactsMvvm$View;", "Lorg/owntracks/android/ui/base/view/MvvmView;", "app_debug"})
    public static abstract interface View extends org.owntracks.android.ui.base.view.MvvmView {
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\n\b\u0000\u0010\u0001*\u0004\u0018\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003R$\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/owntracks/android/ui/contacts/ContactsMvvm$ViewModel;", "V", "Lorg/owntracks/android/ui/base/view/MvvmView;", "Lorg/owntracks/android/ui/base/viewmodel/MvvmViewModel;", "contacts", "Landroidx/lifecycle/MutableLiveData;", "", "", "Lorg/owntracks/android/model/FusedContact;", "getContacts", "()Landroidx/lifecycle/MutableLiveData;", "app_debug"})
    public static abstract interface ViewModel<V extends org.owntracks.android.ui.base.view.MvvmView> extends org.owntracks.android.ui.base.viewmodel.MvvmViewModel<V> {
        
        @org.jetbrains.annotations.NotNull()
        public abstract androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, org.owntracks.android.model.FusedContact>> getContacts();
    }
}
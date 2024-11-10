package org.owntracks.android.data.repos;

import androidx.lifecycle.MutableLiveData;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.model.messages.MessageCard;
import org.owntracks.android.model.messages.MessageLocation;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u0005H&J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005H&J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H&J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H&R$\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lorg/owntracks/android/data/repos/ContactsRepo;", "", "all", "Landroidx/lifecycle/MutableLiveData;", "", "", "Lorg/owntracks/android/model/FusedContact;", "getAll", "()Landroidx/lifecycle/MutableLiveData;", "revision", "", "getRevision", "()J", "clearAll", "", "getById", "id", "remove", "update", "messageCard", "Lorg/owntracks/android/model/messages/MessageCard;", "messageLocation", "Lorg/owntracks/android/model/messages/MessageLocation;", "app_debug"})
public abstract interface ContactsRepo {
    
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, org.owntracks.android.model.FusedContact>> getAll();
    
    @org.jetbrains.annotations.Nullable()
    public abstract org.owntracks.android.model.FusedContact getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    public abstract void clearAll();
    
    public abstract void remove(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    public abstract void update(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageLocation messageLocation);
    
    public abstract void update(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageCard messageCard);
    
    public abstract long getRevision();
}
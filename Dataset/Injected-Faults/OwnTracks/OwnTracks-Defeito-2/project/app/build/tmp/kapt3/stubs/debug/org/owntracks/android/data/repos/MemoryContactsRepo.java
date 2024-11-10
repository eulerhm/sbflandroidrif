package org.owntracks.android.data.repos;

import androidx.annotation.MainThread;
import androidx.lifecycle.MutableLiveData;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import javax.inject.Singleton;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.model.messages.MessageCard;
import org.owntracks.android.model.messages.MessageLocation;
import org.owntracks.android.support.ContactImageProvider;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0017J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u00020\nH\u0016J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0007J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001cH\u0007J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u000bH\u0002J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\nH\u0016J\u0018\u0010 \u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010!\u001a\u00020\"H\u0016J\u0018\u0010 \u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010#\u001a\u00020$H\u0016R&\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u00020\u000f8VX\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006&"}, d2 = {"Lorg/owntracks/android/data/repos/MemoryContactsRepo;", "Lorg/owntracks/android/data/repos/ContactsRepo;", "eventBus", "Lorg/greenrobot/eventbus/EventBus;", "contactImageProvider", "Lorg/owntracks/android/support/ContactImageProvider;", "(Lorg/greenrobot/eventbus/EventBus;Lorg/owntracks/android/support/ContactImageProvider;)V", "all", "Landroidx/lifecycle/MutableLiveData;", "", "", "Lorg/owntracks/android/model/FusedContact;", "getAll", "()Landroidx/lifecycle/MutableLiveData;", "majorRevision", "", "revision", "getRevision", "()J", "setRevision", "(J)V", "clearAll", "", "getById", "id", "onEventMainThread", "e", "Lorg/owntracks/android/support/Events$EndpointChanged;", "Lorg/owntracks/android/support/Events$ModeChanged;", "put", "contact", "remove", "update", "messageCard", "Lorg/owntracks/android/model/messages/MessageCard;", "messageLocation", "Lorg/owntracks/android/model/messages/MessageLocation;", "Companion", "app_debug"})
@javax.inject.Singleton()
public final class MemoryContactsRepo implements org.owntracks.android.data.repos.ContactsRepo {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, org.owntracks.android.model.FusedContact>> all = null;
    private long majorRevision = 0L;
    private long revision = 0L;
    private final org.greenrobot.eventbus.EventBus eventBus = null;
    private final org.owntracks.android.support.ContactImageProvider contactImageProvider = null;
    public static final long MAJOR_STEP = 1000000L;
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.data.repos.MemoryContactsRepo.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, org.owntracks.android.model.FusedContact>> getAll() {
        return null;
    }
    
    @java.lang.Override()
    public long getRevision() {
        return 0L;
    }
    
    public void setRevision(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public org.owntracks.android.model.FusedContact getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    private final synchronized void put(java.lang.String id, org.owntracks.android.model.FusedContact contact) {
    }
    
    @androidx.annotation.MainThread()
    @java.lang.Override()
    public synchronized void clearAll() {
    }
    
    @java.lang.Override()
    public synchronized void remove(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @java.lang.Override()
    public void update(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageCard messageCard) {
    }
    
    @java.lang.Override()
    public synchronized void update(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageLocation messageLocation) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventMainThread(@org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"UNUSED_PARAMETER"})
    org.owntracks.android.support.Events.ModeChanged e) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventMainThread(@org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"UNUSED_PARAMETER"})
    org.owntracks.android.support.Events.EndpointChanged e) {
    }
    
    @javax.inject.Inject()
    public MemoryContactsRepo(@org.jetbrains.annotations.NotNull()
    org.greenrobot.eventbus.EventBus eventBus, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.ContactImageProvider contactImageProvider) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/data/repos/MemoryContactsRepo$Companion;", "", "()V", "MAJOR_STEP", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
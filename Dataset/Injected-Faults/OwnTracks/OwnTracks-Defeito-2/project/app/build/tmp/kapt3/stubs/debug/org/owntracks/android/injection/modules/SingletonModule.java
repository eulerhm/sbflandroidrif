package org.owntracks.android.injection.modules;

import androidx.test.espresso.idling.CountingIdlingResource;
import dagger.Module;
import dagger.Provides;
import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.EventBusIndex;
import org.owntracks.android.data.repos.ContactsRepo;
import org.owntracks.android.data.repos.MemoryContactsRepo;
import org.owntracks.android.support.ContactImageProvider;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\b\u0010\t\u001a\u00020\u0006H\u0007J\b\u0010\n\u001a\u00020\u000bH\u0007\u00a8\u0006\f"}, d2 = {"Lorg/owntracks/android/injection/modules/SingletonModule;", "", "()V", "provideContactsRepo", "Lorg/owntracks/android/data/repos/ContactsRepo;", "eventBus", "Lorg/greenrobot/eventbus/EventBus;", "contactImageProvider", "Lorg/owntracks/android/support/ContactImageProvider;", "provideEventbus", "provideOutgoingQueueIdlingResource", "Landroidx/test/espresso/idling/CountingIdlingResource;", "app_debug"})
@dagger.Module()
public final class SingletonModule {
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.greenrobot.eventbus.EventBus provideEventbus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.owntracks.android.data.repos.ContactsRepo provideContactsRepo(@org.jetbrains.annotations.Nullable()
    org.greenrobot.eventbus.EventBus eventBus, @org.jetbrains.annotations.Nullable()
    org.owntracks.android.support.ContactImageProvider contactImageProvider) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final androidx.test.espresso.idling.CountingIdlingResource provideOutgoingQueueIdlingResource() {
        return null;
    }
    
    public SingletonModule() {
        super();
    }
}
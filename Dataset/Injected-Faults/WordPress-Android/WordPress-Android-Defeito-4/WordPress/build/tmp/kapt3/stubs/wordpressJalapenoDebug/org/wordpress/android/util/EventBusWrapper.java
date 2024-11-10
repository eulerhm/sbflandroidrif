package org.wordpress.android.util;

import java.lang.System;

/**
 * Provides an interface for [org.greenrobot.eventbus.EventBus] which can be mocked and used in unit tests.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0001J\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001J\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0001\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/util/EventBusWrapper;", "", "()V", "isRegistered", "", "subscriber", "post", "", "event", "postSticky", "register", "removeStickyEvent", "unregister", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class EventBusWrapper {
    
    @javax.inject.Inject()
    public EventBusWrapper() {
        super();
    }
    
    public final void register(@org.jetbrains.annotations.NotNull()
    java.lang.Object subscriber) {
    }
    
    public final void unregister(@org.jetbrains.annotations.NotNull()
    java.lang.Object subscriber) {
    }
    
    public final void removeStickyEvent(@org.jetbrains.annotations.NotNull()
    java.lang.Object event) {
    }
    
    public final boolean isRegistered(@org.jetbrains.annotations.NotNull()
    java.lang.Object subscriber) {
        return false;
    }
    
    public final void postSticky(@org.jetbrains.annotations.NotNull()
    java.lang.Object event) {
    }
    
    public final void post(@org.jetbrains.annotations.NotNull()
    java.lang.Object event) {
    }
}
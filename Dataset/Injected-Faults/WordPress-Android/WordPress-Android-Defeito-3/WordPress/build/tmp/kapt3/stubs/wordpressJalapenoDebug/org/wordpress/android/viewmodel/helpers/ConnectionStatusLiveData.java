package org.wordpress.android.viewmodel.helpers;

import java.lang.System;

/**
 * A LiveData instance that can be injected to keep track of the network availability.
 *
 * Use [Factory] to create an instance. The Factory guarantees that this only emits if the network availability
 * changes and not when the user switches between cellular and wi-fi.
 *
 * IMPORTANT: It needs to be observed for the changes to be posted.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/viewmodel/helpers/ConnectionStatusLiveData;", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/helpers/ConnectionStatus;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "networkReceiver", "Landroid/content/BroadcastReceiver;", "onActive", "", "onInactive", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ConnectionStatusLiveData extends androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ConnectionStatus> {
    private final android.content.Context context = null;
    private final android.content.BroadcastReceiver networkReceiver = null;
    
    private ConnectionStatusLiveData(android.content.Context context) {
        super(null);
    }
    
    @java.lang.Override()
    protected void onActive() {
    }
    
    @java.lang.Override()
    protected void onInactive() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/viewmodel/helpers/ConnectionStatusLiveData$Factory;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/helpers/ConnectionStatus;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory {
        private final android.content.Context context = null;
        
        @javax.inject.Inject()
        public Factory(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.helpers.ConnectionStatus> create() {
            return null;
        }
    }
}
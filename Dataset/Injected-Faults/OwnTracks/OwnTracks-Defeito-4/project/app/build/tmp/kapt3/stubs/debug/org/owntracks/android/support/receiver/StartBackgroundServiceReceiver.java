package org.owntracks.android.support.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import dagger.android.DaggerBroadcastReceiver;
import org.owntracks.android.services.BackgroundService;
import org.owntracks.android.support.Preferences;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/owntracks/android/support/receiver/StartBackgroundServiceReceiver;", "Ldagger/android/DaggerBroadcastReceiver;", "()V", "preferences", "Lorg/owntracks/android/support/Preferences;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_debug"})
public final class StartBackgroundServiceReceiver extends dagger.android.DaggerBroadcastReceiver {
    @org.jetbrains.annotations.Nullable()
    @javax.inject.Inject()
    public org.owntracks.android.support.Preferences preferences;
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    public StartBackgroundServiceReceiver() {
        super();
    }
}
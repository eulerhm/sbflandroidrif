package com.google.android.gnd.system;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0003J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/google/android/gnd/system/NotificationManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "createNotificationChannels", "", "createSyncNotification", "Landroid/app/Notification;", "state", "Lcom/google/android/gnd/persistence/remote/TransferProgress$UploadState;", "title", "", "total", "", "progress", "gnd_debug"})
@javax.inject.Singleton()
public final class NotificationManager {
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public NotificationManager(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        super();
    }
    
    @androidx.annotation.RequiresApi(api = android.os.Build.VERSION_CODES.O)
    private final void createNotificationChannels(android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification createSyncNotification(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.remote.TransferProgress.UploadState state, @org.jetbrains.annotations.NotNull()
    java.lang.String title, int total, int progress) {
        return null;
    }
}
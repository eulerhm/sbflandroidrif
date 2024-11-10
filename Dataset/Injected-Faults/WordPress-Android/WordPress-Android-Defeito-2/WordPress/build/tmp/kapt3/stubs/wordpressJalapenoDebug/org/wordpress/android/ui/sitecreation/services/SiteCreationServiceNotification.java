package org.wordpress.android.ui.sitecreation.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceNotification;", "", "()V", "channelResId", "", "colorResId", "drawableResId", "createCreatingSiteNotification", "Landroid/app/Notification;", "context", "Landroid/content/Context;", "createFailureNotification", "createSuccessNotification", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteCreationServiceNotification {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.sitecreation.services.SiteCreationServiceNotification INSTANCE = null;
    private static final int channelResId = org.wordpress.android.R.string.notification_channel_normal_id;
    private static final int colorResId = org.wordpress.android.R.color.primary_50;
    private static final int drawableResId = org.wordpress.android.R.drawable.ic_app_white_24dp;
    
    private SiteCreationServiceNotification() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification createCreatingSiteNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification createSuccessNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification createFailureNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}
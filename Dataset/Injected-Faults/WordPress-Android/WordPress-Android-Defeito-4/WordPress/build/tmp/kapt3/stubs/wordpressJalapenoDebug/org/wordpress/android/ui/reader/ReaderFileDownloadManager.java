package org.wordpress.android.ui.reader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/reader/ReaderFileDownloadManager;", "Landroid/content/BroadcastReceiver;", "authenticationUtils", "Lorg/wordpress/android/ui/utils/AuthenticationUtils;", "downloadManager", "Lorg/wordpress/android/ui/utils/DownloadManagerWrapper;", "(Lorg/wordpress/android/ui/utils/AuthenticationUtils;Lorg/wordpress/android/ui/utils/DownloadManagerWrapper;)V", "downloadFile", "", "fileUrl", "", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "openDownloadedAttachment", "downloadId", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderFileDownloadManager extends android.content.BroadcastReceiver {
    private final org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils = null;
    private final org.wordpress.android.ui.utils.DownloadManagerWrapper downloadManager = null;
    
    @javax.inject.Inject()
    public ReaderFileDownloadManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.DownloadManagerWrapper downloadManager) {
        super();
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    public final void downloadFile(@org.jetbrains.annotations.NotNull()
    java.lang.String fileUrl) {
    }
    
    private final void openDownloadedAttachment(android.content.Context context, long downloadId) {
    }
}
package org.wordpress.android.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\nJ\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0006J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/utils/DownloadManagerWrapper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "buildQuery", "Landroid/app/DownloadManager$Query;", "buildRequest", "Landroid/app/DownloadManager$Request;", "fileUrl", "", "downloadManager", "Landroid/app/DownloadManager;", "enqueue", "", "request", "getMimeType", "url", "guessUrl", "openDownloadedAttachment", "", "attachmentMimeType", "query", "Landroid/database/Cursor;", "toPublicUri", "Landroid/net/Uri;", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class DownloadManagerWrapper {
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public DownloadManagerWrapper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final long enqueue(@org.jetbrains.annotations.NotNull()
    android.app.DownloadManager.Request request) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.DownloadManager.Request buildRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String fileUrl) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.database.Cursor query(@org.jetbrains.annotations.NotNull()
    android.app.DownloadManager.Query query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.DownloadManager.Query buildQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String guessUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String fileUrl) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMimeType(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    private final android.net.Uri toPublicUri(java.lang.String fileUrl) {
        return null;
    }
    
    public final void openDownloadedAttachment(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String fileUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String attachmentMimeType) {
    }
    
    private final android.app.DownloadManager downloadManager() {
        return null;
    }
}
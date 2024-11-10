package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/UploadWorker;", "Landroidx/work/Worker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "uploadStarter", "Lorg/wordpress/android/ui/uploads/UploadStarter;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lorg/wordpress/android/ui/uploads/UploadStarter;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "Companion", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UploadWorker extends androidx.work.Worker {
    private final org.wordpress.android.ui.uploads.UploadStarter uploadStarter = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.UploadWorker.Companion Companion = null;
    private static final int UPLOAD_FROM_ALL_SITES = -1;
    
    public UploadWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters workerParams, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadStarter uploadStarter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.work.ListenableWorker.Result doWork() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/UploadWorker$Factory;", "Landroidx/work/WorkerFactory;", "uploadStarter", "Lorg/wordpress/android/ui/uploads/UploadStarter;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lorg/wordpress/android/ui/uploads/UploadStarter;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "createWorker", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory extends androidx.work.WorkerFactory {
        private final org.wordpress.android.ui.uploads.UploadStarter uploadStarter = null;
        private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.uploads.UploadStarter uploadStarter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.SiteStore siteStore) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public androidx.work.ListenableWorker createWorker(@org.jetbrains.annotations.NotNull()
        android.content.Context appContext, @org.jetbrains.annotations.NotNull()
        java.lang.String workerClassName, @org.jetbrains.annotations.NotNull()
        androidx.work.WorkerParameters workerParameters) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/util/UploadWorker$Companion;", "", "()V", "UPLOAD_FROM_ALL_SITES", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
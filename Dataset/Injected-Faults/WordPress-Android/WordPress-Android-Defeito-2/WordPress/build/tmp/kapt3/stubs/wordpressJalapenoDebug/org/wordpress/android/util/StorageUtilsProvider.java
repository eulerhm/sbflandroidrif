package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/StorageUtilsProvider;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "isDeviceRunningOutOfSpace", "", "notifyOnLowStorageSpace", "", "fm", "Landroidx/fragment/app/FragmentManager;", "source", "Lorg/wordpress/android/util/StorageUtilsProvider$Source;", "Companion", "Source", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class StorageUtilsProvider {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.StorageUtilsProvider.Companion Companion = null;
    private static final java.lang.String DIALOG_FRAGMENT_TAG = "storage-utils-dialog-fragment";
    private static final int FULL_STORAGE_PERCENTAGE = 100;
    private static final int LOW_STORAGE_THRESHOLD = 10;
    
    @javax.inject.Inject()
    public StorageUtilsProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    public final void notifyOnLowStorageSpace(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fm, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.StorageUtilsProvider.Source source) {
    }
    
    private final boolean isDeviceRunningOutOfSpace() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/util/StorageUtilsProvider$Source;", "", "description", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "EDITOR", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Source {
        /*public static final*/ EDITOR /* = new EDITOR(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String description = null;
        
        Source(java.lang.String description) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescription() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/util/StorageUtilsProvider$Companion;", "", "()V", "DIALOG_FRAGMENT_TAG", "", "FULL_STORAGE_PERCENTAGE", "", "LOW_STORAGE_THRESHOLD", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
package org.wordpress.android.ui.photopicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\b\u0010\b\u001a\u00020\u0006H\u0002J\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PermissionsHandler;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "hasCameraPermission", "", "hasPermissionsToAccessPhotos", "hasReadStoragePermission", "hasStoragePermission", "hasWriteStoragePermission", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PermissionsHandler {
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public PermissionsHandler(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean hasPermissionsToAccessPhotos() {
        return false;
    }
    
    public final boolean hasStoragePermission() {
        return false;
    }
    
    public final boolean hasWriteStoragePermission() {
        return false;
    }
    
    private final boolean hasReadStoragePermission() {
        return false;
    }
    
    private final boolean hasCameraPermission() {
        return false;
    }
}
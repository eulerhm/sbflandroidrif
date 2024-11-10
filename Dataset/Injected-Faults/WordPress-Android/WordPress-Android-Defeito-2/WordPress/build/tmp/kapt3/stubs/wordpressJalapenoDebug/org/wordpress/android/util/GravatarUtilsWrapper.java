package org.wordpress.android.util;

import java.lang.System;

/**
 * Injectable wrapper around GravatarUtils.
 *
 * GravatarUtils interface is consisted of static methods, which makes the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/util/GravatarUtilsWrapper;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fixGravatarUrl", "", "imageUrl", "avatarSz", "", "fixGravatarUrlWithResource", "avatarSzRes", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class GravatarUtilsWrapper {
    private final android.content.Context appContext = null;
    
    @javax.inject.Inject()
    public GravatarUtilsWrapper(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fixGravatarUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, int avatarSz) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fixGravatarUrlWithResource(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, @androidx.annotation.DimenRes()
    int avatarSzRes) {
        return null;
    }
}
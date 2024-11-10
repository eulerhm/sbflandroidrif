package org.wordpress.android.util;

import java.lang.System;

/**
 * Injectable wrapper around FluxCUtilsWrapper.
 *
 * FluxCUtilsWrapper interface is consisted of static methods, which make the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\"\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/util/FluxCUtilsWrapper;", "", "appContext", "Landroid/content/Context;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "(Landroid/content/Context;Lorg/wordpress/android/fluxc/store/MediaStore;)V", "mediaFileFromMediaModel", "Lorg/wordpress/android/util/helpers/MediaFile;", "mediaModel", "Lorg/wordpress/android/fluxc/model/MediaModel;", "mediaModelFromLocalUri", "uri", "Landroid/net/Uri;", "mimeType", "", "localSiteId", "", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class FluxCUtilsWrapper {
    private final android.content.Context appContext = null;
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    
    @javax.inject.Inject()
    public FluxCUtilsWrapper(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.MediaModel mediaModelFromLocalUri(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.Nullable()
    java.lang.String mimeType, int localSiteId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.util.helpers.MediaFile mediaFileFromMediaModel(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.MediaModel mediaModel) {
        return null;
    }
}
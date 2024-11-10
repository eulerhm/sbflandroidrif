package org.wordpress.android.util;

import java.lang.System;

/**
 * Injectable wrapper around UrlUtils.
 *
 * UrlUtils interface is consisted of static methods, which makes the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J\u0010\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\r\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/UrlUtilsWrapper;", "", "()V", "addUrlSchemeIfNeeded", "", "url", "addHttps", "", "extractSubDomain", "domain", "getHost", "urlString", "isImageUrl", "isValidUrlAndHostNotNull", "removeScheme", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class UrlUtilsWrapper {
    
    @javax.inject.Inject()
    public UrlUtilsWrapper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String extractSubDomain(@org.jetbrains.annotations.NotNull()
    java.lang.String domain) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String addUrlSchemeIfNeeded(@org.jetbrains.annotations.NotNull()
    java.lang.String url, boolean addHttps) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHost(@org.jetbrains.annotations.Nullable()
    java.lang.String urlString) {
        return null;
    }
    
    public final boolean isValidUrlAndHostNotNull(@org.jetbrains.annotations.Nullable()
    java.lang.String urlString) {
        return false;
    }
    
    public final boolean isImageUrl(@org.jetbrains.annotations.Nullable()
    java.lang.String urlString) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String removeScheme(@org.jetbrains.annotations.NotNull()
    java.lang.String urlString) {
        return null;
    }
}
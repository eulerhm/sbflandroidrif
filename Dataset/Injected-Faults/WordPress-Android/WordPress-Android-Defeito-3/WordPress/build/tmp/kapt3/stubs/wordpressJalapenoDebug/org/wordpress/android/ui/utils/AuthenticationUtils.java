package org.wordpress.android.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/utils/AuthenticationUtils;", "", "accessToken", "Lorg/wordpress/android/fluxc/network/rest/wpcom/auth/AccessToken;", "httpAuthManager", "Lorg/wordpress/android/fluxc/network/HTTPAuthManager;", "userAgent", "Lorg/wordpress/android/fluxc/network/UserAgent;", "privateAtomicCookie", "Lorg/wordpress/android/fluxc/network/rest/wpcom/site/PrivateAtomicCookie;", "(Lorg/wordpress/android/fluxc/network/rest/wpcom/auth/AccessToken;Lorg/wordpress/android/fluxc/network/HTTPAuthManager;Lorg/wordpress/android/fluxc/network/UserAgent;Lorg/wordpress/android/fluxc/network/rest/wpcom/site/PrivateAtomicCookie;)V", "getAuthHeaders", "", "", "url", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class AuthenticationUtils {
    private final org.wordpress.android.fluxc.network.rest.wpcom.auth.AccessToken accessToken = null;
    private final org.wordpress.android.fluxc.network.HTTPAuthManager httpAuthManager = null;
    private final org.wordpress.android.fluxc.network.UserAgent userAgent = null;
    private final org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie privateAtomicCookie = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.utils.AuthenticationUtils.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AUTHORIZATION_HEADER_NAME = "Authorization";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COOKIE_HEADER_NAME = "Cookie";
    
    @javax.inject.Inject()
    public AuthenticationUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.rest.wpcom.auth.AccessToken accessToken, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.HTTPAuthManager httpAuthManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.UserAgent userAgent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie privateAtomicCookie) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> getAuthHeaders(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/utils/AuthenticationUtils$Companion;", "", "()V", "AUTHORIZATION_HEADER_NAME", "", "COOKIE_HEADER_NAME", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
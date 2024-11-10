package org.wordpress.android.ui.deeplinks;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0014\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\bH\u0002J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "uriUtilsWrapper", "Lorg/wordpress/android/util/UriUtilsWrapper;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/util/UriUtilsWrapper;)V", "blogIdToSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "blogId", "", "extractHostFromSite", "site", "extractSiteModelFromTargetHost", "", "host", "getRedirectUri", "Lorg/wordpress/android/util/UriWrapper;", "uri", "hostToSite", "siteUrl", "isTrackingUrl", "", "isWpLoginUrl", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DeepLinkUriUtils {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.util.UriUtilsWrapper uriUtilsWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.deeplinks.DeepLinkUriUtils.Companion Companion = null;
    private static final java.lang.String HOST_API_WORDPRESS_COM = "public-api.wordpress.com";
    private static final java.lang.String MOBILE_TRACKING_PATH = "mbar";
    private static final java.lang.String WP_LOGIN = "wp-login.php";
    private static final java.lang.String REDIRECT_TO_PARAM = "redirect_to";
    
    @javax.inject.Inject()
    public DeepLinkUriUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriUtilsWrapper uriUtilsWrapper) {
        super();
    }
    
    private final java.lang.String extractHostFromSite(org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.util.UriWrapper getRedirectUri(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.SiteModel> extractSiteModelFromTargetHost(java.lang.String host) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.SiteModel hostToSite(@org.jetbrains.annotations.NotNull()
    java.lang.String siteUrl) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.SiteModel blogIdToSite(@org.jetbrains.annotations.NotNull()
    java.lang.String blogId) {
        return null;
    }
    
    /**
     * Tracking URIs like `public-api.wordpress.com/mbar/...` come from emails and should be handled here
     */
    public final boolean isTrackingUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return false;
    }
    
    public final boolean isWpLoginUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils$Companion;", "", "()V", "HOST_API_WORDPRESS_COM", "", "MOBILE_TRACKING_PATH", "REDIRECT_TO_PARAM", "WP_LOGIN", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
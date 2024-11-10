package org.wordpress.android.ui.deeplinks.handlers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\fH\u0002J\u000e\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/StatsLinkHandler;", "Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandler;", "deepLinkUriUtils", "Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;", "(Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;)V", "buildNavigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "shouldHandleUrl", "", "stripUrl", "", "toSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "toStatsTimeframe", "Lorg/wordpress/android/ui/stats/StatsTimeframe;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsLinkHandler implements org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandler {
    private final org.wordpress.android.ui.deeplinks.DeepLinkUriUtils deepLinkUriUtils = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.deeplinks.handlers.StatsLinkHandler.Companion Companion = null;
    private static final java.lang.String STATS_PATH = "stats";
    
    @javax.inject.Inject()
    public StatsLinkHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.DeepLinkUriUtils deepLinkUriUtils) {
        super();
    }
    
    /**
     * Builds navigate action from URL like:
     * https://wordpress.com/stats/$timeframe/$site
     * where timeframe and site are optional
     * or
     * wordpress://stats
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction buildNavigateAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    /**
     * Returns true if the URI should be handled by StatsLinkHandler.
     * The handled links are `https://wordpress.com/stats/day/$site` and `wordpress://stats`
     */
    @java.lang.Override()
    public boolean shouldHandleUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String stripUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    /**
     * Converts HOST name of a site to SiteModel. It finds the Site in the current local sites and matches the name
     * to the host.
     */
    private final org.wordpress.android.fluxc.model.SiteModel toSite(java.lang.String $this$toSite) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.StatsTimeframe toStatsTimeframe(java.lang.String $this$toStatsTimeframe) {
        return null;
    }
    
    /**
     * Toast messages emitted from the handler
     */
    @org.jetbrains.annotations.Nullable()
    public androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/StatsLinkHandler$Companion;", "", "()V", "STATS_PATH", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
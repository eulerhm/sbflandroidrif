package org.wordpress.android.ui.deeplinks.handlers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J,\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u001bH\u0016J\u000e\u0010\u001c\u001a\u0004\u0018\u00010\u0014*\u00020\u0019H\u0002J\u000e\u0010\u001d\u001a\u0004\u0018\u00010\u0014*\u00020\u0019H\u0002J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u0016*\u00020\u00192\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/EditorLinkHandler;", "Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandler;", "deepLinkUriUtils", "Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "(Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;Lorg/wordpress/android/fluxc/store/PostStore;)V", "_toast", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "buildNavigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "openEditorForSiteAndPost", "hasSiteParam", "", "hasPostParam", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "shouldHandleUrl", "stripUrl", "", "toast", "Landroidx/lifecycle/LiveData;", "blogIdToSite", "hostNameToSite", "toPost", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class EditorLinkHandler implements org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandler {
    private final org.wordpress.android.ui.deeplinks.DeepLinkUriUtils deepLinkUriUtils = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> _toast = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.deeplinks.handlers.EditorLinkHandler.Companion Companion = null;
    private static final java.lang.String POST_PATH = "post";
    private static final java.lang.String BLOG_ID = "blogId";
    private static final java.lang.String POST_ID = "postId";
    
    @javax.inject.Inject()
    public EditorLinkHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.DeepLinkUriUtils deepLinkUriUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast() {
        return null;
    }
    
    /**
     * Builds navigate action from URL like:
     * https://wordpress.com/post/siteNameOrUrl/postId
     * where siteNameOrUrl and postID are optional
     * or App links like wordpress://post?blogId=798&postId=1231
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction buildNavigateAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    /**
     * Returns true if the URI should be handled by EditorLinkHandler.
     * The handled links are `wordpress.com/post...1
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
     * Converts BlogID if long or Site URL for other cases to a SiteModel
     */
    private final org.wordpress.android.fluxc.model.SiteModel blogIdToSite(java.lang.String $this$blogIdToSite) {
        return null;
    }
    
    /**
     * Converts HOST name of a site to SiteModel. It finds the Site in the current local sites and matches the name
     * to the host.
     */
    private final org.wordpress.android.fluxc.model.SiteModel hostNameToSite(java.lang.String $this$hostNameToSite) {
        return null;
    }
    
    /**
     * Converts the post ID in String to the local PostModel (if available).
     */
    private final org.wordpress.android.fluxc.model.PostModel toPost(java.lang.String $this$toPost, org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    private final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction openEditorForSiteAndPost(boolean hasSiteParam, boolean hasPostParam, org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.fluxc.model.PostModel post) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/EditorLinkHandler$Companion;", "", "()V", "BLOG_ID", "", "POST_ID", "POST_PATH", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
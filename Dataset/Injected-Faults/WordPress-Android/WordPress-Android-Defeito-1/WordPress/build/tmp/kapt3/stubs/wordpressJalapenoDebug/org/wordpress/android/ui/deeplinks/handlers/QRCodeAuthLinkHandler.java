package org.wordpress.android.ui.deeplinks.handlers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/QRCodeAuthLinkHandler;", "Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandler;", "()V", "buildNavigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "shouldHandleUrl", "", "stripUrl", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QRCodeAuthLinkHandler implements org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandler {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.deeplinks.handlers.QRCodeAuthLinkHandler.Companion Companion = null;
    private static final java.lang.String GET_PATH = "get";
    private static final java.lang.String HOST_APPS_WORDPRESS_COM = "apps.wordpress.com";
    
    @javax.inject.Inject()
    public QRCodeAuthLinkHandler() {
        super();
    }
    
    /**
     * Returns true if the URI looks like `apps.wordpress.com/get`
     */
    @java.lang.Override()
    public boolean shouldHandleUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction buildNavigateAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String stripUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    /**
     * Toast messages emitted from the handler
     */
    @org.jetbrains.annotations.Nullable()
    public androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/QRCodeAuthLinkHandler$Companion;", "", "()V", "GET_PATH", "", "HOST_APPS_WORDPRESS_COM", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
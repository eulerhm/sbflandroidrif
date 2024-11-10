package org.wordpress.android.ui.deeplinks.handlers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\n\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0018\u00010\u000bH\u0016\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandler;", "", "buildNavigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "shouldHandleUrl", "", "stripUrl", "", "toast", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface DeepLinkHandler {
    
    /**
     * Returns true when the url is handled by this handler
     */
    public abstract boolean shouldHandleUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri);
    
    /**
     * Builds navigate action from the deep link
     */
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction buildNavigateAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri);
    
    /**
     * Strips all uri sensitive params for tracking purposes
     */
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String stripUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri);
    
    /**
     * Toast messages emitted from the handler
     */
    @org.jetbrains.annotations.Nullable()
    public abstract androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
        
        /**
         * Toast messages emitted from the handler
         */
        @org.jetbrains.annotations.Nullable()
        public static androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandler $this) {
            return null;
        }
    }
}
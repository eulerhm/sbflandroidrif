package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersManager;", "", "bloggingRemindersFeatureConfig", "Lorg/wordpress/android/util/config/BloggingRemindersFeatureConfig;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "(Lorg/wordpress/android/util/config/BloggingRemindersFeatureConfig;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "bloggingRemindersShown", "", "siteId", "", "shouldShowBloggingRemindersPrompt", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BloggingRemindersManager {
    private final org.wordpress.android.util.config.BloggingRemindersFeatureConfig bloggingRemindersFeatureConfig = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    
    @javax.inject.Inject()
    public BloggingRemindersManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.BloggingRemindersFeatureConfig bloggingRemindersFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper) {
        super();
    }
    
    public final boolean shouldShowBloggingRemindersPrompt(int siteId) {
        return false;
    }
    
    public final void bloggingRemindersShown(int siteId) {
    }
}
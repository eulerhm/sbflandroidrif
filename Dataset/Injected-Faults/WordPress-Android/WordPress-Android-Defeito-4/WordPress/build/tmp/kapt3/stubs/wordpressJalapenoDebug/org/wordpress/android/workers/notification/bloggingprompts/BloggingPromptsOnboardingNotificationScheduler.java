package org.wordpress.android.workers.notification.bloggingprompts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/workers/notification/bloggingprompts/BloggingPromptsOnboardingNotificationScheduler;", "", "localNotificationScheduler", "Lorg/wordpress/android/workers/notification/local/LocalNotificationScheduler;", "bloggingPromptsOnboardingNotificationHandler", "Lorg/wordpress/android/workers/notification/bloggingprompts/BloggingPromptsOnboardingNotificationHandler;", "bloggingPromptsFeatureConfig", "Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;", "(Lorg/wordpress/android/workers/notification/local/LocalNotificationScheduler;Lorg/wordpress/android/workers/notification/bloggingprompts/BloggingPromptsOnboardingNotificationHandler;Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;)V", "cancelBloggingPromptsOnboardingNotification", "", "scheduleBloggingPromptsOnboardingNotificationIfNeeded", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BloggingPromptsOnboardingNotificationScheduler {
    private final org.wordpress.android.workers.notification.local.LocalNotificationScheduler localNotificationScheduler = null;
    private final org.wordpress.android.workers.notification.bloggingprompts.BloggingPromptsOnboardingNotificationHandler bloggingPromptsOnboardingNotificationHandler = null;
    private final org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig = null;
    
    @javax.inject.Inject()
    public BloggingPromptsOnboardingNotificationScheduler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotificationScheduler localNotificationScheduler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.bloggingprompts.BloggingPromptsOnboardingNotificationHandler bloggingPromptsOnboardingNotificationHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig) {
        super();
    }
    
    public final void scheduleBloggingPromptsOnboardingNotificationIfNeeded() {
    }
    
    public final void cancelBloggingPromptsOnboardingNotification() {
    }
}
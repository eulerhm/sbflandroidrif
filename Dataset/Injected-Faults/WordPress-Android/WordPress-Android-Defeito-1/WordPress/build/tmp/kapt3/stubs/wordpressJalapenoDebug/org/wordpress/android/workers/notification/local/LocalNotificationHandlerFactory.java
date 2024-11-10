package org.wordpress.android.workers.notification.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotificationHandlerFactory;", "", "createSiteNotificationHandler", "Lorg/wordpress/android/workers/notification/createsite/CreateSiteNotificationHandler;", "bloggingPromptsOnboardingNotificationHandler", "Lorg/wordpress/android/workers/notification/bloggingprompts/BloggingPromptsOnboardingNotificationHandler;", "(Lorg/wordpress/android/workers/notification/createsite/CreateSiteNotificationHandler;Lorg/wordpress/android/workers/notification/bloggingprompts/BloggingPromptsOnboardingNotificationHandler;)V", "buildLocalNotificationHandler", "Lorg/wordpress/android/workers/notification/local/LocalNotificationHandler;", "type", "Lorg/wordpress/android/workers/notification/local/LocalNotification$Type;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LocalNotificationHandlerFactory {
    private final org.wordpress.android.workers.notification.createsite.CreateSiteNotificationHandler createSiteNotificationHandler = null;
    private final org.wordpress.android.workers.notification.bloggingprompts.BloggingPromptsOnboardingNotificationHandler bloggingPromptsOnboardingNotificationHandler = null;
    
    @javax.inject.Inject()
    public LocalNotificationHandlerFactory(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.createsite.CreateSiteNotificationHandler createSiteNotificationHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.bloggingprompts.BloggingPromptsOnboardingNotificationHandler bloggingPromptsOnboardingNotificationHandler) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.notification.local.LocalNotificationHandler buildLocalNotificationHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotification.Type type) {
        return null;
    }
}
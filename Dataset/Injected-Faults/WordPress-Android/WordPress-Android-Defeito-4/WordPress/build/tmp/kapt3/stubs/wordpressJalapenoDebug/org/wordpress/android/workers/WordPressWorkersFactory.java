package org.wordpress.android.workers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/workers/WordPressWorkersFactory;", "Landroidx/work/DelegatingWorkerFactory;", "uploadStarter", "Lorg/wordpress/android/ui/uploads/UploadStarter;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "localNotificationHandlerFactory", "Lorg/wordpress/android/workers/notification/local/LocalNotificationHandlerFactory;", "reminderScheduler", "Lorg/wordpress/android/workers/reminder/ReminderScheduler;", "reminderNotifier", "Lorg/wordpress/android/workers/reminder/ReminderNotifier;", "weeklyRoundupNotifier", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;", "promptReminderNotifier", "Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier;", "(Lorg/wordpress/android/ui/uploads/UploadStarter;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/workers/notification/local/LocalNotificationHandlerFactory;Lorg/wordpress/android/workers/reminder/ReminderScheduler;Lorg/wordpress/android/workers/reminder/ReminderNotifier;Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WordPressWorkersFactory extends androidx.work.DelegatingWorkerFactory {
    
    @javax.inject.Inject()
    public WordPressWorkersFactory(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadStarter uploadStarter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotificationHandlerFactory localNotificationHandlerFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.ReminderScheduler reminderScheduler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.ReminderNotifier reminderNotifier, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier weeklyRoundupNotifier, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.prompt.PromptReminderNotifier promptReminderNotifier) {
        super();
    }
}
package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/posts/SavePostToDbUseCase;", "", "uploadUtils", "Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "dateTimeUtils", "Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "pendingDraftsNotificationsUtils", "Lorg/wordpress/android/ui/notifications/utils/PendingDraftsNotificationsUtilsWrapper;", "context", "Landroid/content/Context;", "(Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;Lorg/wordpress/android/util/DateTimeUtilsWrapper;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/notifications/utils/PendingDraftsNotificationsUtilsWrapper;Landroid/content/Context;)V", "handlePendingDraftNotifications", "", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "savePostToDb", "postRepository", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SavePostToDbUseCase {
    private final org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtils = null;
    private final org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtils = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.notifications.utils.PendingDraftsNotificationsUtilsWrapper pendingDraftsNotificationsUtils = null;
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public SavePostToDbUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.utils.PendingDraftsNotificationsUtilsWrapper pendingDraftsNotificationsUtils, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void savePostToDb(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository postRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void handlePendingDraftNotifications(org.wordpress.android.ui.posts.EditPostRepository editPostRepository) {
    }
}
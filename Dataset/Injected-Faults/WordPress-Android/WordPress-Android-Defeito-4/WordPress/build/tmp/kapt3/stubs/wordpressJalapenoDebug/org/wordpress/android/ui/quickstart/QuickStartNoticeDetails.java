package org.wordpress.android.ui.quickstart;

import java.lang.System;

/**
 * Static data that represents info that goes into Quick Start notices
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\b\u0086\u0001\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B#\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartNoticeDetails;", "", "taskString", "", "titleResId", "", "messageResId", "(Ljava/lang/String;ILjava/lang/String;II)V", "getMessageResId", "()I", "getTaskString", "()Ljava/lang/String;", "getTitleResId", "UPDATE_SITE_TITLE", "VIEW_SITE_TUTORIAL", "SHARE_SITE_TUTORIAL", "PUBLISH_POST_TUTORIAL", "FOLLOW_SITES_TUTORIAL", "UPLOAD_SITE_ICON", "CHECK_STATS", "REVIEW_PAGES", "CHECK_NOTIFICATIONS", "UPLOAD_MEDIA", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public enum QuickStartNoticeDetails {
    /*public static final*/ UPDATE_SITE_TITLE /* = new UPDATE_SITE_TITLE(null, 0, 0) */,
    /*public static final*/ VIEW_SITE_TUTORIAL /* = new VIEW_SITE_TUTORIAL(null, 0, 0) */,
    /*public static final*/ SHARE_SITE_TUTORIAL /* = new SHARE_SITE_TUTORIAL(null, 0, 0) */,
    /*public static final*/ PUBLISH_POST_TUTORIAL /* = new PUBLISH_POST_TUTORIAL(null, 0, 0) */,
    /*public static final*/ FOLLOW_SITES_TUTORIAL /* = new FOLLOW_SITES_TUTORIAL(null, 0, 0) */,
    /*public static final*/ UPLOAD_SITE_ICON /* = new UPLOAD_SITE_ICON(null, 0, 0) */,
    /*public static final*/ CHECK_STATS /* = new CHECK_STATS(null, 0, 0) */,
    /*public static final*/ REVIEW_PAGES /* = new REVIEW_PAGES(null, 0, 0) */,
    /*public static final*/ CHECK_NOTIFICATIONS /* = new CHECK_NOTIFICATIONS(null, 0, 0) */,
    /*public static final*/ UPLOAD_MEDIA /* = new UPLOAD_MEDIA(null, 0, 0) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String taskString = null;
    private final int titleResId = 0;
    private final int messageResId = 0;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.quickstart.QuickStartNoticeDetails.Companion Companion = null;
    
    QuickStartNoticeDetails(java.lang.String taskString, @androidx.annotation.StringRes()
    int titleResId, @androidx.annotation.StringRes()
    int messageResId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTaskString() {
        return null;
    }
    
    public final int getTitleResId() {
        return 0;
    }
    
    public final int getMessageResId() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartNoticeDetails$Companion;", "", "()V", "getNoticeForTask", "Lorg/wordpress/android/ui/quickstart/QuickStartNoticeDetails;", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.quickstart.QuickStartNoticeDetails getNoticeForTask(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
            return null;
        }
    }
}
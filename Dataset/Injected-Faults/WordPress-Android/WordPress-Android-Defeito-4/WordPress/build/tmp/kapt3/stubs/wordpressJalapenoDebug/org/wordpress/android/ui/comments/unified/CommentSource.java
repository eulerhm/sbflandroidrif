package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/CommentSource;", "", "(Ljava/lang/String;I)V", "toAnalyticsCommentActionSource", "Lorg/wordpress/android/util/analytics/AnalyticsUtils$AnalyticsCommentActionSource;", "NOTIFICATION", "SITE_COMMENTS", "org.wordpress.android_wordpressJalapenoDebug"})
public enum CommentSource {
    /*public static final*/ NOTIFICATION /* = new NOTIFICATION() */,
    /*public static final*/ SITE_COMMENTS /* = new SITE_COMMENTS() */;
    
    CommentSource() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.analytics.AnalyticsUtils.AnalyticsCommentActionSource toAnalyticsCommentActionSource() {
        return null;
    }
}
package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\u000b\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/CommentFilter;", "", "labelResId", "", "(Ljava/lang/String;II)V", "getLabelResId", "()I", "toCommentCacheStatuses", "", "Lorg/wordpress/android/fluxc/model/CommentStatus;", "toCommentStatus", "toTrackingLabelResId", "ALL", "PENDING", "APPROVED", "UNREPLIED", "TRASHED", "SPAM", "DELETE", "org.wordpress.android_wordpressJalapenoDebug"})
public enum CommentFilter {
    /*public static final*/ ALL /* = new ALL(0) */,
    /*public static final*/ PENDING /* = new PENDING(0) */,
    /*public static final*/ APPROVED /* = new APPROVED(0) */,
    /*public static final*/ UNREPLIED /* = new UNREPLIED(0) */,
    /*public static final*/ TRASHED /* = new TRASHED(0) */,
    /*public static final*/ SPAM /* = new SPAM(0) */,
    /*public static final*/ DELETE /* = new DELETE(0) */;
    private final int labelResId = 0;
    
    CommentFilter(int labelResId) {
    }
    
    public final int getLabelResId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.fluxc.model.CommentStatus> toCommentCacheStatuses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.CommentStatus toCommentStatus() {
        return null;
    }
    
    public final int toTrackingLabelResId() {
        return 0;
    }
}
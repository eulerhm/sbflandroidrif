package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsListDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "getChangePayload", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentsListDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<org.wordpress.android.ui.comments.unified.UnifiedCommentListItem> {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.comments.unified.UnifiedCommentsListDiffCallback.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COMMENT_SELECTION_TOGGLED = "COMMENT_SELECTION_TOGGLED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COMMENT_PENDING_STATE_CHANGED = "COMMENT_PENDING_STATE_CHANGED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COMMENT_CLICK_ACTION_CHANGED = "COMMENT_CLICK_ACTION_CHANGED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COMMENT_TOGGLE_ACTION_CHANGED = "COMMENT_TOGGLE_ACTION_CHANGED";
    
    public UnifiedCommentsListDiffCallback() {
        super();
    }
    
    @java.lang.Override()
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListItem oldItem, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListItem newItem) {
        return false;
    }
    
    @java.lang.Override()
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListItem oldItem, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListItem newItem) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.Object getChangePayload(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListItem oldItem, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListItem newItem) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsListDiffCallback$Companion;", "", "()V", "COMMENT_CLICK_ACTION_CHANGED", "", "COMMENT_PENDING_STATE_CHANGED", "COMMENT_SELECTION_TOGGLED", "COMMENT_TOGGLE_ACTION_CHANGED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
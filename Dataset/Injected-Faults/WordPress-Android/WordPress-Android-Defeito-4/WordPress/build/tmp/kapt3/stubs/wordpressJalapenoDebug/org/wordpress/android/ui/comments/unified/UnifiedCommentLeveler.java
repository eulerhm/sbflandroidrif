package org.wordpress.android.ui.comments.unified;

import java.lang.System;

/**
 * Adaptation of ReaderCommentLeveler. We should combine them together as part of Comment Unification.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u001e\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001e\u0010\u0014\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentLeveler;", "", "mComments", "", "Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "(Ljava/util/List;)V", "createLevelList", "Ljava/util/ArrayList;", "getChildren", "commentId", "", "hasChildren", "", "hasParent", "comment", "setLevel", "", "comments", "level", "", "walkCommentsAtLevel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentLeveler {
    private final java.util.List<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> mComments = null;
    
    public UnifiedCommentLeveler(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> mComments) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> createLevelList() {
        return null;
    }
    
    private final boolean walkCommentsAtLevel(java.util.ArrayList<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> comments, int level) {
        return false;
    }
    
    private final boolean hasChildren(long commentId) {
        return false;
    }
    
    private final boolean hasParent(org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> getChildren(long commentId) {
        return null;
    }
    
    private final void setLevel(java.util.ArrayList<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> comments, int level) {
    }
}
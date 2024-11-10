package org.wordpress.android.datasets.wrappers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J \u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tJ\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/datasets/wrappers/ReaderCommentTableWrapper;", "", "()V", "addOrUpdateComment", "", "readerComment", "Lorg/wordpress/android/models/ReaderComment;", "getComment", "blogId", "", "postId", "commentId", "getCommentsForPostSnippet", "Lorg/wordpress/android/models/ReaderCommentList;", "post", "Lorg/wordpress/android/models/ReaderPost;", "limit", "", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderCommentTableWrapper {
    
    @javax.inject.Inject()
    public ReaderCommentTableWrapper() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderCommentList getCommentsForPostSnippet(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, int limit) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderComment getComment(long blogId, long postId, long commentId) {
        return null;
    }
    
    public final void addOrUpdateComment(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderComment readerComment) {
    }
}
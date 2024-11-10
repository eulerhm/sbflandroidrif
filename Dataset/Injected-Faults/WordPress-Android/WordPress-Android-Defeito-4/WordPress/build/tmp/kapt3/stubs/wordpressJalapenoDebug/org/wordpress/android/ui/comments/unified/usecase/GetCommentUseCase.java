package org.wordpress.android.ui.comments.unified.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J#\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/usecase/GetCommentUseCase;", "", "commentsStore", "Lorg/wordpress/android/fluxc/store/CommentsStore;", "(Lorg/wordpress/android/fluxc/store/CommentsStore;)V", "execute", "Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "remoteCommentId", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GetCommentUseCase {
    private final org.wordpress.android.fluxc.store.CommentsStore commentsStore = null;
    
    @javax.inject.Inject()
    public GetCommentUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.CommentsStore commentsStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object execute(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, long remoteCommentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> continuation) {
        return null;
    }
}
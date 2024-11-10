package org.wordpress.android.ui.posts.prepublishing.visibility.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J*\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b0\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/posts/prepublishing/visibility/usecases/UpdatePostStatusUseCase;", "", "dateTimeUtilsWrapper", "Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "postUtilsWrapper", "Lorg/wordpress/android/ui/posts/PostUtilsWrapper;", "(Lorg/wordpress/android/util/DateTimeUtilsWrapper;Lorg/wordpress/android/ui/posts/PostUtilsWrapper;)V", "updatePostStatus", "", "postStatus", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "onPostStatusUpdated", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UpdatePostStatusUseCase {
    private final org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper = null;
    private final org.wordpress.android.ui.posts.PostUtilsWrapper postUtilsWrapper = null;
    
    @javax.inject.Inject()
    public UpdatePostStatusUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostUtilsWrapper postUtilsWrapper) {
        super();
    }
    
    public final void updatePostStatus(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.post.PostStatus postStatus, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.PostImmutableModel, kotlin.Unit> onPostStatusUpdated) {
    }
}
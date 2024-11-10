package org.wordpress.android.ui.posts.prepublishing.home.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J.\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0016\u0010\r\u001a\u0012\u0012\b\u0012\u00060\u000fj\u0002`\u0010\u0012\u0004\u0012\u00020\u00110\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/posts/prepublishing/home/usecases/GetButtonUiStateUseCase;", "", "editorActionsProvider", "Lorg/wordpress/android/ui/posts/editor/EditorActionsProvider;", "uploadUtilsWrapper", "Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "(Lorg/wordpress/android/ui/posts/editor/EditorActionsProvider;Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;)V", "getUiState", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState;", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "onButtonClicked", "Lkotlin/Function1;", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GetButtonUiStateUseCase {
    private final org.wordpress.android.ui.posts.editor.EditorActionsProvider editorActionsProvider = null;
    private final org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper = null;
    
    @javax.inject.Inject()
    public GetButtonUiStateUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.EditorActionsProvider editorActionsProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState getUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
        return null;
    }
}
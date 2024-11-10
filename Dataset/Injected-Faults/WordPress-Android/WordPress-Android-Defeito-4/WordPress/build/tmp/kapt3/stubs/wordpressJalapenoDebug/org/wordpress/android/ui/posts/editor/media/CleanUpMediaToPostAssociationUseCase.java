package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/CleanUpMediaToPostAssociationUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "uploadStore", "Lorg/wordpress/android/fluxc/store/UploadStore;", "aztecEditorWrapper", "Lorg/wordpress/android/ui/posts/editor/AztecEditorFragmentStaticWrapper;", "postUtilsWrapper", "Lorg/wordpress/android/ui/posts/PostUtilsWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/UploadStore;Lorg/wordpress/android/ui/posts/editor/AztecEditorFragmentStaticWrapper;Lorg/wordpress/android/ui/posts/PostUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "purgeMediaToPostAssociationsIfNotInPostAnymore", "", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "(Lorg/wordpress/android/fluxc/model/PostImmutableModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class CleanUpMediaToPostAssociationUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.UploadStore uploadStore = null;
    private final org.wordpress.android.ui.posts.editor.AztecEditorFragmentStaticWrapper aztecEditorWrapper = null;
    private final org.wordpress.android.ui.posts.PostUtilsWrapper postUtilsWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public CleanUpMediaToPostAssociationUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.UploadStore uploadStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.AztecEditorFragmentStaticWrapper aztecEditorWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostUtilsWrapper postUtilsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object purgeMediaToPostAssociationsIfNotInPostAnymore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}
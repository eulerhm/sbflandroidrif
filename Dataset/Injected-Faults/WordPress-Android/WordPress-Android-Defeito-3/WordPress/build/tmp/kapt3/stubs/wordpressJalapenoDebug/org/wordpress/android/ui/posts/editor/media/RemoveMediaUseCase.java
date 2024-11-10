package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/RemoveMediaUseCase;", "", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "mediaUtils", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "uploadService", "Lorg/wordpress/android/ui/uploads/UploadServiceFacade;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/util/MediaUtilsWrapper;Lorg/wordpress/android/ui/uploads/UploadServiceFacade;Lkotlinx/coroutines/CoroutineDispatcher;)V", "removeMediaIfNotUploading", "", "mediaIds", "", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RemoveMediaUseCase {
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtils = null;
    private final org.wordpress.android.ui.uploads.UploadServiceFacade uploadService = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public RemoveMediaUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadServiceFacade uploadService, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeMediaIfNotUploading(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> mediaIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}
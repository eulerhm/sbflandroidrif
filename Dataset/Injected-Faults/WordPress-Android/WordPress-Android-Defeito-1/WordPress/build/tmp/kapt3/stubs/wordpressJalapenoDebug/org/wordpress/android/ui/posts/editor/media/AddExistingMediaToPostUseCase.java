package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

/**
 * Loads existing media items (they must have a valid url) from the local db and adds them to the editor.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ7\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/AddExistingMediaToPostUseCase;", "", "editorTracker", "Lorg/wordpress/android/ui/posts/editor/EditorTracker;", "getMediaModelUseCase", "Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;", "appendMediaToEditorUseCase", "Lorg/wordpress/android/ui/posts/editor/media/AppendMediaToEditorUseCase;", "(Lorg/wordpress/android/ui/posts/editor/EditorTracker;Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;Lorg/wordpress/android/ui/posts/editor/media/AppendMediaToEditorUseCase;)V", "addMediaExistingInRemoteToEditorAsync", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "source", "Lorg/wordpress/android/ui/posts/editor/media/AddExistingMediaSource;", "mediaIdList", "", "", "editorMediaListener", "Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/ui/posts/editor/media/AddExistingMediaSource;Ljava/util/List;Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class AddExistingMediaToPostUseCase {
    private final org.wordpress.android.ui.posts.editor.EditorTracker editorTracker = null;
    private final org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.AppendMediaToEditorUseCase appendMediaToEditorUseCase = null;
    
    @javax.inject.Inject()
    public AddExistingMediaToPostUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.EditorTracker editorTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AppendMediaToEditorUseCase appendMediaToEditorUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addMediaExistingInRemoteToEditorAsync(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AddExistingMediaSource source, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> mediaIdList, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}
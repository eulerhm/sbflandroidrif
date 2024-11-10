package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

/**
 * Appends  media items to a content of a post. If the media item exists in the remote, its source is set to
 * MediaModel.url, if it's a local image its source is set to MediaModel.filePath.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/AppendMediaToEditorUseCase;", "", "fluxCUtilsWrapper", "Lorg/wordpress/android/util/FluxCUtilsWrapper;", "(Lorg/wordpress/android/util/FluxCUtilsWrapper;)V", "addMediaToEditor", "", "editorMediaListener", "Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;", "mediaModels", "", "Lorg/wordpress/android/fluxc/model/MediaModel;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class AppendMediaToEditorUseCase {
    private final org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper = null;
    
    @javax.inject.Inject()
    public AppendMediaToEditorUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper) {
        super();
    }
    
    public final void addMediaToEditor(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaModels) {
    }
}
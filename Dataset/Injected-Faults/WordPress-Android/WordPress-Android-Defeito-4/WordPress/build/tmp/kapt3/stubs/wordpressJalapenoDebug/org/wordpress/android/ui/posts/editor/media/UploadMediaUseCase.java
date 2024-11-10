package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

/**
 * Saves post so the UploadService has access to the most up to date version and initiates an upload.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/UploadMediaUseCase;", "", "uploadServiceFacade", "Lorg/wordpress/android/ui/uploads/UploadServiceFacade;", "(Lorg/wordpress/android/ui/uploads/UploadServiceFacade;)V", "saveQueuedPostAndStartUpload", "", "editorMediaListener", "Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;", "mediaModels", "", "Lorg/wordpress/android/fluxc/model/MediaModel;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class UploadMediaUseCase {
    private final org.wordpress.android.ui.uploads.UploadServiceFacade uploadServiceFacade = null;
    
    @javax.inject.Inject()
    public UploadMediaUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadServiceFacade uploadServiceFacade) {
        super();
    }
    
    public final void saveQueuedPostAndStartUpload(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaModels) {
    }
}
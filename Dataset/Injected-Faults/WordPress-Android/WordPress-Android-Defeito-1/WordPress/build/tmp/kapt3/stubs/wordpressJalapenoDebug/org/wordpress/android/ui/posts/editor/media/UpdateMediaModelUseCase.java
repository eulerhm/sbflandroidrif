package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

/**
 * Updates posts localId, remoteId and upload status.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/UpdateMediaModelUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lorg/wordpress/android/fluxc/Dispatcher;)V", "updateMediaModel", "", "media", "Lorg/wordpress/android/fluxc/model/MediaModel;", "postData", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "initialUploadState", "Lorg/wordpress/android/fluxc/model/MediaModel$MediaUploadState;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class UpdateMediaModelUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    
    @javax.inject.Inject()
    public UpdateMediaModelUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super();
    }
    
    public final void updateMediaModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel media, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel postData, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel.MediaUploadState initialUploadState) {
    }
}
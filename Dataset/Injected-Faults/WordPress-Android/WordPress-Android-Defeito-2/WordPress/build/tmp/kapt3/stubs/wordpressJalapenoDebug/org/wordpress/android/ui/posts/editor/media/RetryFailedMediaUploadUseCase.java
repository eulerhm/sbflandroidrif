package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\'\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/RetryFailedMediaUploadUseCase;", "", "getMediaModelUseCase", "Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;", "updateMediaModelUseCase", "Lorg/wordpress/android/ui/posts/editor/media/UpdateMediaModelUseCase;", "uploadMediaUseCase", "Lorg/wordpress/android/ui/posts/editor/media/UploadMediaUseCase;", "tracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;Lorg/wordpress/android/ui/posts/editor/media/UpdateMediaModelUseCase;Lorg/wordpress/android/ui/posts/editor/media/UploadMediaUseCase;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "retryFailedMediaAsync", "", "editorMediaListener", "Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;", "failedMediaLocalIds", "", "", "(Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class RetryFailedMediaUploadUseCase {
    private final org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.UpdateMediaModelUseCase updateMediaModelUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.UploadMediaUseCase uploadMediaUseCase = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker = null;
    
    @javax.inject.Inject()
    public RetryFailedMediaUploadUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.UpdateMediaModelUseCase updateMediaModelUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.UploadMediaUseCase uploadMediaUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object retryFailedMediaAsync(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> failedMediaLocalIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}
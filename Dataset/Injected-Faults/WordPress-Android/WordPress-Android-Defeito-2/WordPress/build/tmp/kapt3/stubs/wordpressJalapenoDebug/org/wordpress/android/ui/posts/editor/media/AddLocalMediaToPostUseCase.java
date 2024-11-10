package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

/**
 * Processes a list of local media items in the background (optimizing, resizing, rotating, etc.), adds them to
 * the editor one at a time and initiates their upload.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J1\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJK\u0010\u001d\u001a\u00020\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010#\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J&\u0010%\u001a\u00020\u00142\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002JK\u0010(\u001a\u00020\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010#\u001a\u00020\u001bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u001e\u0010)\u001a\u00020\u00142\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/AddLocalMediaToPostUseCase;", "", "copyMediaToAppStorageUseCase", "Lorg/wordpress/android/ui/posts/editor/media/CopyMediaToAppStorageUseCase;", "optimizeMediaUseCase", "Lorg/wordpress/android/ui/posts/editor/media/OptimizeMediaUseCase;", "getMediaModelUseCase", "Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;", "updateMediaModelUseCase", "Lorg/wordpress/android/ui/posts/editor/media/UpdateMediaModelUseCase;", "appendMediaToEditorUseCase", "Lorg/wordpress/android/ui/posts/editor/media/AppendMediaToEditorUseCase;", "uploadMediaUseCase", "Lorg/wordpress/android/ui/posts/editor/media/UploadMediaUseCase;", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "context", "Landroid/content/Context;", "(Lorg/wordpress/android/ui/posts/editor/media/CopyMediaToAppStorageUseCase;Lorg/wordpress/android/ui/posts/editor/media/OptimizeMediaUseCase;Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;Lorg/wordpress/android/ui/posts/editor/media/UpdateMediaModelUseCase;Lorg/wordpress/android/ui/posts/editor/media/AppendMediaToEditorUseCase;Lorg/wordpress/android/ui/posts/editor/media/UploadMediaUseCase;Lorg/wordpress/android/util/MediaUtilsWrapper;Landroid/content/Context;)V", "addLocalMediaToEditorAsync", "", "localMediaIds", "", "", "editorMediaListener", "Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;", "doUploadAfterAdding", "", "(Ljava/util/List;Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addNewMediaToEditorAsync", "uriList", "Landroid/net/Uri;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "freshlyTaken", "trackEvent", "(Ljava/util/List;Lorg/wordpress/android/fluxc/model/SiteModel;ZLorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addToEditorAndOptionallyUpload", "mediaModels", "Lorg/wordpress/android/fluxc/model/MediaModel;", "processMediaUris", "updateMediaModel", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class AddLocalMediaToPostUseCase {
    private final org.wordpress.android.ui.posts.editor.media.CopyMediaToAppStorageUseCase copyMediaToAppStorageUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.OptimizeMediaUseCase optimizeMediaUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.UpdateMediaModelUseCase updateMediaModelUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.AppendMediaToEditorUseCase appendMediaToEditorUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.UploadMediaUseCase uploadMediaUseCase = null;
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public AddLocalMediaToPostUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.CopyMediaToAppStorageUseCase copyMediaToAppStorageUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.OptimizeMediaUseCase optimizeMediaUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.UpdateMediaModelUseCase updateMediaModelUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AppendMediaToEditorUseCase appendMediaToEditorUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.UploadMediaUseCase uploadMediaUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Adds media items with existing localMediaId to the editor and optionally initiates an upload.
     * Does NOT optimize the items.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addLocalMediaToEditorAsync(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> localMediaIds, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener, boolean doUploadAfterAdding, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * Copies files to app storage, optimizes them, adds them to the editor and optionally initiates an upload.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addNewMediaToEditorAsync(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uriList, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean freshlyTaken, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener, boolean doUploadAfterAdding, boolean trackEvent, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final java.lang.Object processMediaUris(java.util.List<? extends android.net.Uri> uriList, org.wordpress.android.fluxc.model.SiteModel site, boolean freshlyTaken, org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener, boolean doUploadAfterAdding, boolean trackEvent, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final void addToEditorAndOptionallyUpload(java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaModels, org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener, boolean doUploadAfterAdding) {
    }
    
    private final void updateMediaModel(java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaModels, org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener) {
    }
}
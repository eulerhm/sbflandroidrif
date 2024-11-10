package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H&J\u001c\u0010\u0006\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH&J\b\u0010\u000b\u001a\u00020\fH&J\u001c\u0010\r\u001a\u00020\u00032\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\bH&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\tH&J\u0014\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0014H&\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;", "", "advertiseImageOptimization", "", "listener", "Lkotlin/Function0;", "appendMediaFiles", "mediaFiles", "", "", "Lorg/wordpress/android/util/helpers/MediaFile;", "getImmutablePost", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "onMediaModelsCreatedFromOptimizedUris", "oldUriToMediaFiles", "Landroid/net/Uri;", "Lorg/wordpress/android/fluxc/model/MediaModel;", "showVideoDurationLimitWarning", "fileName", "syncPostObjectWithUiAndSaveIt", "Lorg/wordpress/android/ui/posts/EditPostActivity$OnPostUpdatedFromUIListener;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface EditorMediaListener {
    
    public abstract void appendMediaFiles(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends org.wordpress.android.util.helpers.MediaFile> mediaFiles);
    
    public abstract void syncPostObjectWithUiAndSaveIt(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.posts.EditPostActivity.OnPostUpdatedFromUIListener listener);
    
    public abstract void advertiseImageOptimization(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> listener);
    
    public abstract void onMediaModelsCreatedFromOptimizedUris(@org.jetbrains.annotations.NotNull()
    java.util.Map<android.net.Uri, ? extends org.wordpress.android.fluxc.model.MediaModel> oldUriToMediaFiles);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.fluxc.model.PostImmutableModel getImmutablePost();
    
    public abstract void showVideoDurationLimitWarning(@org.jetbrains.annotations.NotNull()
    java.lang.String fileName);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}
package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0011B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001f\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/CopyMediaToAppStorageUseCase;", "", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "authenticationUtils", "Lorg/wordpress/android/ui/utils/AuthenticationUtils;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/MediaUtilsWrapper;Lorg/wordpress/android/ui/utils/AuthenticationUtils;Lkotlinx/coroutines/CoroutineDispatcher;)V", "copyFilesToAppStorageIfNecessary", "Lorg/wordpress/android/ui/posts/editor/media/CopyMediaToAppStorageUseCase$CopyMediaResult;", "uriList", "", "Landroid/net/Uri;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyToAppStorage", "mediaUri", "CopyMediaResult", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class CopyMediaToAppStorageUseCase {
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
    private final org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public CopyMediaToAppStorageUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.AuthenticationUtils authenticationUtils, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object copyFilesToAppStorageIfNecessary(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uriList, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.posts.editor.media.CopyMediaToAppStorageUseCase.CopyMediaResult> continuation) {
        return null;
    }
    
    private final android.net.Uri copyToAppStorage(android.net.Uri mediaUri) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/CopyMediaToAppStorageUseCase$CopyMediaResult;", "", "permanentlyAccessibleUris", "", "Landroid/net/Uri;", "copyingSomeMediaFailed", "", "(Ljava/util/List;Z)V", "getCopyingSomeMediaFailed", "()Z", "getPermanentlyAccessibleUris", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CopyMediaResult {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<android.net.Uri> permanentlyAccessibleUris = null;
        private final boolean copyingSomeMediaFailed = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.editor.media.CopyMediaToAppStorageUseCase.CopyMediaResult copy(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends android.net.Uri> permanentlyAccessibleUris, boolean copyingSomeMediaFailed) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public CopyMediaResult(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends android.net.Uri> permanentlyAccessibleUris, boolean copyingSomeMediaFailed) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<android.net.Uri> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<android.net.Uri> getPermanentlyAccessibleUris() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getCopyingSomeMediaFailed() {
            return false;
        }
    }
}
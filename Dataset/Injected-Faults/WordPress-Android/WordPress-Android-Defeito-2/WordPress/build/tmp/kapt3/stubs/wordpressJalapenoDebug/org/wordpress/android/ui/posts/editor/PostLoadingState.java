package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0001\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/PostLoadingState;", "", "value", "", "progressDialogUiState", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "(Ljava/lang/String;IILorg/wordpress/android/ui/posts/ProgressDialogUiState;)V", "getProgressDialogUiState", "()Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "getValue", "()I", "NONE", "LOADING_REVISION", "UPLOADING_FOR_PREVIEW", "REMOTE_AUTO_SAVING_FOR_PREVIEW", "PREVIEWING", "REMOTE_AUTO_SAVE_PREVIEW_ERROR", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public enum PostLoadingState {
    /*public static final*/ NONE /* = new NONE(0, null) */,
    /*public static final*/ LOADING_REVISION /* = new LOADING_REVISION(0, null) */,
    /*public static final*/ UPLOADING_FOR_PREVIEW /* = new UPLOADING_FOR_PREVIEW(0, null) */,
    /*public static final*/ REMOTE_AUTO_SAVING_FOR_PREVIEW /* = new REMOTE_AUTO_SAVING_FOR_PREVIEW(0, null) */,
    /*public static final*/ PREVIEWING /* = new PREVIEWING(0, null) */,
    /*public static final*/ REMOTE_AUTO_SAVE_PREVIEW_ERROR /* = new REMOTE_AUTO_SAVE_PREVIEW_ERROR(0, null) */;
    private final int value = 0;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.posts.ProgressDialogUiState progressDialogUiState = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.editor.PostLoadingState.Companion Companion = null;
    
    PostLoadingState(int value, org.wordpress.android.ui.posts.ProgressDialogUiState progressDialogUiState) {
    }
    
    public final int getValue() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.ProgressDialogUiState getProgressDialogUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.posts.editor.PostLoadingState fromInt(int value) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/PostLoadingState$Companion;", "", "()V", "fromInt", "Lorg/wordpress/android/ui/posts/editor/PostLoadingState;", "value", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.posts.editor.PostLoadingState fromInt(int value) {
            return null;
        }
    }
}
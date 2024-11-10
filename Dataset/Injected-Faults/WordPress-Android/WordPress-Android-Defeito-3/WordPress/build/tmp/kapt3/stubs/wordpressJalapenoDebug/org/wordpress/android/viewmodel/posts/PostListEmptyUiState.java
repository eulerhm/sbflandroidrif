package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0017\u0018\u0019\u001a\u001bBG\b\u0004\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e\u0082\u0001\u0005\u001c\u001d\u001e\u001f \u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState;", "", "title", "Lorg/wordpress/android/ui/utils/UiString;", "imgResId", "", "buttonText", "onButtonClick", "Lkotlin/Function0;", "", "emptyViewVisible", "", "(Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function0;Z)V", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString;", "getEmptyViewVisible", "()Z", "getImgResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOnButtonClick", "()Lkotlin/jvm/functions/Function0;", "getTitle", "DataShown", "EmptyList", "Loading", "PermissionsError", "RefreshError", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$EmptyList;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$DataShown;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$Loading;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$RefreshError;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$PermissionsError;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostListEmptyUiState {
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.utils.UiString title = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer imgResId = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.utils.UiString buttonText = null;
    @org.jetbrains.annotations.Nullable()
    private final kotlin.jvm.functions.Function0<kotlin.Unit> onButtonClick = null;
    private final boolean emptyViewVisible = false;
    
    private PostListEmptyUiState(org.wordpress.android.ui.utils.UiString title, @androidx.annotation.DrawableRes()
    java.lang.Integer imgResId, org.wordpress.android.ui.utils.UiString buttonText, kotlin.jvm.functions.Function0<kotlin.Unit> onButtonClick, boolean emptyViewVisible) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getImgResId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString getButtonText() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnButtonClick() {
        return null;
    }
    
    public final boolean getEmptyViewVisible() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\b\u0003\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$EmptyList;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "buttonText", "onButtonClick", "Lkotlin/Function0;", "", "imageResId", "", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function0;I)V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EmptyList extends org.wordpress.android.viewmodel.posts.PostListEmptyUiState {
        
        public EmptyList(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString buttonText, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onButtonClick, @androidx.annotation.DrawableRes()
        int imageResId) {
            super(null, null, null, null, false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$DataShown;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DataShown extends org.wordpress.android.viewmodel.posts.PostListEmptyUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.viewmodel.posts.PostListEmptyUiState.DataShown INSTANCE = null;
        
        private DataShown() {
            super(null, null, null, null, false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$Loading;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading extends org.wordpress.android.viewmodel.posts.PostListEmptyUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.viewmodel.posts.PostListEmptyUiState.Loading INSTANCE = null;
        
        private Loading() {
            super(null, null, null, null, false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$RefreshError;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "buttonText", "onButtonClick", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function0;)V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class RefreshError extends org.wordpress.android.viewmodel.posts.PostListEmptyUiState {
        
        public RefreshError(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString buttonText, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onButtonClick) {
            super(null, null, null, null, false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState$PermissionsError;", "Lorg/wordpress/android/viewmodel/posts/PostListEmptyUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PermissionsError extends org.wordpress.android.viewmodel.posts.PostListEmptyUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.viewmodel.posts.PostListEmptyUiState.PermissionsError INSTANCE = null;
        
        private PermissionsError() {
            super(null, null, null, null, false);
        }
    }
}
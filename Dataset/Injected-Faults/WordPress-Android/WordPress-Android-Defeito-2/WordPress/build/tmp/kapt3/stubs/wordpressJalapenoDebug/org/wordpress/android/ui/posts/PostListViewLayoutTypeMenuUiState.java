package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0019\b\u0004\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0002\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListViewLayoutTypeMenuUiState;", "", "iconRes", "", "title", "Lorg/wordpress/android/ui/utils/UiString;", "(ILorg/wordpress/android/ui/utils/UiString;)V", "getIconRes", "()I", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "CompactViewLayoutTypeMenuUiState", "StandardViewLayoutTypeMenuUiState", "Lorg/wordpress/android/ui/posts/PostListViewLayoutTypeMenuUiState$StandardViewLayoutTypeMenuUiState;", "Lorg/wordpress/android/ui/posts/PostListViewLayoutTypeMenuUiState$CompactViewLayoutTypeMenuUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PostListViewLayoutTypeMenuUiState {
    private final int iconRes = 0;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.utils.UiString title = null;
    
    private PostListViewLayoutTypeMenuUiState(@androidx.annotation.DrawableRes()
    int iconRes, org.wordpress.android.ui.utils.UiString title) {
        super();
    }
    
    public final int getIconRes() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiString getTitle() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListViewLayoutTypeMenuUiState$StandardViewLayoutTypeMenuUiState;", "Lorg/wordpress/android/ui/posts/PostListViewLayoutTypeMenuUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class StandardViewLayoutTypeMenuUiState extends org.wordpress.android.ui.posts.PostListViewLayoutTypeMenuUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.posts.PostListViewLayoutTypeMenuUiState.StandardViewLayoutTypeMenuUiState INSTANCE = null;
        
        private StandardViewLayoutTypeMenuUiState() {
            super(0, null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListViewLayoutTypeMenuUiState$CompactViewLayoutTypeMenuUiState;", "Lorg/wordpress/android/ui/posts/PostListViewLayoutTypeMenuUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CompactViewLayoutTypeMenuUiState extends org.wordpress.android.ui.posts.PostListViewLayoutTypeMenuUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.posts.PostListViewLayoutTypeMenuUiState.CompactViewLayoutTypeMenuUiState INSTANCE = null;
        
        private CompactViewLayoutTypeMenuUiState() {
            super(0, null);
        }
    }
}
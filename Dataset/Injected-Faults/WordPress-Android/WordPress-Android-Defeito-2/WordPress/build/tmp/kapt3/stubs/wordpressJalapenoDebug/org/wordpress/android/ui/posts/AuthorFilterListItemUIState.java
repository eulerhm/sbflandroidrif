package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000e\u000fB\u001f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0001\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState;", "", "id", "", "text", "Lorg/wordpress/android/ui/utils/UiString;", "isSelected", "", "(JLorg/wordpress/android/ui/utils/UiString;Z)V", "getId", "()J", "()Z", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "Everyone", "Me", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState$Everyone;", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState$Me;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class AuthorFilterListItemUIState {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.utils.UiString text = null;
    private final boolean isSelected = false;
    
    private AuthorFilterListItemUIState(long id, org.wordpress.android.ui.utils.UiString text, boolean isSelected) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiString getText() {
        return null;
    }
    
    public boolean isSelected() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState$Everyone;", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState;", "isSelected", "", "imageRes", "", "(ZI)V", "getImageRes", "()I", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Everyone extends org.wordpress.android.ui.posts.AuthorFilterListItemUIState {
        private final boolean isSelected = false;
        private final int imageRes = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.AuthorFilterListItemUIState.Everyone copy(boolean isSelected, @androidx.annotation.DrawableRes()
        int imageRes) {
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
        
        public Everyone(boolean isSelected, @androidx.annotation.DrawableRes()
        int imageRes) {
            super(0L, null, false);
        }
        
        public final boolean component1() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getImageRes() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003J\u001f\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState$Me;", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState;", "avatarUrl", "", "isSelected", "", "(Ljava/lang/String;Z)V", "getAvatarUrl", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Me extends org.wordpress.android.ui.posts.AuthorFilterListItemUIState {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String avatarUrl = null;
        private final boolean isSelected = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.AuthorFilterListItemUIState.Me copy(@org.jetbrains.annotations.Nullable()
        java.lang.String avatarUrl, boolean isSelected) {
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
        
        public Me(@org.jetbrains.annotations.Nullable()
        java.lang.String avatarUrl, boolean isSelected) {
            super(0L, null, false);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getAvatarUrl() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
    }
}
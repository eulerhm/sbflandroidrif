package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/engagement/BottomSheetUiState;", "", "()V", "UserProfileUiState", "Lorg/wordpress/android/ui/engagement/BottomSheetUiState$UserProfileUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BottomSheetUiState {
    
    private BottomSheetUiState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u009e\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012O\b\u0002\u0010\f\u001aI\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\r\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0013J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u000bH\u00c6\u0003JP\u0010.\u001aI\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\rH\u00c6\u0003J\u00b4\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2O\b\u0002\u0010\f\u001aI\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\r2\b\b\u0002\u0010\u0012\u001a\u00020\u0003H\u00c6\u0001J\u0013\u00100\u001a\u00020\u00182\b\u00101\u001a\u0004\u0018\u000102H\u00d6\u0003J\t\u00103\u001a\u000204H\u00d6\u0001J\t\u00105\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aRX\u0010\f\u001aI\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015\u00a8\u00066"}, d2 = {"Lorg/wordpress/android/ui/engagement/BottomSheetUiState$UserProfileUiState;", "Lorg/wordpress/android/ui/engagement/BottomSheetUiState;", "userAvatarUrl", "", "blavatarUrl", "userName", "userLogin", "userBio", "siteTitle", "siteUrl", "siteId", "", "onSiteClickListener", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "source", "", "blogPreviewSource", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLkotlin/jvm/functions/Function3;Ljava/lang/String;)V", "getBlavatarUrl", "()Ljava/lang/String;", "getBlogPreviewSource", "hasSiteUrl", "", "getHasSiteUrl", "()Z", "getOnSiteClickListener", "()Lkotlin/jvm/functions/Function3;", "getSiteId", "()J", "getSiteTitle", "getSiteUrl", "getUserAvatarUrl", "getUserBio", "getUserLogin", "getUserName", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UserProfileUiState extends org.wordpress.android.ui.engagement.BottomSheetUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userAvatarUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String blavatarUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userLogin = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userBio = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String siteTitle = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String siteUrl = null;
        private final long siteId = 0L;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> onSiteClickListener = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String blogPreviewSource = null;
        private final boolean hasSiteUrl = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.BottomSheetUiState.UserProfileUiState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String userAvatarUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String blavatarUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String userName, @org.jetbrains.annotations.NotNull()
        java.lang.String userLogin, @org.jetbrains.annotations.NotNull()
        java.lang.String userBio, @org.jetbrains.annotations.NotNull()
        java.lang.String siteTitle, @org.jetbrains.annotations.NotNull()
        java.lang.String siteUrl, long siteId, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onSiteClickListener, @org.jetbrains.annotations.NotNull()
        java.lang.String blogPreviewSource) {
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
        
        public UserProfileUiState(@org.jetbrains.annotations.NotNull()
        java.lang.String userAvatarUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String blavatarUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String userName, @org.jetbrains.annotations.NotNull()
        java.lang.String userLogin, @org.jetbrains.annotations.NotNull()
        java.lang.String userBio, @org.jetbrains.annotations.NotNull()
        java.lang.String siteTitle, @org.jetbrains.annotations.NotNull()
        java.lang.String siteUrl, long siteId, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onSiteClickListener, @org.jetbrains.annotations.NotNull()
        java.lang.String blogPreviewSource) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserAvatarUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBlavatarUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserLogin() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserBio() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSiteTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSiteUrl() {
            return null;
        }
        
        public final long component8() {
            return 0L;
        }
        
        public final long getSiteId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> component9() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.String, java.lang.String, kotlin.Unit> getOnSiteClickListener() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBlogPreviewSource() {
            return null;
        }
        
        public final boolean getHasSiteUrl() {
            return false;
        }
    }
}
package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\b\t\n\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "", "()V", "ActionType", "ButtonUiState", "HeaderUiState", "HomeUiState", "StoryTitleUiState", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$HomeUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$StoryTitleUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$HeaderUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PrepublishingHomeItemUiState {
    
    private PrepublishingHomeItemUiState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BZ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0003\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u000f\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\nH\u00c6\u0003J&\u0010#\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u000f\u0018\u00010\fH\u00c6\u0003Jd\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0003\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2%\b\u0002\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u000f\u0018\u00010\fH\u00c6\u0001J\u0013\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\'H\u00d6\u0003J\t\u0010(\u001a\u00020\u0005H\u00d6\u0001J\t\u0010)\u001a\u00020*H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R.\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u000f\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006+"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$HomeUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "actionType", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ActionType;", "actionTypeColor", "", "actionResult", "Lorg/wordpress/android/ui/utils/UiString;", "actionResultColor", "actionClickable", "", "onActionClicked", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "(Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ActionType;ILorg/wordpress/android/ui/utils/UiString;IZLkotlin/jvm/functions/Function1;)V", "getActionClickable", "()Z", "getActionResult", "()Lorg/wordpress/android/ui/utils/UiString;", "setActionResult", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getActionResultColor", "()I", "getActionType", "()Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ActionType;", "getActionTypeColor", "getOnActionClicked", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HomeUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType actionType = null;
        private final int actionTypeColor = 0;
        @org.jetbrains.annotations.Nullable()
        private org.wordpress.android.ui.utils.UiString actionResult;
        private final int actionResultColor = 0;
        private final boolean actionClickable = false;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType, kotlin.Unit> onActionClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.HomeUiState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType actionType, @androidx.annotation.ColorRes()
        int actionTypeColor, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString actionResult, @androidx.annotation.ColorRes()
        int actionResultColor, boolean actionClickable, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType, kotlin.Unit> onActionClicked) {
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
        
        public HomeUiState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType actionType, @androidx.annotation.ColorRes()
        int actionTypeColor, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString actionResult, @androidx.annotation.ColorRes()
        int actionResultColor, boolean actionClickable, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType, kotlin.Unit> onActionClicked) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType getActionType() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getActionTypeColor() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getActionResult() {
            return null;
        }
        
        public final void setActionResult(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString p0) {
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getActionResultColor() {
            return 0;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean getActionClickable() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType, kotlin.Unit> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType, kotlin.Unit> getOnActionClicked() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J5\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$StoryTitleUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "storyThumbnailUrl", "", "storyTitle", "Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "onStoryTitleChanged", "Lkotlin/Function1;", "", "(Ljava/lang/String;Lorg/wordpress/android/ui/utils/UiString$UiStringText;Lkotlin/jvm/functions/Function1;)V", "getOnStoryTitleChanged", "()Lkotlin/jvm/functions/Function1;", "getStoryThumbnailUrl", "()Ljava/lang/String;", "getStoryTitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class StoryTitleUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String storyThumbnailUrl = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString.UiStringText storyTitle = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onStoryTitleChanged = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.StoryTitleUiState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String storyThumbnailUrl, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString.UiStringText storyTitle, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onStoryTitleChanged) {
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
        
        public StoryTitleUiState(@org.jetbrains.annotations.NotNull()
        java.lang.String storyThumbnailUrl, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString.UiStringText storyTitle, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onStoryTitleChanged) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getStoryThumbnailUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString.UiStringText component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString.UiStringText getStoryTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getOnStoryTitleChanged() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$HeaderUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "siteName", "Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "siteIconUrl", "", "(Lorg/wordpress/android/ui/utils/UiString$UiStringText;Ljava/lang/String;)V", "getSiteIconUrl", "()Ljava/lang/String;", "getSiteName", "()Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HeaderUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString.UiStringText siteName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String siteIconUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.HeaderUiState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString.UiStringText siteName, @org.jetbrains.annotations.NotNull()
        java.lang.String siteIconUrl) {
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
        
        public HeaderUiState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString.UiStringText siteName, @org.jetbrains.annotations.NotNull()
        java.lang.String siteIconUrl) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringText component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringText getSiteName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSiteIconUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0011\u0012\u0013\u0014\u0015B\u001b\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR&\u0010\n\u001a\u0014\u0012\b\u0012\u00060\u0005j\u0002`\u0006\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0005\u0016\u0017\u0018\u0019\u001a\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "publishPost", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "(Lorg/wordpress/android/ui/utils/UiString$UiStringRes;Z)V", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "onButtonClicked", "Lkotlin/Function1;", "", "getOnButtonClicked", "()Lkotlin/jvm/functions/Function1;", "getPublishPost", "()Z", "PublishButtonUiState", "SaveButtonUiState", "ScheduleButtonUiState", "SubmitButtonUiState", "UpdateButtonUiState", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$PublishButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$ScheduleButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$UpdateButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$SubmitButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$SaveButtonUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ButtonUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString.UiStringRes buttonText = null;
        private final boolean publishPost = false;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> onButtonClicked = null;
        
        private ButtonUiState(org.wordpress.android.ui.utils.UiString.UiStringRes buttonText, boolean publishPost) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringRes getButtonText() {
            return null;
        }
        
        public final boolean getPublishPost() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnButtonClicked() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R$\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$PublishButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState;", "onButtonClicked", "Lkotlin/Function1;", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "", "(Lkotlin/jvm/functions/Function1;)V", "getOnButtonClicked", "()Lkotlin/jvm/functions/Function1;", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PublishButtonUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> onButtonClicked = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState.PublishButtonUiState copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
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
            
            public PublishButtonUiState(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnButtonClicked() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R$\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$ScheduleButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState;", "onButtonClicked", "Lkotlin/Function1;", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "", "(Lkotlin/jvm/functions/Function1;)V", "getOnButtonClicked", "()Lkotlin/jvm/functions/Function1;", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ScheduleButtonUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> onButtonClicked = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState.ScheduleButtonUiState copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
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
            
            public ScheduleButtonUiState(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnButtonClicked() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R$\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$UpdateButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState;", "onButtonClicked", "Lkotlin/Function1;", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "", "(Lkotlin/jvm/functions/Function1;)V", "getOnButtonClicked", "()Lkotlin/jvm/functions/Function1;", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UpdateButtonUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> onButtonClicked = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState.UpdateButtonUiState copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
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
            
            public UpdateButtonUiState(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnButtonClicked() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R$\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$SubmitButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState;", "onButtonClicked", "Lkotlin/Function1;", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "", "(Lkotlin/jvm/functions/Function1;)V", "getOnButtonClicked", "()Lkotlin/jvm/functions/Function1;", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SubmitButtonUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> onButtonClicked = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState.SubmitButtonUiState copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
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
            
            public SubmitButtonUiState(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnButtonClicked() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R$\u0010\u0002\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState$SaveButtonUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ButtonUiState;", "onButtonClicked", "Lkotlin/Function1;", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "", "(Lkotlin/jvm/functions/Function1;)V", "getOnButtonClicked", "()Lkotlin/jvm/functions/Function1;", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SaveButtonUiState extends org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> onButtonClicked = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ButtonUiState.SaveButtonUiState copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
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
            
            public SaveButtonUiState(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onButtonClicked) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnButtonClicked() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ActionType;", "", "textRes", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "(Ljava/lang/String;ILorg/wordpress/android/ui/utils/UiString$UiStringRes;)V", "getTextRes", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "PUBLISH", "TAGS", "CATEGORIES", "ADD_CATEGORY", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ActionType {
        /*public static final*/ PUBLISH /* = new PUBLISH(null) */,
        /*public static final*/ TAGS /* = new TAGS(null) */,
        /*public static final*/ CATEGORIES /* = new CATEGORIES(null) */,
        /*public static final*/ ADD_CATEGORY /* = new ADD_CATEGORY(null) */;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString.UiStringRes textRes = null;
        
        ActionType(org.wordpress.android.ui.utils.UiString.UiStringRes textRes) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringRes getTextRes() {
            return null;
        }
    }
}
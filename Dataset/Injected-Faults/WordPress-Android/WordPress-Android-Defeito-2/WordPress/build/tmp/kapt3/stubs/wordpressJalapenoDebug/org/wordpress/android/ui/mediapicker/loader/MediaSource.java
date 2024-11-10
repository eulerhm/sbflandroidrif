package org.wordpress.android.ui.mediapicker.loader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\nJ1\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaSource;", "", "load", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "forced", "", "loadMore", "filter", "", "(ZZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MediaLoadingResult", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface MediaSource {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object load(boolean forced, boolean loadMore, @org.jetbrains.annotations.Nullable()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult> continuation);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\b\t\nB\u0015\b\u0004\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\u000b\f\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "", "data", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "Empty", "Failure", "Success", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult$Success;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult$Empty;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class MediaLoadingResult {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> data = null;
        
        private MediaLoadingResult(java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> data) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> getData() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult$Success;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "data", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "hasMore", "", "(Ljava/util/List;Z)V", "getData", "()Ljava/util/List;", "getHasMore", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> data = null;
            private final boolean hasMore = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult.Success copy(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> data, boolean hasMore) {
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
            
            public Success(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> data, boolean hasMore) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> getData() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getHasMore() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JH\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult$Empty;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "htmlSubtitle", "image", "", "bottomImage", "bottomImageContentDescription", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;)V", "getBottomImage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBottomImageContentDescription", "()Lorg/wordpress/android/ui/utils/UiString;", "getHtmlSubtitle", "getImage", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;)Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult$Empty;", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Empty extends org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString htmlSubtitle = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer image = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer bottomImage = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString bottomImageContentDescription = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult.Empty copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString htmlSubtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, @org.jetbrains.annotations.Nullable()
            java.lang.Integer bottomImage, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString bottomImageContentDescription) {
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
            
            public Empty(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString htmlSubtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, @org.jetbrains.annotations.Nullable()
            java.lang.Integer bottomImage, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString bottomImageContentDescription) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getHtmlSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getImage() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component4() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getBottomImage() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component5() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getBottomImageContentDescription() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J@\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult$Failure;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "htmlSubtitle", "image", "", "data", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "getHtmlSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "getImage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle", "component1", "component2", "component3", "component4", "copy", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/util/List;)Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult$Failure;", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failure extends org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString htmlSubtitle = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer image = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> data = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult.Failure copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString htmlSubtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, @org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> data) {
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
            
            public Failure(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString htmlSubtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, @org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> data) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getHtmlSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getImage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> getData() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}
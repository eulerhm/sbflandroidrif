package org.wordpress.android.ui.mediapicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u000b\f\r\u000e\u000f\u0010\u0011B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0004\u0012\u0013\u0014\u0015\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem;", "", "type", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$Type;", "fullWidthItem", "", "(Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$Type;Z)V", "getFullWidthItem", "()Z", "getType", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$Type;", "ClickAction", "FileItem", "NextPageLoader", "PhotoItem", "ToggleAction", "Type", "VideoItem", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$PhotoItem;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$VideoItem;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$FileItem;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$NextPageLoader;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class MediaPickerUiItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.Type type = null;
    private final boolean fullWidthItem = false;
    
    private MediaPickerUiItem(org.wordpress.android.ui.mediapicker.MediaPickerUiItem.Type type, boolean fullWidthItem) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.Type getType() {
        return null;
    }
    
    public final boolean getFullWidthItem() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\fH\u00c6\u0003J\t\u0010#\u001a\u00020\u000eH\u00c6\u0003JV\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001\u00a2\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\u00072\b\u0010\'\u001a\u0004\u0018\u00010(H\u00d6\u0003J\t\u0010)\u001a\u00020\tH\u00d6\u0001J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0014R\u0015\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006+"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$PhotoItem;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem;", "url", "", "identifier", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "isSelected", "", "selectedOrder", "", "showOrderCounter", "toggleAction", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;", "clickAction", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;", "(Ljava/lang/String;Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;)V", "getClickAction", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;", "getIdentifier", "()Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "()Z", "getSelectedOrder", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowOrderCounter", "getToggleAction", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;", "getUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;)Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$PhotoItem;", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PhotoItem extends org.wordpress.android.ui.mediapicker.MediaPickerUiItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier = null;
        private final boolean isSelected = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer selectedOrder = null;
        private final boolean showOrderCounter = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.PhotoItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction) {
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
        
        public PhotoItem(@org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction) {
            super(null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier getIdentifier() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSelectedOrder() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean getShowOrderCounter() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction getToggleAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction getClickAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\fH\u00c6\u0003J\t\u0010#\u001a\u00020\u000eH\u00c6\u0003JV\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001\u00a2\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\u00072\b\u0010\'\u001a\u0004\u0018\u00010(H\u00d6\u0003J\t\u0010)\u001a\u00020\tH\u00d6\u0001J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0014R\u0015\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006+"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$VideoItem;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem;", "url", "", "identifier", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "isSelected", "", "selectedOrder", "", "showOrderCounter", "toggleAction", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;", "clickAction", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;", "(Ljava/lang/String;Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;)V", "getClickAction", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;", "getIdentifier", "()Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "()Z", "getSelectedOrder", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowOrderCounter", "getToggleAction", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;", "getUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;)Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$VideoItem;", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class VideoItem extends org.wordpress.android.ui.mediapicker.MediaPickerUiItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier = null;
        private final boolean isSelected = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer selectedOrder = null;
        private final boolean showOrderCounter = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.VideoItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction) {
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
        
        public VideoItem(@org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction) {
            super(null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier getIdentifier() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSelectedOrder() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean getShowOrderCounter() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction getToggleAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction getClickAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\bH\u00c6\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\t\u0010$\u001a\u00020\bH\u00c6\u0003J\t\u0010%\u001a\u00020\rH\u00c6\u0003J\t\u0010&\u001a\u00020\u000fH\u00c6\u0003Jb\u0010\'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u00c6\u0001\u00a2\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020\b2\b\u0010*\u001a\u0004\u0018\u00010+H\u00d6\u0003J\t\u0010,\u001a\u00020\nH\u00d6\u0001J\t\u0010-\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0018R\u0015\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$FileItem;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem;", "identifier", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "fileName", "", "fileExtension", "isSelected", "", "selectedOrder", "", "showOrderCounter", "toggleAction", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;", "clickAction", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;", "(Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;)V", "getClickAction", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;", "getFileExtension", "()Ljava/lang/String;", "getFileName", "getIdentifier", "()Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "()Z", "getSelectedOrder", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowOrderCounter", "getToggleAction", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;)Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$FileItem;", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FileItem extends org.wordpress.android.ui.mediapicker.MediaPickerUiItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String fileName = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String fileExtension = null;
        private final boolean isSelected = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer selectedOrder = null;
        private final boolean showOrderCounter = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.FileItem copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, @org.jetbrains.annotations.NotNull()
        java.lang.String fileName, @org.jetbrains.annotations.Nullable()
        java.lang.String fileExtension, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction) {
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
        
        public FileItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, @org.jetbrains.annotations.NotNull()
        java.lang.String fileName, @org.jetbrains.annotations.Nullable()
        java.lang.String fileExtension, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction clickAction) {
            super(null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier getIdentifier() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFileName() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getFileExtension() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSelectedOrder() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean getShowOrderCounter() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction getToggleAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction getClickAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$NextPageLoader;", "Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem;", "isLoading", "", "loadAction", "Lkotlin/Function0;", "", "(ZLkotlin/jvm/functions/Function0;)V", "()Z", "getLoadAction", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NextPageLoader extends org.wordpress.android.ui.mediapicker.MediaPickerUiItem {
        private final boolean isLoading = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> loadAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.NextPageLoader copy(boolean isLoading, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> loadAction) {
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
        
        public NextPageLoader(boolean isLoading, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> loadAction) {
            super(null, false);
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isLoading() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getLoadAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00126\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J9\u0010\u0012\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c2\u0003JW\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u000528\b\u0002\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\u0006\u0010\u001a\u001a\u00020\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR>\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ToggleAction;", "", "identifier", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "canMultiselect", "", "toggleSelected", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "(Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;ZLkotlin/jvm/functions/Function2;)V", "getCanMultiselect", "()Z", "getIdentifier", "()Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "toggle", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ToggleAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier = null;
        private final boolean canMultiselect = false;
        private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.mediapicker.MediaItem.Identifier, java.lang.Boolean, kotlin.Unit> toggleSelected = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ToggleAction copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean canMultiselect, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.mediapicker.MediaItem.Identifier, ? super java.lang.Boolean, kotlin.Unit> toggleSelected) {
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
        
        public ToggleAction(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean canMultiselect, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.mediapicker.MediaItem.Identifier, ? super java.lang.Boolean, kotlin.Unit> toggleSelected) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier getIdentifier() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getCanMultiselect() {
            return false;
        }
        
        private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.mediapicker.MediaItem.Identifier, java.lang.Boolean, kotlin.Unit> component3() {
            return null;
        }
        
        public final void toggle() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00126\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\u000bJ\u0006\u0010\u000f\u001a\u00020\nJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J9\u0010\u0012\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c2\u0003JW\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u000528\b\u0002\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R>\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$ClickAction;", "", "identifier", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "isVideo", "", "clickItem", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "(Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;ZLkotlin/jvm/functions/Function2;)V", "getIdentifier", "()Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "()Z", "click", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ClickAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier = null;
        private final boolean isVideo = false;
        private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.mediapicker.MediaItem.Identifier, java.lang.Boolean, kotlin.Unit> clickItem = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerUiItem.ClickAction copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean isVideo, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.mediapicker.MediaItem.Identifier, ? super java.lang.Boolean, kotlin.Unit> clickItem) {
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
        
        public ClickAction(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, boolean isVideo, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.mediapicker.MediaItem.Identifier, ? super java.lang.Boolean, kotlin.Unit> clickItem) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier getIdentifier() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isVideo() {
            return false;
        }
        
        private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.mediapicker.MediaItem.Identifier, java.lang.Boolean, kotlin.Unit> component3() {
            return null;
        }
        
        public final void click() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaPickerUiItem$Type;", "", "(Ljava/lang/String;I)V", "PHOTO", "VIDEO", "FILE", "NEXT_PAGE_LOADER", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ PHOTO /* = new PHOTO() */,
        /*public static final*/ VIDEO /* = new VIDEO() */,
        /*public static final*/ FILE /* = new FILE() */,
        /*public static final*/ NEXT_PAGE_LOADER /* = new NEXT_PAGE_LOADER() */;
        
        Type() {
        }
    }
}
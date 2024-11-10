package org.wordpress.android.ui.mediapicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0006\t\n\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent;", "", "()V", "EditMedia", "Exit", "IconClickEvent", "InsertMedia", "PreviewMedia", "PreviewUrl", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$PreviewUrl;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$PreviewMedia;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$EditMedia;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$InsertMedia;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$IconClickEvent;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$Exit;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class MediaNavigationEvent {
    
    private MediaNavigationEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$PreviewUrl;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreviewUrl extends org.wordpress.android.ui.mediapicker.MediaNavigationEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaNavigationEvent.PreviewUrl copy(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
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
        
        public PreviewUrl(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$PreviewMedia;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent;", "media", "Lorg/wordpress/android/fluxc/model/MediaModel;", "(Lorg/wordpress/android/fluxc/model/MediaModel;)V", "getMedia", "()Lorg/wordpress/android/fluxc/model/MediaModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PreviewMedia extends org.wordpress.android.ui.mediapicker.MediaNavigationEvent {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.MediaModel media = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaNavigationEvent.PreviewMedia copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.MediaModel media) {
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
        
        public PreviewMedia(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.MediaModel media) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.MediaModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.MediaModel getMedia() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$EditMedia;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent;", "uris", "", "Lorg/wordpress/android/util/UriWrapper;", "(Ljava/util/List;)V", "getUris", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EditMedia extends org.wordpress.android.ui.mediapicker.MediaNavigationEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.util.UriWrapper> uris = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaNavigationEvent.EditMedia copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.util.UriWrapper> uris) {
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
        
        public EditMedia(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.util.UriWrapper> uris) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.util.UriWrapper> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.util.UriWrapper> getUris() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$InsertMedia;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent;", "identifiers", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "(Ljava/util/List;)V", "getIdentifiers", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class InsertMedia extends org.wordpress.android.ui.mediapicker.MediaNavigationEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaNavigationEvent.InsertMedia copy(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers) {
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
        
        public InsertMedia(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem.Identifier> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem.Identifier> getIdentifiers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$IconClickEvent;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent;", "action", "Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerAction;", "(Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerAction;)V", "getAction", "()Lorg/wordpress/android/ui/mediapicker/MediaPickerFragment$MediaPickerAction;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class IconClickEvent extends org.wordpress.android.ui.mediapicker.MediaNavigationEvent {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerAction action = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaNavigationEvent.IconClickEvent copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerAction action) {
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
        
        public IconClickEvent(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerAction action) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerAction component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaPickerFragment.MediaPickerAction getAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent$Exit;", "Lorg/wordpress/android/ui/mediapicker/MediaNavigationEvent;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Exit extends org.wordpress.android.ui.mediapicker.MediaNavigationEvent {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.mediapicker.MediaNavigationEvent.Exit INSTANCE = null;
        
        private Exit() {
            super();
        }
    }
}
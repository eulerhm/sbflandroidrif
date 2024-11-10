package org.wordpress.android.ui.mediapicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0002$%B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u000bH\u00c6\u0003JI\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem;", "", "identifier", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "url", "", "name", "type", "Lorg/wordpress/android/ui/mediapicker/MediaType;", "mimeType", "dataModified", "", "(Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/mediapicker/MediaType;Ljava/lang/String;J)V", "getDataModified", "()J", "getIdentifier", "()Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "getMimeType", "()Ljava/lang/String;", "getName", "getType", "()Lorg/wordpress/android/ui/mediapicker/MediaType;", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "Identifier", "IdentifierType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MediaItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String url = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.mediapicker.MediaType type = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String mimeType = null;
    private final long dataModified = 0L;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mediapicker.MediaItem copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaType type, @org.jetbrains.annotations.Nullable()
    java.lang.String mimeType, long dataModified) {
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
    
    public MediaItem(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaItem.Identifier identifier, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaType type, @org.jetbrains.annotations.Nullable()
    java.lang.String mimeType, long dataModified) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mediapicker.MediaType component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mediapicker.MediaType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMimeType() {
        return null;
    }
    
    public final long component6() {
        return 0L;
    }
    
    public final long getDataModified() {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem$IdentifierType;", "", "(Ljava/lang/String;I)V", "LOCAL_URI", "REMOTE_ID", "LOCAL_ID", "STOCK_MEDIA_IDENTIFIER", "GIF_MEDIA_IDENTIFIER", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum IdentifierType {
        /*public static final*/ LOCAL_URI /* = new LOCAL_URI() */,
        /*public static final*/ REMOTE_ID /* = new REMOTE_ID() */,
        /*public static final*/ LOCAL_ID /* = new LOCAL_ID() */,
        /*public static final*/ STOCK_MEDIA_IDENTIFIER /* = new STOCK_MEDIA_IDENTIFIER() */,
        /*public static final*/ GIF_MEDIA_IDENTIFIER /* = new GIF_MEDIA_IDENTIFIER() */;
        
        IdentifierType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u000e2\u00020\u0001:\u0006\u000e\u000f\u0010\u0011\u0012\u0013B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0005\u0014\u0015\u0016\u0017\u0018\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "Landroid/os/Parcelable;", "type", "Lorg/wordpress/android/ui/mediapicker/MediaItem$IdentifierType;", "(Lorg/wordpress/android/ui/mediapicker/MediaItem$IdentifierType;)V", "getType", "()Lorg/wordpress/android/ui/mediapicker/MediaItem$IdentifierType;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "GifMediaIdentifier", "LocalId", "LocalUri", "RemoteId", "StockMediaIdentifier", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$LocalUri;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$RemoteId;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$LocalId;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$StockMediaIdentifier;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$GifMediaIdentifier;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Identifier implements android.os.Parcelable {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mediapicker.MediaItem.IdentifierType type = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.mediapicker.MediaItem.Identifier.Companion Companion = null;
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmField()
        public static final android.os.Parcelable.Creator<org.wordpress.android.ui.mediapicker.MediaItem.Identifier> CREATOR = null;
        
        private Identifier(org.wordpress.android.ui.mediapicker.MediaItem.IdentifierType type) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.MediaItem.IdentifierType getType() {
            return null;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$LocalUri;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "value", "Lorg/wordpress/android/util/UriWrapper;", "queued", "", "(Lorg/wordpress/android/util/UriWrapper;Z)V", "getQueued", "()Z", "getValue", "()Lorg/wordpress/android/util/UriWrapper;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class LocalUri extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.util.UriWrapper value = null;
            private final boolean queued = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier.LocalUri copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper value, boolean queued) {
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
            
            public LocalUri(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper value, boolean queued) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.UriWrapper component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.UriWrapper getValue() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getQueued() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$RemoteId;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "value", "", "(J)V", "getValue", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RemoteId extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier {
            private final long value = 0L;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier.RemoteId copy(long value) {
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
            
            public RemoteId(long value) {
                super(null);
            }
            
            public final long component1() {
                return 0L;
            }
            
            public final long getValue() {
                return 0L;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$LocalId;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "value", "", "(I)V", "getValue", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class LocalId extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier {
            private final int value = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier.LocalId copy(int value) {
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
            
            public LocalId(int value) {
                super(null);
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getValue() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$StockMediaIdentifier;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "url", "", "name", "title", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getTitle", "getUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class StockMediaIdentifier extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier {
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String url = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String name = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String title = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier.StockMediaIdentifier copy(@org.jetbrains.annotations.Nullable()
            java.lang.String url, @org.jetbrains.annotations.Nullable()
            java.lang.String name, @org.jetbrains.annotations.Nullable()
            java.lang.String title) {
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
            
            public StockMediaIdentifier(@org.jetbrains.annotations.Nullable()
            java.lang.String url, @org.jetbrains.annotations.Nullable()
            java.lang.String name, @org.jetbrains.annotations.Nullable()
            java.lang.String title) {
                super(null);
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getUrl() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getName() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getTitle() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$GifMediaIdentifier;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "largeImageUri", "Lorg/wordpress/android/util/UriWrapper;", "title", "", "(Lorg/wordpress/android/util/UriWrapper;Ljava/lang/String;)V", "getLargeImageUri", "()Lorg/wordpress/android/util/UriWrapper;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class GifMediaIdentifier extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.util.UriWrapper largeImageUri = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String title = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.MediaItem.Identifier.GifMediaIdentifier copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper largeImageUri, @org.jetbrains.annotations.Nullable()
            java.lang.String title) {
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
            
            public GifMediaIdentifier(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper largeImageUri, @org.jetbrains.annotations.Nullable()
            java.lang.String title) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.UriWrapper component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.UriWrapper getLargeImageUri() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getTitle() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
        }
    }
}
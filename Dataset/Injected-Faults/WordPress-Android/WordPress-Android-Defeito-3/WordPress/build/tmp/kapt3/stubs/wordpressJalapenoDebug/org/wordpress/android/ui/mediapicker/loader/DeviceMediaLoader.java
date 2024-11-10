package org.wordpress.android.ui.mediapicker.loader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0003#$%B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J7\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012H\u0002\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\u0016J)\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\u0002\u0010\u001cJ1\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\u0002\u0010 J\f\u0010!\u001a\u00020\u001b*\u00020\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader;", "", "context", "Landroid/content/Context;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "(Landroid/content/Context;Lorg/wordpress/android/util/LocaleManagerWrapper;)V", "mimeTypes", "Lorg/wordpress/android/fluxc/utils/MimeTypes;", "getCursor", "Landroid/database/Cursor;", "condition", "", "pageSize", "", "baseUri", "Landroid/net/Uri;", "projection", "", "(Ljava/lang/String;ILandroid/net/Uri;[Ljava/lang/String;)Landroid/database/Cursor;", "getMimeType", "uri", "Lorg/wordpress/android/util/UriWrapper;", "loadDocuments", "Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader$DeviceMediaList;", "filter", "limitDate", "", "(Ljava/lang/String;ILjava/lang/Long;)Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader$DeviceMediaList;", "loadMedia", "mediaType", "Lorg/wordpress/android/ui/mediapicker/MediaType;", "(Lorg/wordpress/android/ui/mediapicker/MediaType;Ljava/lang/String;ILjava/lang/Long;)Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader$DeviceMediaList;", "lastModifiedInSecs", "Ljava/io/File;", "Companion", "DeviceMediaItem", "DeviceMediaList", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DeviceMediaLoader {
    private final android.content.Context context = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.fluxc.utils.MimeTypes mimeTypes = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.Companion Companion = null;
    private static final java.lang.String ID_COL = "_id";
    private static final java.lang.String ID_DATE_MODIFIED = "date_modified";
    private static final java.lang.String ID_TITLE = "title";
    
    @javax.inject.Inject()
    public DeviceMediaLoader(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaList loadMedia(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaType mediaType, @org.jetbrains.annotations.Nullable()
    java.lang.String filter, int pageSize, @org.jetbrains.annotations.Nullable()
    java.lang.Long limitDate) {
        return null;
    }
    
    private final android.database.Cursor getCursor(java.lang.String condition, int pageSize, android.net.Uri baseUri, java.lang.String[] projection) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaList loadDocuments(@org.jetbrains.annotations.Nullable()
    java.lang.String filter, int pageSize, @org.jetbrains.annotations.Nullable()
    java.lang.Long limitDate) {
        return null;
    }
    
    private final long lastModifiedInSecs(java.io.File $this$lastModifiedInSecs) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMimeType(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ*\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader$DeviceMediaList;", "", "items", "", "Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader$DeviceMediaItem;", "next", "", "(Ljava/util/List;Ljava/lang/Long;)V", "getItems", "()Ljava/util/List;", "getNext", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "copy", "(Ljava/util/List;Ljava/lang/Long;)Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader$DeviceMediaList;", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DeviceMediaList {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaItem> items = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long next = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaList copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaItem> items, @org.jetbrains.annotations.Nullable()
        java.lang.Long next) {
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
        
        public DeviceMediaList(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaItem> items, @org.jetbrains.annotations.Nullable()
        java.lang.Long next) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaItem> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaItem> getItems() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getNext() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader$DeviceMediaItem;", "", "uri", "Lorg/wordpress/android/util/UriWrapper;", "title", "", "dateModified", "", "(Lorg/wordpress/android/util/UriWrapper;Ljava/lang/String;J)V", "getDateModified", "()J", "getTitle", "()Ljava/lang/String;", "getUri", "()Lorg/wordpress/android/util/UriWrapper;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DeviceMediaItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.util.UriWrapper uri = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        private final long dateModified = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.loader.DeviceMediaLoader.DeviceMediaItem copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.UriWrapper uri, @org.jetbrains.annotations.NotNull()
        java.lang.String title, long dateModified) {
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
        
        public DeviceMediaItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.UriWrapper uri, @org.jetbrains.annotations.NotNull()
        java.lang.String title, long dateModified) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.util.UriWrapper component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.util.UriWrapper getUri() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        public final long component3() {
            return 0L;
        }
        
        public final long getDateModified() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/DeviceMediaLoader$Companion;", "", "()V", "ID_COL", "", "ID_DATE_MODIFIED", "ID_TITLE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
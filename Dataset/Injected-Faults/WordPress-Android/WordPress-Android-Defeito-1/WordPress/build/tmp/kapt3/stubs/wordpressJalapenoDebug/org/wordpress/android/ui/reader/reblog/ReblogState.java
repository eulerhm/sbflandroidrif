package org.wordpress.android.ui.reader.reblog;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/reblog/ReblogState;", "", "()V", "MultipleSites", "NoSite", "SingleSite", "Unknown", "Lorg/wordpress/android/ui/reader/reblog/ReblogState$MultipleSites;", "Lorg/wordpress/android/ui/reader/reblog/ReblogState$SingleSite;", "Lorg/wordpress/android/ui/reader/reblog/ReblogState$NoSite;", "Lorg/wordpress/android/ui/reader/reblog/ReblogState$Unknown;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ReblogState {
    
    private ReblogState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/reblog/ReblogState$MultipleSites;", "Lorg/wordpress/android/ui/reader/reblog/ReblogState;", "defaultSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/models/ReaderPost;)V", "getDefaultSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MultipleSites extends org.wordpress.android.ui.reader.reblog.ReblogState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel defaultSite = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        
        public MultipleSites(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel defaultSite, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getDefaultSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderPost getPost() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/reblog/ReblogState$SingleSite;", "Lorg/wordpress/android/ui/reader/reblog/ReblogState;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/models/ReaderPost;)V", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SingleSite extends org.wordpress.android.ui.reader.reblog.ReblogState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderPost post = null;
        
        public SingleSite(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderPost getPost() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/reblog/ReblogState$NoSite;", "Lorg/wordpress/android/ui/reader/reblog/ReblogState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NoSite extends org.wordpress.android.ui.reader.reblog.ReblogState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.reblog.ReblogState.NoSite INSTANCE = null;
        
        private NoSite() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/reblog/ReblogState$Unknown;", "Lorg/wordpress/android/ui/reader/reblog/ReblogState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Unknown extends org.wordpress.android.ui.reader.reblog.ReblogState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.reblog.ReblogState.Unknown INSTANCE = null;
        
        private Unknown() {
            super();
        }
    }
}
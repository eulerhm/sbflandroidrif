package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u000e\u000fB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\n\u0010\f\u001a\u00020\u000b*\u00020\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewNavigationDelegate;", "", "()V", "allowedUrls", "", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewNavigationDelegate$UrlMatcher;", "optionalLanguagePath", "Lkotlin/text/Regex;", "canNavigateTo", "", "url", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewNavigationDelegate$Url;", "toUrl", "Landroid/net/Uri;", "Url", "UrlMatcher", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainRegistrationCheckoutWebViewNavigationDelegate {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate INSTANCE = null;
    private static final kotlin.text.Regex optionalLanguagePath = null;
    private static final java.util.List<org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate.UrlMatcher> allowedUrls = null;
    
    private DomainRegistrationCheckoutWebViewNavigationDelegate() {
        super();
    }
    
    public final boolean canNavigateTo(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate.Url url) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate.Url toUrl(@org.jetbrains.annotations.NotNull()
    android.net.Uri $this$toUrl) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003H\u00c2\u0003J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c2\u0003J#\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewNavigationDelegate$UrlMatcher;", "", "host", "Lkotlin/text/Regex;", "paths", "", "(Lkotlin/text/Regex;Ljava/util/List;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "matches", "url", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewNavigationDelegate$Url;", "matchesHost", "matchesPath", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UrlMatcher {
        private final kotlin.text.Regex host = null;
        private final java.util.List<kotlin.text.Regex> paths = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate.UrlMatcher copy(@org.jetbrains.annotations.NotNull()
        kotlin.text.Regex host, @org.jetbrains.annotations.NotNull()
        java.util.List<kotlin.text.Regex> paths) {
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
        
        public UrlMatcher(@org.jetbrains.annotations.NotNull()
        kotlin.text.Regex host, @org.jetbrains.annotations.NotNull()
        java.util.List<kotlin.text.Regex> paths) {
            super();
        }
        
        private final kotlin.text.Regex component1() {
            return null;
        }
        
        private final java.util.List<kotlin.text.Regex> component2() {
            return null;
        }
        
        private final boolean matchesHost(org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate.Url url) {
            return false;
        }
        
        private final boolean matchesPath(org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate.Url url) {
            return false;
        }
        
        public final boolean matches(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate.Url url) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewNavigationDelegate$Url;", "", "host", "", "path", "(Ljava/lang/String;Ljava/lang/String;)V", "getHost", "()Ljava/lang/String;", "getPath", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Url {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String host = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String path = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate.Url copy(@org.jetbrains.annotations.NotNull()
        java.lang.String host, @org.jetbrains.annotations.NotNull()
        java.lang.String path) {
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
        
        public Url(@org.jetbrains.annotations.NotNull()
        java.lang.String host, @org.jetbrains.annotations.NotNull()
        java.lang.String path) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPath() {
            return null;
        }
    }
}
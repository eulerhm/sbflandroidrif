package org.wordpress.android.ui.deeplinks;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator;", "", "()V", "handleNavigationAction", "", "navigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "NavigateAction", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DeepLinkNavigator {
    
    @javax.inject.Inject()
    public DeepLinkNavigator() {
        super();
    }
    
    @kotlin.Suppress(names = {"ComplexMethod"})
    public final void handleNavigationAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction navigateAction, @org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0012\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0012\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "", "()V", "LoginForResult", "OpenEditor", "OpenEditorForPost", "OpenEditorForSite", "OpenInBrowser", "OpenInReader", "OpenNotifications", "OpenPages", "OpenPagesForSite", "OpenQRCodeAuthFlow", "OpenReader", "OpenStats", "OpenStatsForSite", "OpenStatsForSiteAndTimeframe", "OpenStatsForTimeframe", "ShowSignInFlow", "StartCreateSiteFlow", "ViewPostInReader", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$LoginForResult;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenInBrowser;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenEditorForPost;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenEditorForSite;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenReader;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenInReader;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$ViewPostInReader;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenEditor;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenStatsForSiteAndTimeframe;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenStatsForSite;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenStatsForTimeframe;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenStats;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$StartCreateSiteFlow;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$ShowSignInFlow;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenNotifications;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenPagesForSite;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenPages;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenQRCodeAuthFlow;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class NavigateAction {
        
        private NavigateAction() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$LoginForResult;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class LoginForResult extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.LoginForResult INSTANCE = null;
            
            private LoginForResult() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenInBrowser;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "(Lorg/wordpress/android/util/UriWrapper;)V", "getUri", "()Lorg/wordpress/android/util/UriWrapper;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenInBrowser extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.util.UriWrapper uri = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenInBrowser copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper uri) {
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
            
            public OpenInBrowser(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper uri) {
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
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenEditorForPost;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "postId", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;I)V", "getPostId", "()I", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenEditorForPost extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            private final int postId = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenEditorForPost copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, int postId) {
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
            
            public OpenEditorForPost(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, int postId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
            
            public final int component2() {
                return 0;
            }
            
            public final int getPostId() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenEditorForSite;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenEditorForSite extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenEditorForSite copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site) {
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
            
            public OpenEditorForSite(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenReader;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenReader extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenReader INSTANCE = null;
            
            private OpenReader() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenInReader;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "(Lorg/wordpress/android/util/UriWrapper;)V", "getUri", "()Lorg/wordpress/android/util/UriWrapper;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenInReader extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.util.UriWrapper uri = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenInReader copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper uri) {
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
            
            public OpenInReader(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper uri) {
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
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$ViewPostInReader;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "blogId", "", "postId", "uri", "Lorg/wordpress/android/util/UriWrapper;", "(JJLorg/wordpress/android/util/UriWrapper;)V", "getBlogId", "()J", "getPostId", "getUri", "()Lorg/wordpress/android/util/UriWrapper;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ViewPostInReader extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            private final long blogId = 0L;
            private final long postId = 0L;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.util.UriWrapper uri = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.ViewPostInReader copy(long blogId, long postId, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper uri) {
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
            
            public ViewPostInReader(long blogId, long postId, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.util.UriWrapper uri) {
                super();
            }
            
            public final long component1() {
                return 0L;
            }
            
            public final long getBlogId() {
                return 0L;
            }
            
            public final long component2() {
                return 0L;
            }
            
            public final long getPostId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.UriWrapper component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.UriWrapper getUri() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenEditor;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenEditor extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenEditor INSTANCE = null;
            
            private OpenEditor() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenStatsForSiteAndTimeframe;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "statsTimeframe", "Lorg/wordpress/android/ui/stats/StatsTimeframe;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/ui/stats/StatsTimeframe;)V", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "getStatsTimeframe", "()Lorg/wordpress/android/ui/stats/StatsTimeframe;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenStatsForSiteAndTimeframe extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenStatsForSiteAndTimeframe copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
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
            
            public OpenStatsForSiteAndTimeframe(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.StatsTimeframe component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.StatsTimeframe getStatsTimeframe() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenStatsForSite;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenStatsForSite extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenStatsForSite copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site) {
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
            
            public OpenStatsForSite(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenStatsForTimeframe;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "statsTimeframe", "Lorg/wordpress/android/ui/stats/StatsTimeframe;", "(Lorg/wordpress/android/ui/stats/StatsTimeframe;)V", "getStatsTimeframe", "()Lorg/wordpress/android/ui/stats/StatsTimeframe;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenStatsForTimeframe extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenStatsForTimeframe copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
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
            
            public OpenStatsForTimeframe(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.StatsTimeframe component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.StatsTimeframe getStatsTimeframe() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenStats;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenStats extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenStats INSTANCE = null;
            
            private OpenStats() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$StartCreateSiteFlow;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class StartCreateSiteFlow extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.StartCreateSiteFlow INSTANCE = null;
            
            private StartCreateSiteFlow() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$ShowSignInFlow;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ShowSignInFlow extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.ShowSignInFlow INSTANCE = null;
            
            private ShowSignInFlow() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenNotifications;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenNotifications extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenNotifications INSTANCE = null;
            
            private OpenNotifications() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenPagesForSite;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenPagesForSite extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenPagesForSite copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site) {
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
            
            public OpenPagesForSite(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.SiteModel getSite() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenPages;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenPages extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenPages INSTANCE = null;
            
            private OpenPages() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction$OpenQRCodeAuthFlow;", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "", "(Ljava/lang/String;)V", "getUri", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OpenQRCodeAuthFlow extends org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String uri = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenQRCodeAuthFlow copy(@org.jetbrains.annotations.NotNull()
            java.lang.String uri) {
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
            
            public OpenQRCodeAuthFlow(@org.jetbrains.annotations.NotNull()
            java.lang.String uri) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUri() {
                return null;
            }
        }
    }
}
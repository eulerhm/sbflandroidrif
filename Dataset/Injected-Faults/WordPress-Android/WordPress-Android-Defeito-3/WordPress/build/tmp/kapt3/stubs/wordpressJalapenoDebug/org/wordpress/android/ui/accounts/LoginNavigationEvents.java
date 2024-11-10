package org.wordpress.android.ui.accounts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\n\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\fB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\n\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "", "()V", "CloseWithResultOk", "CreateNewSite", "SelectSite", "ShowEmailLoginScreen", "ShowInstructions", "ShowLoginViaSiteAddressScreen", "ShowNoJetpackSites", "ShowPostSignupInterstitialScreen", "ShowSignInForResultJetpackOnly", "ShowSiteAddressError", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowSiteAddressError;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowNoJetpackSites;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowSignInForResultJetpackOnly;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowInstructions;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowPostSignupInterstitialScreen;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$SelectSite;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$CreateNewSite;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$CloseWithResultOk;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowEmailLoginScreen;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowLoginViaSiteAddressScreen;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class LoginNavigationEvents {
    
    private LoginNavigationEvents() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowSiteAddressError;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowSiteAddressError extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowSiteAddressError copy(@org.jetbrains.annotations.NotNull()
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
        
        public ShowSiteAddressError(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowNoJetpackSites;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowNoJetpackSites extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowNoJetpackSites INSTANCE = null;
        
        private ShowNoJetpackSites() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowSignInForResultJetpackOnly;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowSignInForResultJetpackOnly extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowSignInForResultJetpackOnly INSTANCE = null;
        
        private ShowSignInForResultJetpackOnly() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowInstructions;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowInstructions extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowInstructions copy(@org.jetbrains.annotations.NotNull()
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
        
        public ShowInstructions() {
            super();
        }
        
        public ShowInstructions(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowPostSignupInterstitialScreen;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowPostSignupInterstitialScreen extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowPostSignupInterstitialScreen INSTANCE = null;
        
        private ShowPostSignupInterstitialScreen() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$SelectSite;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "localId", "", "(I)V", "getLocalId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SelectSite extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        private final int localId = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.accounts.LoginNavigationEvents.SelectSite copy(int localId) {
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
        
        public SelectSite(int localId) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getLocalId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$CreateNewSite;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CreateNewSite extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.accounts.LoginNavigationEvents.CreateNewSite INSTANCE = null;
        
        private CreateNewSite() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$CloseWithResultOk;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CloseWithResultOk extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.accounts.LoginNavigationEvents.CloseWithResultOk INSTANCE = null;
        
        private CloseWithResultOk() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowEmailLoginScreen;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowEmailLoginScreen extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowEmailLoginScreen INSTANCE = null;
        
        private ShowEmailLoginScreen() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginNavigationEvents$ShowLoginViaSiteAddressScreen;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowLoginViaSiteAddressScreen extends org.wordpress.android.ui.accounts.LoginNavigationEvents {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.accounts.LoginNavigationEvents.ShowLoginViaSiteAddressScreen INSTANCE = null;
        
        private ShowLoginViaSiteAddressScreen() {
            super();
        }
    }
}
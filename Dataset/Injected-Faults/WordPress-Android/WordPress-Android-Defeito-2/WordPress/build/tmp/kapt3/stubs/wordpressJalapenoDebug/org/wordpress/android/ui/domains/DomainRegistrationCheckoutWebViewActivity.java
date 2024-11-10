package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0014J\b\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewActivity;", "Lorg/wordpress/android/ui/WPWebViewActivity;", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewClient$DomainRegistrationCheckoutWebViewClientListener;", "()V", "createWebViewClient", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewClient;", "allowedURL", "", "", "onCheckoutSuccess", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "OpenCheckout", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainRegistrationCheckoutWebViewActivity extends org.wordpress.android.ui.WPWebViewActivity implements org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewClient.DomainRegistrationCheckoutWebViewClientListener {
    
    public DomainRegistrationCheckoutWebViewActivity() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewClient createWebViewClient(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> allowedURL) {
        return null;
    }
    
    @java.lang.Override()
    public void onCheckoutSuccess() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00132\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001:\u0002\u0012\u0013B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0016\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewActivity$OpenCheckout;", "Landroidx/activity/result/contract/ActivityResultContract;", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewActivity$OpenCheckout$CheckoutDetails;", "Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "getCheckoutUrl", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "parseResult", "resultCode", "", "intent", "CheckoutDetails", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OpenCheckout extends androidx.activity.result.contract.ActivityResultContract<org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewActivity.OpenCheckout.CheckoutDetails, org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent> {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewActivity.OpenCheckout.Companion Companion = null;
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String REGISTRATION_DOMAIN_NAME = "REGISTRATION_DOMAIN_NAME";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String REGISTRATION_EMAIL = "REGISTRATION_EMAIL";
        
        public OpenCheckout() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewActivity.OpenCheckout.CheckoutDetails input) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent parseResult(int resultCode, @org.jetbrains.annotations.Nullable()
        android.content.Intent intent) {
            return null;
        }
        
        private final java.lang.String getCheckoutUrl(org.wordpress.android.fluxc.model.SiteModel site) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewActivity$OpenCheckout$CheckoutDetails;", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "domainName", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/String;)V", "getDomainName", "()Ljava/lang/String;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class CheckoutDetails {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.SiteModel site = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String domainName = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewActivity.OpenCheckout.CheckoutDetails copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
            java.lang.String domainName) {
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
            
            public CheckoutDetails(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
            java.lang.String domainName) {
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
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getDomainName() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewActivity$OpenCheckout$Companion;", "", "()V", "REGISTRATION_DOMAIN_NAME", "", "REGISTRATION_EMAIL", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
        }
    }
}
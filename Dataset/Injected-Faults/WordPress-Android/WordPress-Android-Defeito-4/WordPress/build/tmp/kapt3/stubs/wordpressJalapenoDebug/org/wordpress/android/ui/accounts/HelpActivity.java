package org.wordpress.android.ui.accounts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 <2\u00020\u0001:\u0002<=B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010/\u001a\u000200H\u0002J\u0012\u00101\u001a\u0002002\b\u00102\u001a\u0004\u0018\u000103H\u0014J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u000200H\u0014J\b\u00109\u001a\u000200H\u0002J\b\u0010:\u001a\u000200H\u0002J\f\u0010;\u001a\u000200*\u00020\nH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r\u0018\u00010\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001d\u001a\u00020\u001e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010#\u001a\u00020$8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u001e\u0010)\u001a\u00020*8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u00a8\u0006>"}, d2 = {"Lorg/wordpress/android/ui/accounts/HelpActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "getAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "setAccountStore", "(Lorg/wordpress/android/fluxc/store/AccountStore;)V", "binding", "Lorg/wordpress/android/databinding/HelpActivityBinding;", "extraTagsFromExtras", "Ljava/util/ArrayList;", "", "kotlin.jvm.PlatformType", "getExtraTagsFromExtras", "()Ljava/util/ArrayList;", "extraTagsFromExtras$delegate", "Lkotlin/Lazy;", "originFromExtras", "Lorg/wordpress/android/ui/accounts/HelpActivity$Origin;", "getOriginFromExtras", "()Lorg/wordpress/android/ui/accounts/HelpActivity$Origin;", "originFromExtras$delegate", "selectedSiteFromExtras", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSelectedSiteFromExtras", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "selectedSiteFromExtras$delegate", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "getSiteStore", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "setSiteStore", "(Lorg/wordpress/android/fluxc/store/SiteStore;)V", "supportHelper", "Lorg/wordpress/android/support/SupportHelper;", "getSupportHelper", "()Lorg/wordpress/android/support/SupportHelper;", "setSupportHelper", "(Lorg/wordpress/android/support/SupportHelper;)V", "zendeskHelper", "Lorg/wordpress/android/support/ZendeskHelper;", "getZendeskHelper", "()Lorg/wordpress/android/support/ZendeskHelper;", "setZendeskHelper", "(Lorg/wordpress/android/support/ZendeskHelper;)V", "createNewZendeskTicket", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "showFaq", "showZendeskTickets", "refreshContactEmailText", "Companion", "Origin", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HelpActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.AccountStore accountStore;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.SiteStore siteStore;
    @javax.inject.Inject()
    public org.wordpress.android.support.SupportHelper supportHelper;
    @javax.inject.Inject()
    public org.wordpress.android.support.ZendeskHelper zendeskHelper;
    private org.wordpress.android.databinding.HelpActivityBinding binding;
    private final kotlin.Lazy originFromExtras$delegate = null;
    private final kotlin.Lazy extraTagsFromExtras$delegate = null;
    private final kotlin.Lazy selectedSiteFromExtras$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.accounts.HelpActivity.Companion Companion = null;
    private static final java.lang.String ORIGIN_KEY = "ORIGIN_KEY";
    private static final java.lang.String EXTRA_TAGS_KEY = "EXTRA_TAGS_KEY";
    
    public HelpActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getAccountStore() {
        return null;
    }
    
    public final void setAccountStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore() {
        return null;
    }
    
    public final void setSiteStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.support.SupportHelper getSupportHelper() {
        return null;
    }
    
    public final void setSupportHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.support.SupportHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.support.ZendeskHelper getZendeskHelper() {
        return null;
    }
    
    public final void setZendeskHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.support.ZendeskHelper p0) {
    }
    
    private final org.wordpress.android.ui.accounts.HelpActivity.Origin getOriginFromExtras() {
        return null;
    }
    
    private final java.util.ArrayList<java.lang.String> getExtraTagsFromExtras() {
        return null;
    }
    
    private final org.wordpress.android.fluxc.model.SiteModel getSelectedSiteFromExtras() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void createNewZendeskTicket() {
    }
    
    private final void showZendeskTickets() {
    }
    
    private final void showFaq() {
    }
    
    private final void refreshContactEmailText(org.wordpress.android.databinding.HelpActivityBinding $this$refreshContactEmailText) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.HelpActivity.Origin origin, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel selectedSite, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> extraSupportTags) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/accounts/HelpActivity$Origin;", "", "stringValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "UNKNOWN", "ZENDESK_NOTIFICATION", "LOGIN_SCREEN_WPCOM", "LOGIN_SCREEN_SELF_HOSTED", "LOGIN_SCREEN_JETPACK", "SIGNUP_SCREEN", "ME_SCREEN_HELP", "DELETE_SITE", "DISCARD_CHANGES", "FEEDBACK_AZTEC", "LOGIN_EMAIL", "LOGIN_MAGIC_LINK", "LOGIN_EMAIL_PASSWORD", "LOGIN_2FA", "LOGIN_SITE_ADDRESS", "LOGIN_SOCIAL", "LOGIN_USERNAME_PASSWORD", "RELEASE_NOTES", "SIGNUP_EMAIL", "SIGNUP_MAGIC_LINK", "SIGNUP_CONFIRMATION", "SITE_CREATION_CREATING", "SITE_CREATION_SEGMENTS", "SITE_CREATION_VERTICALS", "SITE_CREATION_DOMAINS", "SITE_CREATION_SITE_INFO", "EDITOR_HELP", "SCAN_SCREEN_HELP", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Origin {
        /*public static final*/ UNKNOWN /* = new UNKNOWN(null) */,
        /*public static final*/ ZENDESK_NOTIFICATION /* = new ZENDESK_NOTIFICATION(null) */,
        /*public static final*/ LOGIN_SCREEN_WPCOM /* = new LOGIN_SCREEN_WPCOM(null) */,
        /*public static final*/ LOGIN_SCREEN_SELF_HOSTED /* = new LOGIN_SCREEN_SELF_HOSTED(null) */,
        /*public static final*/ LOGIN_SCREEN_JETPACK /* = new LOGIN_SCREEN_JETPACK(null) */,
        /*public static final*/ SIGNUP_SCREEN /* = new SIGNUP_SCREEN(null) */,
        /*public static final*/ ME_SCREEN_HELP /* = new ME_SCREEN_HELP(null) */,
        /*public static final*/ DELETE_SITE /* = new DELETE_SITE(null) */,
        /*public static final*/ DISCARD_CHANGES /* = new DISCARD_CHANGES(null) */,
        /*public static final*/ FEEDBACK_AZTEC /* = new FEEDBACK_AZTEC(null) */,
        /*public static final*/ LOGIN_EMAIL /* = new LOGIN_EMAIL(null) */,
        /*public static final*/ LOGIN_MAGIC_LINK /* = new LOGIN_MAGIC_LINK(null) */,
        /*public static final*/ LOGIN_EMAIL_PASSWORD /* = new LOGIN_EMAIL_PASSWORD(null) */,
        /*public static final*/ LOGIN_2FA /* = new LOGIN_2FA(null) */,
        /*public static final*/ LOGIN_SITE_ADDRESS /* = new LOGIN_SITE_ADDRESS(null) */,
        /*public static final*/ LOGIN_SOCIAL /* = new LOGIN_SOCIAL(null) */,
        /*public static final*/ LOGIN_USERNAME_PASSWORD /* = new LOGIN_USERNAME_PASSWORD(null) */,
        /*public static final*/ RELEASE_NOTES /* = new RELEASE_NOTES(null) */,
        /*public static final*/ SIGNUP_EMAIL /* = new SIGNUP_EMAIL(null) */,
        /*public static final*/ SIGNUP_MAGIC_LINK /* = new SIGNUP_MAGIC_LINK(null) */,
        /*public static final*/ SIGNUP_CONFIRMATION /* = new SIGNUP_CONFIRMATION(null) */,
        /*public static final*/ SITE_CREATION_CREATING /* = new SITE_CREATION_CREATING(null) */,
        /*public static final*/ SITE_CREATION_SEGMENTS /* = new SITE_CREATION_SEGMENTS(null) */,
        /*public static final*/ SITE_CREATION_VERTICALS /* = new SITE_CREATION_VERTICALS(null) */,
        /*public static final*/ SITE_CREATION_DOMAINS /* = new SITE_CREATION_DOMAINS(null) */,
        /*public static final*/ SITE_CREATION_SITE_INFO /* = new SITE_CREATION_SITE_INFO(null) */,
        /*public static final*/ EDITOR_HELP /* = new EDITOR_HELP(null) */,
        /*public static final*/ SCAN_SCREEN_HELP /* = new SCAN_SCREEN_HELP(null) */;
        private final java.lang.String stringValue = null;
        
        Origin(java.lang.String stringValue) {
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J2\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/accounts/HelpActivity$Companion;", "", "()V", "EXTRA_TAGS_KEY", "", "ORIGIN_KEY", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "origin", "Lorg/wordpress/android/ui/accounts/HelpActivity$Origin;", "selectedSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "extraSupportTags", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.accounts.HelpActivity.Origin origin, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.SiteModel selectedSite, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.String> extraSupportTags) {
            return null;
        }
    }
}
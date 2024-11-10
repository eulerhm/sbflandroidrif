package org.wordpress.android.ui.stats;

import java.lang.System;

/**
 * An activity that shows when user tries to open Stats without Jetpack connected.
 * It offers a link to the Jetpack connection flow.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010\u0019\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0012H\u0014J\b\u0010\u001e\u001a\u00020\u0012H\u0014J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!H\u0002J\f\u0010\"\u001a\u00020\u0012*\u00020#H\u0002J\f\u0010$\u001a\u00020\u0012*\u00020#H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/stats/StatsConnectJetpackActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "mAccountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "getMAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "setMAccountStore", "(Lorg/wordpress/android/fluxc/store/AccountStore;)V", "mDispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "getMDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "setMDispatcher", "(Lorg/wordpress/android/fluxc/Dispatcher;)V", "mIsJetpackConnectStarted", "", "checkAndContinueJetpackConnectionFlow", "", "savedInstanceState", "Landroid/os/Bundle;", "initDagger", "onAccountChanged", "event", "Lorg/wordpress/android/fluxc/store/AccountStore$OnAccountChanged;", "onCreate", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onStart", "onStop", "startJetpackConnectionFlow", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "initViews", "Lorg/wordpress/android/databinding/StatsJetpackConnectionActivityBinding;", "setActionBar", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsConnectJetpackActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    private boolean mIsJetpackConnectStarted = false;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.AccountStore mAccountStore;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.Dispatcher mDispatcher;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.StatsConnectJetpackActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_CONTINUE_JETPACK_CONNECT = "ARG_CONTINUE_JETPACK_CONNECT";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FAQ_URL = "https://wordpress.org/plugins/jetpack/#faq";
    
    public StatsConnectJetpackActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getMAccountStore() {
        return null;
    }
    
    public final void setMAccountStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getMDispatcher() {
        return null;
    }
    
    public final void setMDispatcher(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initDagger() {
    }
    
    private final void setActionBar(org.wordpress.android.databinding.StatsJetpackConnectionActivityBinding $this$setActionBar) {
    }
    
    /**
     * Continue Jetpack connect flow if coming from login/signup magic link.
     */
    private final void checkAndContinueJetpackConnectionFlow(android.os.Bundle savedInstanceState) {
    }
    
    private final void initViews(org.wordpress.android.databinding.StatsJetpackConnectionActivityBinding $this$initViews) {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void startJetpackConnectionFlow(org.wordpress.android.fluxc.model.SiteModel siteModel) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onAccountChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/stats/StatsConnectJetpackActivity$Companion;", "", "()V", "ARG_CONTINUE_JETPACK_CONNECT", "", "FAQ_URL", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
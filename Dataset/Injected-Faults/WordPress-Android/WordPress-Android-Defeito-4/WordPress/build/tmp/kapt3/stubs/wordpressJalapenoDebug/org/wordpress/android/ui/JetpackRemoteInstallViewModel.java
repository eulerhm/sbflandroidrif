package org.wordpress.android.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001.B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0014J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 H\u0007J\u000e\u0010!\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u0018H\u0002J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u0018H\u0002J\u0018\u0010$\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u00182\b\u0010%\u001a\u0004\u0018\u00010&J\u0010\u0010\'\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u0018H\u0002J\"\u0010(\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\b\b\u0002\u0010+\u001a\u00020,H\u0002J\u0016\u0010-\u001a\u00020\u0011*\u0004\u0018\u00010&2\u0006\u0010#\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewModel;", "Landroidx/lifecycle/ViewModel;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "jetpackStore", "Lorg/wordpress/android/fluxc/store/JetpackStore;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/JetpackStore;)V", "liveActionOnResult", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewModel$JetpackResultActionData;", "getLiveActionOnResult", "()Landroidx/lifecycle/LiveData;", "liveViewState", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState;", "getLiveViewState", "mutableActionOnResult", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "mutableViewState", "Landroidx/lifecycle/MutableLiveData;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "connect", "", "siteId", "", "onCleared", "onEventsUpdated", "event", "Lorg/wordpress/android/fluxc/store/JetpackStore$OnJetpackInstalled;", "onLogin", "restart", "site", "start", "type", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewState$Type;", "startRemoteInstall", "triggerResultAction", "action", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewModel$JetpackResultActionData$Action;", "hasAccessToken", "", "toState", "JetpackResultActionData", "org.wordpress.android_wordpressJalapenoDebug"})
public final class JetpackRemoteInstallViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    
    /**
     * JetpackStore needs to be injected here as otherwise FluxC doesn't accept emitted events.
     */
    private final org.wordpress.android.fluxc.store.JetpackStore jetpackStore = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.JetpackRemoteInstallViewState> mutableViewState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.JetpackRemoteInstallViewState> liveViewState = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData> mutableActionOnResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData> liveActionOnResult = null;
    private org.wordpress.android.fluxc.model.SiteModel siteModel;
    
    @javax.inject.Inject()
    public JetpackRemoteInstallViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"unused"})
    org.wordpress.android.fluxc.store.JetpackStore jetpackStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.JetpackRemoteInstallViewState> getLiveViewState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData> getLiveActionOnResult() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.JetpackRemoteInstallViewState.Type type) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void onLogin(int siteId) {
    }
    
    private final org.wordpress.android.ui.JetpackRemoteInstallViewState toState(org.wordpress.android.ui.JetpackRemoteInstallViewState.Type $this$toState, org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    private final void start(org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void restart(org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void connect(int siteId) {
    }
    
    private final void startRemoteInstall(org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final void triggerResultAction(int siteId, org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData.Action action, boolean hasAccessToken) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventsUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.JetpackStore.OnJetpackInstalled event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0019B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewModel$JetpackResultActionData;", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "loggedIn", "", "action", "Lorg/wordpress/android/ui/JetpackRemoteInstallViewModel$JetpackResultActionData$Action;", "(Lorg/wordpress/android/fluxc/model/SiteModel;ZLorg/wordpress/android/ui/JetpackRemoteInstallViewModel$JetpackResultActionData$Action;)V", "getAction", "()Lorg/wordpress/android/ui/JetpackRemoteInstallViewModel$JetpackResultActionData$Action;", "getLoggedIn", "()Z", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "Action", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class JetpackResultActionData {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        private final boolean loggedIn = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData.Action action = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, boolean loggedIn, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData.Action action) {
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
        
        public JetpackResultActionData(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, boolean loggedIn, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData.Action action) {
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
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getLoggedIn() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData.Action component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.JetpackRemoteInstallViewModel.JetpackResultActionData.Action getAction() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/JetpackRemoteInstallViewModel$JetpackResultActionData$Action;", "", "(Ljava/lang/String;I)V", "LOGIN", "MANUAL_INSTALL", "CONNECT", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum Action {
            /*public static final*/ LOGIN /* = new LOGIN() */,
            /*public static final*/ MANUAL_INSTALL /* = new MANUAL_INSTALL() */,
            /*public static final*/ CONNECT /* = new CONNECT() */;
            
            Action() {
            }
        }
    }
}
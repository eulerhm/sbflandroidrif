package org.wordpress.android.ui.jetpack;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\"B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u000f\u001a\u00020\u0010J\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u000bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u000bJ\u001f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u001c2\u0006\u0010\u0014\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u001d\u001a\u00020\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\rH\u0007J\u001e\u0010!\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R:\u0010\t\u001a.\u0012\u0004\u0012\u00020\u000b\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f0\nj\u0016\u0012\u0004\u0012\u00020\u000b\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f`\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/jetpack/JetpackCapabilitiesUseCase;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "continuation", "Ljava/util/HashMap;", "", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/SiteStore$OnJetpackCapabilitiesFetched;", "Lkotlin/collections/HashMap;", "clear", "", "fetchJetpackCapabilities", "", "Lorg/wordpress/android/fluxc/model/JetpackCapability;", "remoteSiteId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchJetpackPurchasedProducts", "Lorg/wordpress/android/ui/jetpack/JetpackCapabilitiesUseCase$JetpackPurchasedProducts;", "forceResumeDuplicateRequests", "getCachedJetpackCapabilities", "getCachedJetpackPurchasedProducts", "getJetpackPurchasedProducts", "Lkotlinx/coroutines/flow/Flow;", "mapToJetpackPurchasedProducts", "capabilities", "onJetpackCapabilitiesFetched", "event", "updateCache", "JetpackPurchasedProducts", "org.wordpress.android_wordpressJalapenoDebug"})
public final class JetpackCapabilitiesUseCase {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private java.util.HashMap<java.lang.Long, kotlin.coroutines.Continuation<org.wordpress.android.fluxc.store.SiteStore.OnJetpackCapabilitiesFetched>> continuation;
    
    @javax.inject.Inject()
    public JetpackCapabilitiesUseCase(@org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"unused"})
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper) {
        super();
    }
    
    public final void clear() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getJetpackPurchasedProducts(long remoteSiteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase.JetpackPurchasedProducts>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase.JetpackPurchasedProducts getCachedJetpackPurchasedProducts(long remoteSiteId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchJetpackPurchasedProducts(long remoteSiteId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase.JetpackPurchasedProducts> continuation) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase.JetpackPurchasedProducts mapToJetpackPurchasedProducts(java.util.List<? extends org.wordpress.android.fluxc.model.JetpackCapability> capabilities) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.JetpackCapability> getCachedJetpackCapabilities(long remoteSiteId) {
        return null;
    }
    
    private final java.lang.Object fetchJetpackCapabilities(long remoteSiteId, kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.fluxc.model.JetpackCapability>> continuation) {
        return null;
    }
    
    private final void forceResumeDuplicateRequests(long remoteSiteId) {
    }
    
    private final void updateCache(long remoteSiteId, java.util.List<? extends org.wordpress.android.fluxc.model.JetpackCapability> capabilities) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onJetpackCapabilitiesFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnJetpackCapabilitiesFetched event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/jetpack/JetpackCapabilitiesUseCase$JetpackPurchasedProducts;", "", "scan", "", "backup", "(ZZ)V", "getBackup", "()Z", "getScan", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class JetpackPurchasedProducts {
        private final boolean scan = false;
        private final boolean backup = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase.JetpackPurchasedProducts copy(boolean scan, boolean backup) {
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
        
        public JetpackPurchasedProducts(boolean scan, boolean backup) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getScan() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getBackup() {
            return false;
        }
    }
}
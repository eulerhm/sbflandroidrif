package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001#B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0019\u001a\u00020\u001aH\u0007J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0006\u0010 \u001a\u00020\u001aJ\u000e\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u000eR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/fluxc/Dispatcher;)V", "_siteChanged", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult;", "counter", "", "maxAttempts", "siteChanged", "Landroidx/lifecycle/LiveData;", "getSiteChanged", "()Landroidx/lifecycle/LiveData;", "<set-?>", "Lorg/wordpress/android/fluxc/model/SiteModel;", "siteModel", "getSiteModel", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "clear", "", "hasLoadedSite", "", "onSiteChanged", "event", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSiteChanged;", "reset", "start", "localSiteId", "SiteUpdateResult", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class StatsSiteProvider {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private org.wordpress.android.fluxc.model.SiteModel siteModel;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult>> _siteChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult>> siteChanged = null;
    private final int maxAttempts = 3;
    private int counter = 0;
    
    @javax.inject.Inject()
    public StatsSiteProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSiteModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult>> getSiteChanged() {
        return null;
    }
    
    public final boolean start(int localSiteId) {
        return false;
    }
    
    public final void reset() {
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    public final void clear() {
    }
    
    public final boolean hasLoadedSite() {
        return false;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSiteChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult;", "", "()V", "NotConnectedJetpackSite", "SiteConnected", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult$NotConnectedJetpackSite;", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult$SiteConnected;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SiteUpdateResult {
        
        private SiteUpdateResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult$NotConnectedJetpackSite;", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NotConnectedJetpackSite extends org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult.NotConnectedJetpackSite INSTANCE = null;
            
            private NotConnectedJetpackSite() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult$SiteConnected;", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult;", "siteId", "", "(J)V", "getSiteId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SiteConnected extends org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult {
            private final long siteId = 0L;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult.SiteConnected copy(long siteId) {
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
            
            public SiteConnected(long siteId) {
                super();
            }
            
            public final long component1() {
                return 0L;
            }
            
            public final long getSiteId() {
                return 0L;
            }
        }
    }
}
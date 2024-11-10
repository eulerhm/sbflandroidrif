package org.wordpress.android.ui.stats.refresh.lists.widget.configuration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001*B)\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\"\u001a\u00020\u000eJ\u0006\u0010#\u001a\u00020\u000eJ\u0010\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u0019H\u0002J\u000e\u0010&\u001a\u00020\u000e2\u0006\u0010\'\u001a\u00020\u0017J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010%\u001a\u00020)H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u001b0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0010R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u001b0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010\u00a8\u0006+"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsSiteSelectionViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "dialogOpened", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "getDialogOpened", "()Landroidx/lifecycle/LiveData;", "hideSiteDialog", "getHideSiteDialog", "mutableDialogOpened", "Landroidx/lifecycle/MutableLiveData;", "mutableHideSiteDialog", "mutableNotification", "", "mutableSelectedSite", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsSiteSelectionViewModel$SiteUiModel;", "mutableSites", "", "notification", "getNotification", "selectedSite", "getSelectedSite", "sites", "getSites", "loadSites", "openSiteDialog", "selectSite", "site", "start", "appWidgetId", "toUiModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "SiteUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsSiteSelectionViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel> mutableSelectedSite = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel> selectedSite = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel>> mutableSites = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel>> sites = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> mutableHideSiteDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> hideSiteDialog = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> mutableNotification = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> notification = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> mutableDialogOpened = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> dialogOpened = null;
    
    @javax.inject.Inject()
    public StatsSiteSelectionViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel> getSelectedSite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel>> getSites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getHideSiteDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> getNotification() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getDialogOpened() {
        return null;
    }
    
    public final void start(int appWidgetId) {
    }
    
    public final void loadSites() {
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel toUiModel(org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    private final void selectSite(org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel site) {
    }
    
    public final void openSiteDialog() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BN\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0000\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u0015\u001a\u00020\rJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J$\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0000\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tH\u00c2\u0003J\\\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052#\b\u0002\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0000\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R)\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0000\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsSiteSelectionViewModel$SiteUiModel;", "", "siteId", "", "iconUrl", "", "title", "url", "onClick", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "site", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getIconUrl", "()Ljava/lang/String;", "getSiteId", "()J", "getTitle", "getUrl", "click", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteUiModel {
        private final long siteId = 0L;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String iconUrl = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String url = null;
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel, kotlin.Unit> onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel copy(long siteId, @org.jetbrains.annotations.Nullable()
        java.lang.String iconUrl, @org.jetbrains.annotations.Nullable()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel, kotlin.Unit> onClick) {
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
        
        public SiteUiModel(long siteId, @org.jetbrains.annotations.Nullable()
        java.lang.String iconUrl, @org.jetbrains.annotations.Nullable()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel, kotlin.Unit> onClick) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getSiteId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getIconUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getUrl() {
            return null;
        }
        
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel.SiteUiModel, kotlin.Unit> component5() {
            return null;
        }
        
        public final void click() {
        }
    }
}
package org.wordpress.android.viewmodel.plugins;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002\u0080\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020#H\u0002J(\u0010R\u001a\b\u0012\u0004\u0012\u00020\f0S2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\f0U2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\f0UJ\u000e\u0010Q\u001a\u00020N2\u0006\u0010O\u001a\u00020PJ\b\u0010W\u001a\u00020NH\u0014J\u0010\u0010X\u001a\u00020N2\u0006\u0010Y\u001a\u00020ZH\u0007J\u0010\u0010[\u001a\u00020N2\u0006\u0010Y\u001a\u00020\\H\u0007J\u0010\u0010]\u001a\u00020N2\u0006\u0010Y\u001a\u00020^H\u0007J\u0010\u0010_\u001a\u00020N2\u0006\u0010Y\u001a\u00020`H\u0007J\u0010\u0010a\u001a\u00020N2\u0006\u0010Y\u001a\u00020bH\u0007J\u0010\u0010c\u001a\u00020N2\u0006\u0010Y\u001a\u00020dH\u0007J\u0010\u0010e\u001a\u00020N2\u0006\u0010Y\u001a\u00020fH\u0007J\u0010\u0010g\u001a\u00020N2\u0006\u0010Y\u001a\u00020hH\u0007J\u000e\u0010i\u001a\u00020N2\u0006\u0010j\u001a\u00020PJ\u000e\u0010k\u001a\u00020N2\u0006\u0010l\u001a\u00020mJ\u0010\u0010n\u001a\u00020N2\b\u0010K\u001a\u0004\u0018\u00010\u0012J\u0018\u0010o\u001a\u00020#2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020#H\u0002J\u0006\u0010p\u001a\u00020#J\u0006\u0010q\u001a\u00020NJ\u0018\u0010r\u001a\u00020N2\u0006\u0010s\u001a\u00020\u00122\u0006\u0010t\u001a\u00020#H\u0002J\u0012\u0010u\u001a\u00020N2\b\u0010v\u001a\u0004\u0018\u00010\u0012H\u0002J\u001a\u0010w\u001a\u00020N2\u0006\u0010x\u001a\u00020y2\b\u0010z\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010{\u001a\u00020N2\u0006\u0010x\u001a\u00020yH\u0002J\u0018\u0010|\u001a\u00020N2\u0006\u0010x\u001a\u00020y2\u0006\u0010}\u001a\u00020#H\u0002J\u000e\u0010~\u001a\u00020N2\u0006\u0010\u007f\u001a\u00020mR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R7\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R!\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\u001d0\u001c8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R7\u0010$\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\'\u0010\u001a\u001a\u0004\b%\u0010\u0016\"\u0004\b&\u0010\u0018R!\u0010(\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\u001d0\u001c8F\u00a2\u0006\u0006\u001a\u0004\b)\u0010\u001fR7\u0010*\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b-\u0010\u001a\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010\u0018R!\u0010.\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\u001d0\u001c8F\u00a2\u0006\u0006\u001a\u0004\b/\u0010\u001fR+\u00100\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00128F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b5\u0010\u001a\u001a\u0004\b1\u00102\"\u0004\b3\u00104R7\u00106\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b9\u0010\u001a\u001a\u0004\b7\u0010\u0016\"\u0004\b8\u0010\u0018R!\u0010:\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\u001d0\u001c8F\u00a2\u0006\u0006\u001a\u0004\b;\u0010\u001fR\u0014\u0010<\u001a\u00020#8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020@X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR7\u0010E\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\bH\u0010\u001a\u001a\u0004\bF\u0010\u0016\"\u0004\bG\u0010\u0018R!\u0010I\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`\u001d0\u001c8F\u00a2\u0006\u0006\u001a\u0004\bJ\u0010\u001fR\u0017\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00120\u001c8F\u00a2\u0006\u0006\u001a\u0004\bL\u0010\u001f\u00a8\u0006\u0081\u0001"}, d2 = {"Lorg/wordpress/android/viewmodel/plugins/PluginBrowserViewModel;", "Landroidx/lifecycle/ViewModel;", "mDispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "mPluginStore", "Lorg/wordpress/android/fluxc/store/PluginStore;", "mSiteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/PluginStore;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "_featuredPluginsLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/models/networkresource/ListState;", "Lorg/wordpress/android/fluxc/model/plugin/ImmutablePluginModel;", "_newPluginsLiveData", "_popularPluginsLiveData", "_searchResultsLiveData", "_sitePluginsLiveData", "_title", "", "<set-?>", "featuredPlugins", "getFeaturedPlugins", "()Lorg/wordpress/android/models/networkresource/ListState;", "setFeaturedPlugins", "(Lorg/wordpress/android/models/networkresource/ListState;)V", "featuredPlugins$delegate", "Lkotlin/properties/ReadWriteProperty;", "featuredPluginsLiveData", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/plugins/PluginListState;", "getFeaturedPluginsLiveData", "()Landroidx/lifecycle/LiveData;", "handler", "Landroid/os/Handler;", "isStarted", "", "newPlugins", "getNewPlugins", "setNewPlugins", "newPlugins$delegate", "newPluginsLiveData", "getNewPluginsLiveData", "popularPlugins", "getPopularPlugins", "setPopularPlugins", "popularPlugins$delegate", "popularPluginsLiveData", "getPopularPluginsLiveData", "searchQuery", "getSearchQuery", "()Ljava/lang/String;", "setSearchQuery", "(Ljava/lang/String;)V", "searchQuery$delegate", "searchResults", "getSearchResults", "setSearchResults", "searchResults$delegate", "searchResultsLiveData", "getSearchResultsLiveData", "shouldSearch", "getShouldSearch", "()Z", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSite", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "sitePlugins", "getSitePlugins", "setSitePlugins", "sitePlugins$delegate", "sitePluginsLiveData", "getSitePluginsLiveData", "title", "getTitle", "fetchPlugins", "", "listType", "Lorg/wordpress/android/viewmodel/plugins/PluginBrowserViewModel$PluginListType;", "loadMore", "getDiffCallback", "Lorg/wordpress/android/ui/ListDiffCallback;", "oldList", "", "newList", "onCleared", "onPluginDirectoryFetched", "event", "Lorg/wordpress/android/fluxc/store/PluginStore$OnPluginDirectoryFetched;", "onPluginDirectorySearched", "Lorg/wordpress/android/fluxc/store/PluginStore$OnPluginDirectorySearched;", "onSiteChanged", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSiteChanged;", "onSitePluginConfigured", "Lorg/wordpress/android/fluxc/store/PluginStore$OnSitePluginConfigured;", "onSitePluginDeleted", "Lorg/wordpress/android/fluxc/store/PluginStore$OnSitePluginDeleted;", "onSitePluginInstalled", "Lorg/wordpress/android/fluxc/store/PluginStore$OnSitePluginInstalled;", "onSitePluginUpdated", "Lorg/wordpress/android/fluxc/store/PluginStore$OnSitePluginUpdated;", "onWPOrgPluginFetched", "Lorg/wordpress/android/fluxc/store/PluginStore$OnWPOrgPluginFetched;", "pullToRefresh", "pluginListType", "readFromBundle", "savedInstanceState", "Landroid/os/Bundle;", "setTitle", "shouldFetchPlugins", "shouldShowEmptySearchResultsView", "start", "submitSearch", "query", "delayed", "updateAllPluginListsForSlug", "slug", "updateListStateToError", "directoryType", "Lorg/wordpress/android/fluxc/model/plugin/PluginDirectoryType;", "errorMessage", "updateListStateToReady", "updateListStateToSuccess", "canLoadMore", "writeToBundle", "outState", "PluginListType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PluginBrowserViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.fluxc.Dispatcher mDispatcher = null;
    private final org.wordpress.android.fluxc.store.PluginStore mPluginStore = null;
    private final org.wordpress.android.fluxc.store.SiteStore mSiteStore = null;
    private boolean isStarted = false;
    private final android.os.Handler handler = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> _featuredPluginsLiveData = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> _popularPluginsLiveData = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> _newPluginsLiveData = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> _sitePluginsLiveData = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> _searchResultsLiveData = null;
    private final kotlin.properties.ReadWriteProperty featuredPlugins$delegate = null;
    private final kotlin.properties.ReadWriteProperty popularPlugins$delegate = null;
    private final kotlin.properties.ReadWriteProperty newPlugins$delegate = null;
    private final kotlin.properties.ReadWriteProperty sitePlugins$delegate = null;
    private final kotlin.properties.ReadWriteProperty searchResults$delegate = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _title = null;
    public org.wordpress.android.fluxc.model.SiteModel site;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.properties.ReadWriteProperty searchQuery$delegate = null;
    
    @javax.inject.Inject()
    public PluginBrowserViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher mDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PluginStore mPluginStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore mSiteStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> getFeaturedPluginsLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> getPopularPluginsLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> getNewPluginsLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> getSitePluginsLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel>> getSearchResultsLiveData() {
        return null;
    }
    
    private final org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> getFeaturedPlugins() {
        return null;
    }
    
    private final void setFeaturedPlugins(org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> p0) {
    }
    
    private final org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> getPopularPlugins() {
        return null;
    }
    
    private final void setPopularPlugins(org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> p0) {
    }
    
    private final org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> getNewPlugins() {
        return null;
    }
    
    private final void setNewPlugins(org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> p0) {
    }
    
    private final org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> getSitePlugins() {
        return null;
    }
    
    private final void setSitePlugins(org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> p0) {
    }
    
    private final org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> getSearchResults() {
        return null;
    }
    
    private final void setSearchResults(org.wordpress.android.models.networkresource.ListState<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    public final void setSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSearchQuery() {
        return null;
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    private final boolean getShouldSearch() {
        return false;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void writeToBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void readFromBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void start() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.ListDiffCallback<org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> getDiffCallback(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> oldList, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.fluxc.model.plugin.ImmutablePluginModel> newList) {
        return null;
    }
    
    public final void loadMore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.plugins.PluginBrowserViewModel.PluginListType listType) {
    }
    
    public final void pullToRefresh(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.plugins.PluginBrowserViewModel.PluginListType pluginListType) {
    }
    
    private final void fetchPlugins(org.wordpress.android.viewmodel.plugins.PluginBrowserViewModel.PluginListType listType, boolean loadMore) {
    }
    
    private final boolean shouldFetchPlugins(org.wordpress.android.viewmodel.plugins.PluginBrowserViewModel.PluginListType listType, boolean loadMore) {
        return false;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onWPOrgPluginFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PluginStore.OnWPOrgPluginFetched event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onPluginDirectoryFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PluginStore.OnPluginDirectoryFetched event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onPluginDirectorySearched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PluginStore.OnPluginDirectorySearched event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSitePluginConfigured(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PluginStore.OnSitePluginConfigured event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSitePluginDeleted(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PluginStore.OnSitePluginDeleted event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSitePluginInstalled(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PluginStore.OnSitePluginInstalled event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSitePluginUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PluginStore.OnSitePluginUpdated event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onSiteChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged event) {
    }
    
    private final void updateAllPluginListsForSlug(java.lang.String slug) {
    }
    
    private final void submitSearch(java.lang.String query, boolean delayed) {
    }
    
    public final boolean shouldShowEmptySearchResultsView() {
        return false;
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable()
    java.lang.String title) {
    }
    
    private final void updateListStateToReady(org.wordpress.android.fluxc.model.plugin.PluginDirectoryType directoryType) {
    }
    
    private final void updateListStateToSuccess(org.wordpress.android.fluxc.model.plugin.PluginDirectoryType directoryType, boolean canLoadMore) {
    }
    
    private final void updateListStateToError(org.wordpress.android.fluxc.model.plugin.PluginDirectoryType directoryType, java.lang.String errorMessage) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/viewmodel/plugins/PluginBrowserViewModel$PluginListType;", "", "(Ljava/lang/String;I)V", "SITE", "FEATURED", "POPULAR", "NEW", "SEARCH", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PluginListType {
        /*public static final*/ SITE /* = new SITE() */,
        /*public static final*/ FEATURED /* = new FEATURED() */,
        /*public static final*/ POPULAR /* = new POPULAR() */,
        /*public static final*/ NEW /* = new NEW() */,
        /*public static final*/ SEARCH /* = new SEARCH() */;
        
        PluginListType() {
        }
    }
}
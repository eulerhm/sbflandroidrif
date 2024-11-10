package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 f2\u00020\u0001:\u0001fBA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\u001aH\u0002J\b\u0010Q\u001a\u00020RH\u0002J\b\u0010S\u001a\u00020RH\u0002J\b\u0010T\u001a\u00020RH\u0002J\b\u0010U\u001a\u00020RH\u0014J\u0010\u0010V\u001a\u00020R2\b\u0010P\u001a\u0004\u0018\u00010\u001aJ\u0010\u0010W\u001a\u00020R2\u0006\u0010X\u001a\u00020YH\u0007J\u0006\u0010Z\u001a\u00020RJ\u0010\u0010[\u001a\u00020R2\u0006\u0010P\u001a\u00020\u001aH\u0002J\b\u0010\\\u001a\u00020RH\u0002J\u0010\u0010]\u001a\u00020R2\u0006\u0010^\u001a\u00020\u0013H\u0002J\u0016\u0010_\u001a\u00020R2\u0006\u0010@\u001a\u00020A2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010`\u001a\u00020R2\u0006\u0010a\u001a\u00020\u001cJ\u0011\u0010b\u001a\u00020\u0013*\u00020/H\u0000\u00a2\u0006\u0002\bcJ\u0011\u0010d\u001a\u00020\u001c*\u00020/H\u0000\u00a2\u0006\u0002\beR\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00130\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00130\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001e0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130&\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\'R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130&\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\'R\u000e\u0010)\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170&\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\'R\"\u0010-\u001a\n\u0012\u0004\u0012\u00020/\u0018\u00010.X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u00105\u001a\u00020\u001c2\u0006\u00104\u001a\u00020\u001c8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001f\u0010<\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00130\u00130&\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010\'R\u0019\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0&\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010\'R\u001a\u0010@\u001a\u00020AX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R7\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001e2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001e8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\bK\u0010;\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001d\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001e0&\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u0010\'\u00a8\u0006g"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainSuggestionsViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "productsStore", "Lorg/wordpress/android/fluxc/store/ProductsStore;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "debouncer", "Lorg/wordpress/android/util/helpers/Debouncer;", "siteDomainsFeatureConfig", "Lorg/wordpress/android/util/config/SiteDomainsFeatureConfig;", "createCartUseCase", "Lorg/wordpress/android/ui/domains/usecases/CreateCartUseCase;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/ProductsStore;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/util/helpers/Debouncer;Lorg/wordpress/android/util/config/SiteDomainsFeatureConfig;Lorg/wordpress/android/ui/domains/usecases/CreateCartUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_isButtonProgressBarVisible", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_isIntroVisible", "_onDomainSelected", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/domains/DomainProductDetails;", "_selectedSuggestion", "Lorg/wordpress/android/ui/domains/DomainSuggestionItem;", "_showRedirectMessage", "", "_suggestions", "Lorg/wordpress/android/models/networkresource/ListState;", "domainRegistrationPurpose", "Lorg/wordpress/android/ui/domains/DomainRegistrationActivity$DomainRegistrationPurpose;", "getDomainRegistrationPurpose", "()Lorg/wordpress/android/ui/domains/DomainRegistrationActivity$DomainRegistrationPurpose;", "setDomainRegistrationPurpose", "(Lorg/wordpress/android/ui/domains/DomainRegistrationActivity$DomainRegistrationPurpose;)V", "isButtonProgressBarVisible", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "isIntroVisible", "isQueryTrackingCompleted", "isStarted", "onDomainSelected", "getOnDomainSelected", "products", "", "Lorg/wordpress/android/fluxc/model/products/Product;", "getProducts", "()Ljava/util/List;", "setProducts", "(Ljava/util/List;)V", "<set-?>", "searchQuery", "getSearchQuery", "()Ljava/lang/String;", "setSearchQuery", "(Ljava/lang/String;)V", "searchQuery$delegate", "Lkotlin/properties/ReadWriteProperty;", "selectDomainButtonEnabledState", "getSelectDomainButtonEnabledState", "showRedirectMessage", "getShowRedirectMessage", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSite", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "suggestions", "getSuggestions", "()Lorg/wordpress/android/models/networkresource/ListState;", "setSuggestions", "(Lorg/wordpress/android/models/networkresource/ListState;)V", "suggestions$delegate", "suggestionsLiveData", "getSuggestionsLiveData", "createCart", "Lkotlinx/coroutines/Job;", "selectedSuggestion", "fetchProducts", "", "fetchSuggestions", "initializeDefaultSuggestions", "onCleared", "onDomainSuggestionSelected", "onDomainSuggestionsFetched", "event", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSuggestedDomains;", "onSelectDomainButtonClicked", "selectDomain", "shouldShowRedirectMessage", "showLoadingButton", "isLoading", "start", "updateSearchQuery", "query", "isSaleDomain", "isSaleDomain$org_wordpress_android_wordpressJalapenoDebug", "saleCostForDisplay", "saleCostForDisplay$org_wordpress_android_wordpressJalapenoDebug", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainSuggestionsViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.store.ProductsStore productsStore = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.util.helpers.Debouncer debouncer = null;
    private final org.wordpress.android.util.config.SiteDomainsFeatureConfig siteDomainsFeatureConfig = null;
    private final org.wordpress.android.ui.domains.usecases.CreateCartUseCase createCartUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    public org.wordpress.android.fluxc.model.SiteModel site;
    public org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose domainRegistrationPurpose;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<org.wordpress.android.fluxc.model.products.Product> products;
    private boolean isStarted = false;
    private boolean isQueryTrackingCompleted = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.ui.domains.DomainSuggestionItem>> _suggestions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.ui.domains.DomainSuggestionItem>> suggestionsLiveData = null;
    private final kotlin.properties.ReadWriteProperty suggestions$delegate = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.domains.DomainSuggestionItem> _selectedSuggestion = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> selectDomainButtonEnabledState = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isIntroVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isIntroVisible = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _showRedirectMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> showRedirectMessage = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isButtonProgressBarVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isButtonProgressBarVisible = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainProductDetails>> _onDomainSelected = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainProductDetails>> onDomainSelected = null;
    private final kotlin.properties.ReadWriteProperty searchQuery$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.domains.DomainSuggestionsViewModel.Companion Companion = null;
    private static final long SEARCH_QUERY_DELAY_MS = 250L;
    private static final int SUGGESTIONS_REQUEST_COUNT = 20;
    private static final java.lang.String BLOG_DOMAIN_TLDS = "blog";
    
    @javax.inject.Inject()
    public DomainSuggestionsViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ProductsStore productsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.helpers.Debouncer debouncer, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SiteDomainsFeatureConfig siteDomainsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.usecases.CreateCartUseCase createCartUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    public final void setSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose getDomainRegistrationPurpose() {
        return null;
    }
    
    public final void setDomainRegistrationPurpose(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<org.wordpress.android.fluxc.model.products.Product> getProducts() {
        return null;
    }
    
    public final void setProducts(@org.jetbrains.annotations.Nullable()
    java.util.List<org.wordpress.android.fluxc.model.products.Product> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.models.networkresource.ListState<org.wordpress.android.ui.domains.DomainSuggestionItem>> getSuggestionsLiveData() {
        return null;
    }
    
    private final org.wordpress.android.models.networkresource.ListState<org.wordpress.android.ui.domains.DomainSuggestionItem> getSuggestions() {
        return null;
    }
    
    private final void setSuggestions(org.wordpress.android.models.networkresource.ListState<org.wordpress.android.ui.domains.DomainSuggestionItem> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getSelectDomainButtonEnabledState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isIntroVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getShowRedirectMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isButtonProgressBarVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainProductDetails>> getOnDomainSelected() {
        return null;
    }
    
    private final java.lang.String getSearchQuery() {
        return null;
    }
    
    private final void setSearchQuery(java.lang.String p0) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose domainRegistrationPurpose) {
    }
    
    private final void initializeDefaultSuggestions() {
    }
    
    private final void shouldShowRedirectMessage() {
    }
    
    private final void fetchProducts() {
    }
    
    private final void fetchSuggestions() {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onDomainSuggestionsFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnSuggestedDomains event) {
    }
    
    public final void onDomainSuggestionSelected(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.domains.DomainSuggestionItem selectedSuggestion) {
    }
    
    public final void onSelectDomainButtonClicked() {
    }
    
    public final void updateSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final boolean isSaleDomain$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.products.Product $this$isSaleDomain) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String saleCostForDisplay$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.products.Product $this$saleCostForDisplay) {
        return null;
    }
    
    private final kotlinx.coroutines.Job createCart(org.wordpress.android.ui.domains.DomainSuggestionItem selectedSuggestion) {
        return null;
    }
    
    private final void selectDomain(org.wordpress.android.ui.domains.DomainSuggestionItem selectedSuggestion) {
    }
    
    private final void showLoadingButton(boolean isLoading) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainSuggestionsViewModel$Companion;", "", "()V", "BLOG_DOMAIN_TLDS", "", "SEARCH_QUERY_DELAY_MS", "", "SUGGESTIONS_REQUEST_COUNT", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}
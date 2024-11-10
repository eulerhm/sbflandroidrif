package org.wordpress.android.ui.sitecreation.domains;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ca\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0003fghBC\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0001\u0010\r\u001a\u00020\u000e\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u0010J*\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010&2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00150.2\b\u0010?\u001a\u0004\u0018\u00010@H\u0002J\u0012\u0010A\u001a\u0004\u0018\u00010B2\u0006\u0010C\u001a\u00020*H\u0002J(\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020*2\u0006\u0010G\u001a\u00020*2\u0006\u0010H\u001a\u00020*2\u0006\u0010I\u001a\u00020*H\u0002J\u0006\u0010!\u001a\u00020\u0013JM\u0010J\u001a\b\u0012\u0004\u0012\u00020L0K2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00130N2\b\u0010=\u001a\u0004\u0018\u00010\u00152\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00150K2\u0006\u0010P\u001a\u00020*2\n\b\u0001\u0010Q\u001a\u0004\u0018\u00010RH\u0002\u00a2\u0006\u0002\u0010SJ\u0010\u0010T\u001a\u00020\u00132\u0006\u0010=\u001a\u00020&H\u0002J \u0010U\u001a\u0004\u0018\u00010V2\u0006\u0010=\u001a\u00020\u00152\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00150KH\u0002J\u0012\u0010X\u001a\u00020*2\b\u0010=\u001a\u0004\u0018\u00010&H\u0002J\u0006\u0010Y\u001a\u00020\u0013J\b\u0010Z\u001a\u00020\u0013H\u0014J\u0018\u0010[\u001a\u00020\u00132\u0006\u0010=\u001a\u00020&2\u0006\u0010\\\u001a\u00020]H\u0002J\u0006\u0010/\u001a\u00020\u0013J\b\u0010^\u001a\u00020\u0013H\u0002J\u0010\u0010_\u001a\u00020\u00132\u0006\u0010`\u001a\u00020\u0015H\u0007J\u0006\u0010a\u001a\u00020\u0013J\u000e\u0010b\u001a\u00020\u00132\u0006\u0010=\u001a\u00020\u0015J\u0012\u0010c\u001a\u00020\u00132\b\u0010=\u001a\u0004\u0018\u00010&H\u0002J\u0010\u0010d\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u0019H\u0002J,\u0010e\u001a\u00020\u00132\b\u0010=\u001a\u0004\u0018\u00010&2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00150.2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010@H\u0002R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00150.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00130\"\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010$R/\u00102\u001a\u0004\u0018\u00010\u00152\b\u00101\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020\u00190\"\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010$\u00a8\u0006i"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel;", "Landroidx/lifecycle/ViewModel;", "Lkotlinx/coroutines/CoroutineScope;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "domainSanitizer", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainSanitizer;", "fetchDomainsUseCase", "Lorg/wordpress/android/ui/sitecreation/usecases/FetchDomainsUseCase;", "tracker", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainSanitizer;Lorg/wordpress/android/ui/sitecreation/usecases/FetchDomainsUseCase;Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_clearBtnClicked", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_createSiteBtnClicked", "", "_onHelpClicked", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState;", "clearBtnClicked", "getClearBtnClicked", "()Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "createSiteBtnClicked", "Landroidx/lifecycle/LiveData;", "getCreateSiteBtnClicked", "()Landroidx/lifecycle/LiveData;", "currentQuery", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainSuggestionsQuery;", "fetchDomainsJob", "Lkotlinx/coroutines/Job;", "isStarted", "", "job", "Lkotlinx/coroutines/CompletableJob;", "listState", "Lorg/wordpress/android/models/networkresource/ListState;", "onHelpClicked", "getOnHelpClicked", "<set-?>", "selectedDomain", "getSelectedDomain", "()Ljava/lang/String;", "setSelectedDomain", "(Ljava/lang/String;)V", "selectedDomain$delegate", "Lkotlin/properties/ReadWriteProperty;", "uiState", "getUiState", "createDomainsUiContentState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;", "query", "state", "emptyListMessage", "Lorg/wordpress/android/ui/utils/UiString;", "createHeaderUiState", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationHeaderUiState;", "isVisible", "createSearchInputUiState", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationSearchInputUiState;", "showProgress", "showClearButton", "showDivider", "showKeyboard", "createSuggestionsUiStates", "", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "onRetry", "Lkotlin/Function0;", "data", "errorFetchingSuggestions", "errorResId", "", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;Ljava/util/List;ZLjava/lang/Integer;)Ljava/util/List;", "fetchDomains", "getDomainUnavailableUiState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState;", "domains", "isNonEmptyUserQuery", "onClearTextBtnClicked", "onCleared", "onDomainsFetched", "event", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSuggestedDomains;", "resetUiState", "setSelectedDomainName", "domainName", "start", "updateQuery", "updateQueryInternal", "updateUiState", "updateUiStateToContent", "DomainSuggestionsQuery", "DomainsListItemUiState", "DomainsUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteCreationDomainsViewModel extends androidx.lifecycle.ViewModel implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainSanitizer domainSanitizer = null;
    private final org.wordpress.android.ui.sitecreation.usecases.FetchDomainsUseCase fetchDomainsUseCase = null;
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CompletableJob job = null;
    private kotlinx.coroutines.Job fetchDomainsJob;
    private boolean isStarted = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState> uiState = null;
    private org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainSuggestionsQuery currentQuery;
    private org.wordpress.android.models.networkresource.ListState<java.lang.String> listState;
    private final kotlin.properties.ReadWriteProperty selectedDomain$delegate = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.String> _createSiteBtnClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> createSiteBtnClicked = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _clearBtnClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> clearBtnClicked = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onHelpClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onHelpClicked = null;
    
    @javax.inject.Inject()
    public SiteCreationDomainsViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainSanitizer domainSanitizer, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.usecases.FetchDomainsUseCase fetchDomainsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState> getUiState() {
        return null;
    }
    
    private final java.lang.String getSelectedDomain() {
        return null;
    }
    
    private final void setSelectedDomain(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getCreateSiteBtnClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> getClearBtnClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnHelpClicked() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void start() {
    }
    
    public final void createSiteBtnClicked() {
    }
    
    public final void onClearTextBtnClicked() {
    }
    
    public final void onHelpClicked() {
    }
    
    public final void updateQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    private final void updateQueryInternal(org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainSuggestionsQuery query) {
    }
    
    private final void resetUiState() {
    }
    
    private final void fetchDomains(org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainSuggestionsQuery query) {
    }
    
    private final void onDomainsFetched(org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainSuggestionsQuery query, org.wordpress.android.fluxc.store.SiteStore.OnSuggestedDomains event) {
    }
    
    private final void updateUiStateToContent(org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainSuggestionsQuery query, org.wordpress.android.models.networkresource.ListState<java.lang.String> state, org.wordpress.android.ui.utils.UiString emptyListMessage) {
    }
    
    private final void updateUiState(org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState uiState) {
    }
    
    private final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState createDomainsUiContentState(org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainSuggestionsQuery query, org.wordpress.android.models.networkresource.ListState<java.lang.String> state, org.wordpress.android.ui.utils.UiString emptyListMessage) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState> createSuggestionsUiStates(kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, java.lang.String query, java.util.List<java.lang.String> data, boolean errorFetchingSuggestions, @androidx.annotation.StringRes()
    java.lang.Integer errorResId) {
        return null;
    }
    
    private final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState.DomainsModelUiState getDomainUnavailableUiState(java.lang.String query, java.util.List<java.lang.String> domains) {
        return null;
    }
    
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationHeaderUiState createHeaderUiState(boolean isVisible) {
        return null;
    }
    
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationSearchInputUiState createSearchInputUiState(boolean showProgress, boolean showClearButton, boolean showDivider, boolean showKeyboard) {
        return null;
    }
    
    @androidx.annotation.VisibleForTesting(otherwise = androidx.annotation.VisibleForTesting.PRIVATE)
    public final void setSelectedDomainName(@org.jetbrains.annotations.NotNull()
    java.lang.String domainName) {
    }
    
    private final boolean isNonEmptyUserQuery(org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainSuggestionsQuery query) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001eB)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\tH\u00c6\u0003J3\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState;", "", "headerUiState", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationHeaderUiState;", "searchInputUiState", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationSearchInputUiState;", "contentState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;", "createSiteButtonContainerVisibility", "", "(Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationHeaderUiState;Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationSearchInputUiState;Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;Z)V", "getContentState", "()Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;", "getCreateSiteButtonContainerVisibility", "()Z", "getHeaderUiState", "()Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationHeaderUiState;", "getSearchInputUiState", "()Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationSearchInputUiState;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "DomainsUiContentState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DomainsUiState {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.sitecreation.misc.SiteCreationHeaderUiState headerUiState = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.sitecreation.misc.SiteCreationSearchInputUiState searchInputUiState = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState contentState = null;
        private final boolean createSiteButtonContainerVisibility = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.sitecreation.misc.SiteCreationHeaderUiState headerUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.misc.SiteCreationSearchInputUiState searchInputUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState contentState, boolean createSiteButtonContainerVisibility) {
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
        
        public DomainsUiState(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.sitecreation.misc.SiteCreationHeaderUiState headerUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.misc.SiteCreationSearchInputUiState searchInputUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState contentState, boolean createSiteButtonContainerVisibility) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.sitecreation.misc.SiteCreationHeaderUiState component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.sitecreation.misc.SiteCreationHeaderUiState getHeaderUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.misc.SiteCreationSearchInputUiState component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.misc.SiteCreationSearchInputUiState getSearchInputUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState getContentState() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getCreateSiteButtonContainerVisibility() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000e\u000f\u0010B%\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0001\u0003\u0011\u0012\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;", "", "emptyViewVisibility", "", "exampleViewVisibility", "items", "", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "(ZZLjava/util/List;)V", "getEmptyViewVisibility", "()Z", "getExampleViewVisibility", "getItems", "()Ljava/util/List;", "Empty", "Initial", "VisibleItems", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState$Initial;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState$Empty;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState$VisibleItems;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class DomainsUiContentState {
            private final boolean emptyViewVisibility = false;
            private final boolean exampleViewVisibility = false;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState> items = null;
            
            private DomainsUiContentState(boolean emptyViewVisibility, boolean exampleViewVisibility, java.util.List<? extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState> items) {
                super();
            }
            
            public final boolean getEmptyViewVisibility() {
                return false;
            }
            
            public final boolean getExampleViewVisibility() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState> getItems() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState$Initial;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class Initial extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState.Initial INSTANCE = null;
                
                private Initial() {
                    super(false, false, null);
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState$Empty;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;", "message", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class Empty extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState {
                @org.jetbrains.annotations.Nullable()
                private final org.wordpress.android.ui.utils.UiString message = null;
                
                public Empty(@org.jetbrains.annotations.Nullable()
                org.wordpress.android.ui.utils.UiString message) {
                    super(false, false, null);
                }
                
                @org.jetbrains.annotations.Nullable()
                public final org.wordpress.android.ui.utils.UiString getMessage() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState$VisibleItems;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsUiState$DomainsUiContentState;", "items", "", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "(Ljava/util/List;)V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class VisibleItems extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsUiState.DomainsUiContentState {
                
                public VisibleItems(@org.jetbrains.annotations.NotNull()
                java.util.List<? extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState> items) {
                    super(false, false, null);
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000e\u000fB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0082\u0001\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "", "()V", "clickable", "", "getClickable", "()Z", "onItemTapped", "Lkotlin/Function0;", "", "getOnItemTapped", "()Lkotlin/jvm/functions/Function0;", "setOnItemTapped", "(Lkotlin/jvm/functions/Function0;)V", "DomainsFetchSuggestionsErrorUiState", "DomainsModelUiState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsFetchSuggestionsErrorUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class DomainsListItemUiState {
        @org.jetbrains.annotations.Nullable()
        private kotlin.jvm.functions.Function0<kotlin.Unit> onItemTapped;
        private final boolean clickable = false;
        
        private DomainsListItemUiState() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnItemTapped() {
            return null;
        }
        
        public final void setOnItemTapped(@org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
        }
        
        public boolean getClickable() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B;\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\n\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u0082\u0001\u0002\u0017\u0018\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "name", "", "domain", "checked", "", "radioButtonVisibility", "subTitle", "Lorg/wordpress/android/ui/utils/UiString;", "clickable", "(Ljava/lang/String;Ljava/lang/String;ZZLorg/wordpress/android/ui/utils/UiString;Z)V", "getChecked", "()Z", "getClickable", "getDomain", "()Ljava/lang/String;", "getName", "getRadioButtonVisibility", "getSubTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "DomainsModelAvailableUiState", "DomainsModelUnavailabilityUiState", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState$DomainsModelAvailableUiState;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState$DomainsModelUnavailabilityUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class DomainsModelUiState extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String name = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String domain = null;
            private final boolean checked = false;
            private final boolean radioButtonVisibility = false;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString subTitle = null;
            private final boolean clickable = false;
            
            private DomainsModelUiState(java.lang.String name, java.lang.String domain, boolean checked, boolean radioButtonVisibility, org.wordpress.android.ui.utils.UiString subTitle, boolean clickable) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public java.lang.String getName() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public java.lang.String getDomain() {
                return null;
            }
            
            public boolean getChecked() {
                return false;
            }
            
            public final boolean getRadioButtonVisibility() {
                return false;
            }
            
            @org.jetbrains.annotations.Nullable()
            public org.wordpress.android.ui.utils.UiString getSubTitle() {
                return null;
            }
            
            @java.lang.Override()
            public boolean getClickable() {
                return false;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState$DomainsModelAvailableUiState;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState;", "name", "", "domain", "checked", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getChecked", "()Z", "getDomain", "()Ljava/lang/String;", "getName", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class DomainsModelAvailableUiState extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState.DomainsModelUiState {
                @org.jetbrains.annotations.NotNull()
                private final java.lang.String name = null;
                @org.jetbrains.annotations.NotNull()
                private final java.lang.String domain = null;
                private final boolean checked = false;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState.DomainsModelUiState.DomainsModelAvailableUiState copy(@org.jetbrains.annotations.NotNull()
                java.lang.String name, @org.jetbrains.annotations.NotNull()
                java.lang.String domain, boolean checked) {
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
                
                public DomainsModelAvailableUiState(@org.jetbrains.annotations.NotNull()
                java.lang.String name, @org.jetbrains.annotations.NotNull()
                java.lang.String domain, boolean checked) {
                    super(null, null, false, false, null, false);
                }
                
                @org.jetbrains.annotations.NotNull()
                public final java.lang.String component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.String getName() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final java.lang.String component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.String getDomain() {
                    return null;
                }
                
                public final boolean component3() {
                    return false;
                }
                
                @java.lang.Override()
                public boolean getChecked() {
                    return false;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState$DomainsModelUnavailabilityUiState;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsModelUiState;", "name", "", "domain", "subTitle", "Lorg/wordpress/android/ui/utils/UiString;", "(Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/utils/UiString;)V", "getDomain", "()Ljava/lang/String;", "getName", "getSubTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class DomainsModelUnavailabilityUiState extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState.DomainsModelUiState {
                @org.jetbrains.annotations.NotNull()
                private final java.lang.String name = null;
                @org.jetbrains.annotations.NotNull()
                private final java.lang.String domain = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString subTitle = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState.DomainsModelUiState.DomainsModelUnavailabilityUiState copy(@org.jetbrains.annotations.NotNull()
                java.lang.String name, @org.jetbrains.annotations.NotNull()
                java.lang.String domain, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString subTitle) {
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
                
                public DomainsModelUnavailabilityUiState(@org.jetbrains.annotations.NotNull()
                java.lang.String name, @org.jetbrains.annotations.NotNull()
                java.lang.String domain, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString subTitle) {
                    super(null, null, false, false, null, false);
                }
                
                @org.jetbrains.annotations.NotNull()
                public final java.lang.String component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.String getName() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final java.lang.String component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.String getDomain() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString component3() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString getSubTitle() {
                    return null;
                }
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState$DomainsFetchSuggestionsErrorUiState;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainsListItemUiState;", "messageResId", "", "retryButtonResId", "(II)V", "getMessageResId", "()I", "getRetryButtonResId", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DomainsFetchSuggestionsErrorUiState extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState {
            private final int messageResId = 0;
            private final int retryButtonResId = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainsListItemUiState.DomainsFetchSuggestionsErrorUiState copy(@androidx.annotation.StringRes()
            int messageResId, @androidx.annotation.StringRes()
            int retryButtonResId) {
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
            
            public DomainsFetchSuggestionsErrorUiState(@androidx.annotation.StringRes()
            int messageResId, @androidx.annotation.StringRes()
            int retryButtonResId) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getMessageResId() {
                return 0;
            }
            
            public final int component2() {
                return 0;
            }
            
            public final int getRetryButtonResId() {
                return 0;
            }
        }
    }
    
    /**
     * An organized way to separate user initiated searches from automatic searches so we can handle them differently.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0001\u0007B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainSuggestionsQuery;", "", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "UserQuery", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainSuggestionsQuery$UserQuery;", "org.wordpress.android_wordpressJalapenoDebug"})
    static abstract class DomainSuggestionsQuery {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        private DomainSuggestionsQuery(java.lang.String value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        /**
         * User initiated search.
         */
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainSuggestionsQuery$UserQuery;", "Lorg/wordpress/android/ui/sitecreation/domains/SiteCreationDomainsViewModel$DomainSuggestionsQuery;", "value", "", "(Ljava/lang/String;)V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UserQuery extends org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel.DomainSuggestionsQuery {
            
            public UserQuery(@org.jetbrains.annotations.NotNull()
            java.lang.String value) {
                super(null);
            }
        }
    }
}
package org.wordpress.android.ui.suggestion;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020 H\u0016J\b\u0010$\u001a\u00020 H\u0016J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&2\u0006\u0010(\u001a\u00020)H\u0002R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001cX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/suggestion/XPostsSuggestionSource;", "Lorg/wordpress/android/ui/suggestion/SuggestionSource;", "Lkotlinx/coroutines/CoroutineScope;", "xPostsStore", "Lorg/wordpress/android/fluxc/store/XPostsStore;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/XPostsStore;Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_suggestions", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/suggestion/SuggestionResult;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "fetchJob", "Lkotlinx/coroutines/Job;", "getFetchJob$annotations", "()V", "getFetchJob", "()Lkotlinx/coroutines/Job;", "setFetchJob", "(Lkotlinx/coroutines/Job;)V", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "suggestionData", "Landroidx/lifecycle/LiveData;", "getSuggestionData", "()Landroidx/lifecycle/LiveData;", "initialize", "", "isFetchInProgress", "", "onCleared", "refreshSuggestions", "suggestionsFromResult", "", "Lorg/wordpress/android/ui/suggestion/Suggestion;", "result", "Lorg/wordpress/android/fluxc/store/XPostsResult$Result;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class XPostsSuggestionSource implements org.wordpress.android.ui.suggestion.SuggestionSource, kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.fluxc.store.XPostsStore xPostsStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.coroutines.CoroutineContext coroutineContext = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.suggestion.SuggestionResult> _suggestions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.suggestion.SuggestionResult> suggestionData = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job fetchJob;
    
    @javax.inject.Inject()
    public XPostsSuggestionSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.XPostsStore xPostsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.suggestion.SuggestionResult> getSuggestionData() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlinx.coroutines.Job getFetchJob() {
        return null;
    }
    
    @androidx.annotation.VisibleForTesting()
    @java.lang.Deprecated()
    public static void getFetchJob$annotations() {
    }
    
    public final void setFetchJob(@org.jetbrains.annotations.Nullable()
    kotlinx.coroutines.Job p0) {
    }
    
    @java.lang.Override()
    public void initialize() {
    }
    
    private final java.util.List<org.wordpress.android.ui.suggestion.Suggestion> suggestionsFromResult(org.wordpress.android.fluxc.store.XPostsResult.Result result) {
        return null;
    }
    
    @java.lang.Override()
    public void refreshSuggestions() {
    }
    
    @java.lang.Override()
    public boolean isFetchInProgress() {
        return false;
    }
    
    @java.lang.Override()
    public void onCleared() {
    }
}
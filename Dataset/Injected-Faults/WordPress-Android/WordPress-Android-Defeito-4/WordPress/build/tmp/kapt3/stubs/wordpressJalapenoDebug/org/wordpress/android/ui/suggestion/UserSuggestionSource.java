package org.wordpress.android.ui.suggestion;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B)\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0016J\b\u0010 \u001a\u00020\u001eH\u0016J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#H\u0007J\u0010\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u0016H\u0002J\b\u0010&\u001a\u00020\u001eH\u0016R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001aX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/suggestion/UserSuggestionSource;", "Lorg/wordpress/android/ui/suggestion/SuggestionSource;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Landroid/content/Context;Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/util/EventBusWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_suggestions", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/suggestion/SuggestionResult;", "connectionManager", "Lorg/wordpress/android/ui/suggestion/util/SuggestionServiceConnectionManager;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "isFetching", "", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "suggestionData", "Landroidx/lifecycle/LiveData;", "getSuggestionData", "()Landroidx/lifecycle/LiveData;", "initialize", "", "isFetchInProgress", "onCleared", "onEventMainThread", "event", "Lorg/wordpress/android/ui/suggestion/service/SuggestionEvents$SuggestionNameListUpdated;", "postSavedSuggestions", "suggestionsWereJustUpdated", "refreshSuggestions", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UserSuggestionSource implements org.wordpress.android.ui.suggestion.SuggestionSource, kotlinx.coroutines.CoroutineScope {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.coroutines.CoroutineContext coroutineContext = null;
    private final org.wordpress.android.ui.suggestion.util.SuggestionServiceConnectionManager connectionManager = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.suggestion.SuggestionResult> _suggestions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.suggestion.SuggestionResult> suggestionData = null;
    private boolean isFetching = false;
    
    @javax.inject.Inject()
    public UserSuggestionSource(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
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
    
    @java.lang.Override()
    public void initialize() {
    }
    
    private final void postSavedSuggestions(boolean suggestionsWereJustUpdated) {
    }
    
    @java.lang.Override()
    public void refreshSuggestions() {
    }
    
    @org.greenrobot.eventbus.Subscribe()
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.suggestion.service.SuggestionEvents.SuggestionNameListUpdated event) {
    }
    
    @java.lang.Override()
    public boolean isFetchInProgress() {
        return false;
    }
    
    @java.lang.Override()
    public void onCleared() {
    }
}
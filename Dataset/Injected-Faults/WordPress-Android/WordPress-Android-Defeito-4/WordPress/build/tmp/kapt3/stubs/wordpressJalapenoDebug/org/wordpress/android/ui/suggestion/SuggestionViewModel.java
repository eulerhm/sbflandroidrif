package org.wordpress.android.ui.suggestion;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u001f\u001a\u00020 2\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"J\u0016\u0010$\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\'J\u001e\u0010(\u001a\u00020)2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"2\u0006\u0010+\u001a\u00020\u0019J\b\u0010,\u001a\u00020-H\u0014J\u000e\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u000200J\u0010\u00101\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\u0002J\u000e\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020%R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00064"}, d2 = {"Lorg/wordpress/android/ui/suggestion/SuggestionViewModel;", "Landroidx/lifecycle/ViewModel;", "suggestionSourceProvider", "Lorg/wordpress/android/ui/suggestion/SuggestionSourceProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/ui/suggestion/SuggestionSourceProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "suggestionData", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/ui/suggestion/SuggestionResult;", "getSuggestionData", "()Landroidx/lifecycle/LiveData;", "suggestionPrefix", "", "getSuggestionPrefix", "()C", "suggestionPrefix$delegate", "Lkotlin/Lazy;", "suggestionSource", "Lorg/wordpress/android/ui/suggestion/SuggestionSource;", "suggestionTypeString", "", "getSuggestionTypeString", "()Ljava/lang/String;", "suggestionTypeString$delegate", "type", "Lorg/wordpress/android/ui/suggestion/SuggestionType;", "getEmptyViewState", "Lorg/wordpress/android/ui/suggestion/EmptyViewState;", "displayedSuggestions", "", "Lorg/wordpress/android/ui/suggestion/Suggestion;", "init", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "onAttemptToFinish", "Lorg/wordpress/android/ui/suggestion/FinishAttempt;", "selections", "currentUserInput", "onCleared", "", "onConnectionChanged", "event", "Lorg/wordpress/android/networking/ConnectionChangeReceiver$ConnectionChangeEvent;", "supportsSuggestions", "trackExit", "withSuggestion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SuggestionViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.ui.suggestion.SuggestionSourceProvider suggestionSourceProvider = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private org.wordpress.android.ui.suggestion.SuggestionSource suggestionSource;
    private org.wordpress.android.ui.suggestion.SuggestionType type;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy suggestionPrefix$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy suggestionTypeString$delegate = null;
    
    @javax.inject.Inject()
    public SuggestionViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.suggestion.SuggestionSourceProvider suggestionSourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.suggestion.SuggestionResult> getSuggestionData() {
        return null;
    }
    
    public final char getSuggestionPrefix() {
        return '\u0000';
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSuggestionTypeString() {
        return null;
    }
    
    public final boolean init(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.suggestion.SuggestionType type, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return false;
    }
    
    private final boolean supportsSuggestions(org.wordpress.android.fluxc.model.SiteModel site) {
        return false;
    }
    
    public final void onConnectionChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.networking.ConnectionChangeReceiver.ConnectionChangeEvent event) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.suggestion.EmptyViewState getEmptyViewState(@org.jetbrains.annotations.Nullable()
    java.util.List<org.wordpress.android.ui.suggestion.Suggestion> displayedSuggestions) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.suggestion.FinishAttempt onAttemptToFinish(@org.jetbrains.annotations.Nullable()
    java.util.List<org.wordpress.android.ui.suggestion.Suggestion> selections, @org.jetbrains.annotations.NotNull()
    java.lang.String currentUserInput) {
        return null;
    }
    
    public final void trackExit(boolean withSuggestion) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}
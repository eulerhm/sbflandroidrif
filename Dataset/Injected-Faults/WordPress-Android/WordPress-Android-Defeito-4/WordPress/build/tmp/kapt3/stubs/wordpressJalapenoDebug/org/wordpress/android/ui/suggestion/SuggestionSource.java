package org.wordpress.android.ui.suggestion;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\fH&J\b\u0010\u0010\u001a\u00020\fH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/suggestion/SuggestionSource;", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "suggestionData", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/ui/suggestion/SuggestionResult;", "getSuggestionData", "()Landroidx/lifecycle/LiveData;", "initialize", "", "isFetchInProgress", "", "onCleared", "refreshSuggestions", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface SuggestionSource {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.fluxc.model.SiteModel getSite();
    
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<org.wordpress.android.ui.suggestion.SuggestionResult> getSuggestionData();
    
    public abstract void initialize();
    
    public abstract void refreshSuggestions();
    
    public abstract boolean isFetchInProgress();
    
    public abstract void onCleared();
}
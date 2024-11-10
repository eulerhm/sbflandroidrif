package org.wordpress.android.ui.suggestion;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/suggestion/SuggestionSourceProvider;", "", "suggestionSourceSubcomponentFactory", "Lorg/wordpress/android/ui/suggestion/SuggestionSourceSubcomponent$Factory;", "(Lorg/wordpress/android/ui/suggestion/SuggestionSourceSubcomponent$Factory;)V", "get", "Lorg/wordpress/android/ui/suggestion/SuggestionSource;", "type", "Lorg/wordpress/android/ui/suggestion/SuggestionType;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SuggestionSourceProvider {
    private final org.wordpress.android.ui.suggestion.SuggestionSourceSubcomponent.Factory suggestionSourceSubcomponentFactory = null;
    
    @javax.inject.Inject()
    public SuggestionSourceProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.suggestion.SuggestionSourceSubcomponent.Factory suggestionSourceSubcomponentFactory) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.suggestion.SuggestionSource get(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.suggestion.SuggestionType type, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
}
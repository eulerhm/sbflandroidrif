package org.wordpress.android.ui.suggestion;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001:\u0002\u0006\u0007J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/suggestion/SuggestionSourceSubcomponent;", "", "userSuggestionSource", "Lorg/wordpress/android/ui/suggestion/UserSuggestionSource;", "xPostSuggestionSource", "Lorg/wordpress/android/ui/suggestion/XPostsSuggestionSource;", "Factory", "SuggestionSourceModule", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Subcomponent()
public abstract interface SuggestionSourceSubcomponent {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.ui.suggestion.UserSuggestionSource userSuggestionSource();
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.ui.suggestion.XPostsSuggestionSource xPostSuggestionSource();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/suggestion/SuggestionSourceSubcomponent$Factory;", "", "create", "Lorg/wordpress/android/ui/suggestion/SuggestionSourceSubcomponent;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    @dagger.Subcomponent.Factory()
    public static abstract interface Factory {
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.suggestion.SuggestionSourceSubcomponent create(@org.jetbrains.annotations.NotNull()
        @dagger.BindsInstance()
        org.wordpress.android.fluxc.model.SiteModel site);
    }
    
    @dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bg\u0018\u00002\u00020\u0001\u00a8\u0006\u0002"}, d2 = {"Lorg/wordpress/android/ui/suggestion/SuggestionSourceSubcomponent$SuggestionSourceModule;", "", "org.wordpress.android_wordpressJalapenoDebug"})
    @dagger.Module(subcomponents = {org.wordpress.android.ui.suggestion.SuggestionSourceSubcomponent.class})
    public static abstract interface SuggestionSourceModule {
    }
}
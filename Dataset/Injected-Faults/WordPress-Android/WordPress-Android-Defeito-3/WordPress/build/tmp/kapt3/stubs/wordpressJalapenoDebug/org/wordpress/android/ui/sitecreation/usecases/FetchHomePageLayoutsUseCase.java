package org.wordpress.android.ui.sitecreation.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0017B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0011\u0010\u0012\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rH\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/usecases/FetchHomePageLayoutsUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "themeStore", "Lorg/wordpress/android/fluxc/store/ThemeStore;", "thumbDimensionProvider", "Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignPickerDimensionProvider;", "betaSiteDesigns", "Lorg/wordpress/android/util/config/BetaSiteDesignsFeatureConfig;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/ThemeStore;Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignPickerDimensionProvider;Lorg/wordpress/android/util/config/BetaSiteDesignsFeatureConfig;)V", "continuation", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/ThemeStore$OnStarterDesignsFetched;", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "getThemeStore", "()Lorg/wordpress/android/fluxc/store/ThemeStore;", "fetchStarterDesigns", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onStarterDesignsFetched", "", "event", "GROUP", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FetchHomePageLayoutsUseCase {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.ThemeStore themeStore = null;
    private final org.wordpress.android.ui.sitecreation.theme.SiteDesignPickerDimensionProvider thumbDimensionProvider = null;
    private final org.wordpress.android.util.config.BetaSiteDesignsFeatureConfig betaSiteDesigns = null;
    private kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.ThemeStore.OnStarterDesignsFetched> continuation;
    
    @javax.inject.Inject()
    public FetchHomePageLayoutsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"unused"})
    org.wordpress.android.fluxc.store.ThemeStore themeStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.theme.SiteDesignPickerDimensionProvider thumbDimensionProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.BetaSiteDesignsFeatureConfig betaSiteDesigns) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.ThemeStore getThemeStore() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchStarterDesigns(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.ThemeStore.OnStarterDesignsFetched> continuation) {
        return null;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStarterDesignsFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ThemeStore.OnStarterDesignsFetched event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/usecases/FetchHomePageLayoutsUseCase$GROUP;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "STABLE", "BETA", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum GROUP {
        /*public static final*/ STABLE /* = new STABLE(null) */,
        /*public static final*/ BETA /* = new BETA(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String key = null;
        
        GROUP(java.lang.String key) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getKey() {
            return null;
        }
    }
}
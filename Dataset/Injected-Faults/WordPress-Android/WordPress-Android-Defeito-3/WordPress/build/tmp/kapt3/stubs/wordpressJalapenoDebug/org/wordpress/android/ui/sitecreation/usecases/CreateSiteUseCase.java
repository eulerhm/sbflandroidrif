package org.wordpress.android.ui.sitecreation.usecases;

import java.lang.System;

/**
 * Transforms OnNewSiteCreated EventBus event to a coroutine.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ=\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000bH\u0007R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/usecases/CreateSiteUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "urlUtilsWrapper", "Lorg/wordpress/android/util/UrlUtilsWrapper;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/util/UrlUtilsWrapper;)V", "continuation", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/SiteStore$OnNewSiteCreated;", "createSite", "siteData", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceData;", "languageWordPressId", "", "timeZoneId", "siteVisibility", "Lorg/wordpress/android/fluxc/store/SiteStore$SiteVisibility;", "dryRun", "", "(Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceData;Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/fluxc/store/SiteStore$SiteVisibility;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onNewSiteCreated", "", "event", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CreateSiteUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.util.UrlUtilsWrapper urlUtilsWrapper = null;
    private kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.SiteStore.OnNewSiteCreated> continuation;
    
    @javax.inject.Inject()
    public CreateSiteUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"unused"})
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UrlUtilsWrapper urlUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData siteData, @org.jetbrains.annotations.NotNull()
    java.lang.String languageWordPressId, @org.jetbrains.annotations.NotNull()
    java.lang.String timeZoneId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.SiteVisibility siteVisibility, boolean dryRun, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.SiteStore.OnNewSiteCreated> continuation) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onNewSiteCreated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnNewSiteCreated event) {
    }
}
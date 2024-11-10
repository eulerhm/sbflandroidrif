package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B_\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\u0002\u0010\u0018J+\u0010&\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020(0\'0\u001a2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,\u00a2\u0006\u0002\u0010-J\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020 J\u0019\u00101\u001a\u00020/2\u0006\u00102\u001a\u000203H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00104J\u000e\u00105\u001a\u00020/2\u0006\u00106\u001a\u00020 J\u0006\u00107\u001a\u00020/J\u000e\u00108\u001a\u00020/2\u0006\u00109\u001a\u00020 J\u0006\u0010:\u001a\u00020/J\b\u0010;\u001a\u00020/H\u0002R\u001e\u0010\u0019\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b0\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020 8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u001e\u0010#\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030$0\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006<"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteSourceManager;", "", "quickStartTracker", "Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "currentAvatarSource", "Lorg/wordpress/android/ui/mysite/CurrentAvatarSource;", "domainRegistrationSource", "Lorg/wordpress/android/ui/mysite/cards/domainregistration/DomainRegistrationSource;", "dynamicCardsSource", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardsSource;", "quickStartCardSource", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardSource;", "scanAndBackupSource", "Lorg/wordpress/android/ui/mysite/ScanAndBackupSource;", "selectedSiteSource", "Lorg/wordpress/android/ui/mysite/SelectedSiteSource;", "cardsSource", "Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsSource;", "siteIconProgressSource", "Lorg/wordpress/android/ui/mysite/SiteIconProgressSource;", "bloggingPromptCardSource", "Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptCardSource;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "(Lorg/wordpress/android/ui/quickstart/QuickStartTracker;Lorg/wordpress/android/ui/mysite/CurrentAvatarSource;Lorg/wordpress/android/ui/mysite/cards/domainregistration/DomainRegistrationSource;Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardsSource;Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardSource;Lorg/wordpress/android/ui/mysite/ScanAndBackupSource;Lorg/wordpress/android/ui/mysite/SelectedSiteSource;Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsSource;Lorg/wordpress/android/ui/mysite/SiteIconProgressSource;Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptCardSource;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "allSupportedMySiteSources", "", "Lorg/wordpress/android/ui/mysite/MySiteSource;", "getAllSupportedMySiteSources", "()Ljava/util/List;", "mySiteSources", "showDashboardCards", "", "getShowDashboardCards", "()Z", "siteIndependentSources", "Lorg/wordpress/android/ui/mysite/MySiteSource$SiteIndependentSource;", "getSiteIndependentSources", "build", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "(Lkotlinx/coroutines/CoroutineScope;Ljava/lang/Integer;)Ljava/util/List;", "clear", "", "isRefreshing", "onQuickStartMenuInteraction", "interaction", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;", "(Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResume", "isSiteSelected", "refresh", "refreshBloggingPrompts", "onlyCurrentPrompt", "refreshQuickStart", "refreshSubsetOfAllSources", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteSourceManager {
    private final org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker = null;
    private final org.wordpress.android.ui.mysite.CurrentAvatarSource currentAvatarSource = null;
    private final org.wordpress.android.ui.mysite.cards.domainregistration.DomainRegistrationSource domainRegistrationSource = null;
    private final org.wordpress.android.ui.mysite.dynamiccards.DynamicCardsSource dynamicCardsSource = null;
    private final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardSource quickStartCardSource = null;
    private final org.wordpress.android.ui.mysite.ScanAndBackupSource scanAndBackupSource = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteSource selectedSiteSource = null;
    private final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptCardSource bloggingPromptCardSource = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteSource<?>> mySiteSources = null;
    
    @javax.inject.Inject()
    public MySiteSourceManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.CurrentAvatarSource currentAvatarSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.domainregistration.DomainRegistrationSource domainRegistrationSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.dynamiccards.DynamicCardsSource dynamicCardsSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardSource quickStartCardSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.ScanAndBackupSource scanAndBackupSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteSource selectedSiteSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.CardsSource cardsSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SiteIconProgressSource siteIconProgressSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptCardSource bloggingPromptCardSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository) {
        super();
    }
    
    private final boolean getShowDashboardCards() {
        return false;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteSource<?>> getAllSupportedMySiteSources() {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteSource.SiteIndependentSource<?>> getSiteIndependentSources() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<androidx.lifecycle.LiveData<? extends org.wordpress.android.ui.mysite.MySiteUiState.PartialState>> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, @org.jetbrains.annotations.Nullable()
    java.lang.Integer siteLocalId) {
        return null;
    }
    
    public final boolean isRefreshing() {
        return false;
    }
    
    public final void refresh() {
    }
    
    public final void onResume(boolean isSiteSelected) {
    }
    
    public final void clear() {
    }
    
    private final void refreshSubsetOfAllSources() {
    }
    
    public final void refreshBloggingPrompts(boolean onlyCurrentPrompt) {
    }
    
    public final void refreshQuickStart() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object onQuickStartMenuInteraction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction interaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}
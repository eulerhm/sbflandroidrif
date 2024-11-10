package org.wordpress.android.ui.mysite.cards;

import java.lang.System;

@kotlin.Suppress(names = {"LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J<\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/CardsBuilder;", "", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "quickStartDynamicCardsFeatureConfig", "Lorg/wordpress/android/util/config/QuickStartDynamicCardsFeatureConfig;", "quickActionsCardBuilder", "Lorg/wordpress/android/ui/mysite/cards/quickactions/QuickActionsCardBuilder;", "quickStartCardBuilder", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder;", "quickLinkRibbonBuilder", "Lorg/wordpress/android/ui/mysite/cards/quicklinksribbon/QuickLinkRibbonBuilder;", "dashboardCardsBuilder", "Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsBuilder;", "mySiteDefaultTabExperiment", "Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;", "(Lorg/wordpress/android/util/BuildConfigWrapper;Lorg/wordpress/android/util/config/QuickStartDynamicCardsFeatureConfig;Lorg/wordpress/android/ui/mysite/cards/quickactions/QuickActionsCardBuilder;Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder;Lorg/wordpress/android/ui/mysite/cards/quicklinksribbon/QuickLinkRibbonBuilder;Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsBuilder;Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;)V", "build", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "quickActionsCardBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickActionsCardBuilderParams;", "domainRegistrationCardBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$DomainRegistrationCardBuilderParams;", "quickStartCardBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickStartCardBuilderParams;", "dashboardCardsBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$DashboardCardsBuilderParams;", "quickLinkRibbonBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickLinkRibbonBuilderParams;", "isMySiteTabsEnabled", "", "shouldShowQuickActionsCard", "trackAndBuildDomainRegistrationCard", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DomainRegistrationCard;", "params", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CardsBuilder {
    private final org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper = null;
    private final org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig = null;
    private final org.wordpress.android.ui.mysite.cards.quickactions.QuickActionsCardBuilder quickActionsCardBuilder = null;
    private final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder quickStartCardBuilder = null;
    private final org.wordpress.android.ui.mysite.cards.quicklinksribbon.QuickLinkRibbonBuilder quickLinkRibbonBuilder = null;
    private final org.wordpress.android.ui.mysite.cards.dashboard.CardsBuilder dashboardCardsBuilder = null;
    private final org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment mySiteDefaultTabExperiment = null;
    
    @javax.inject.Inject()
    public CardsBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickactions.QuickActionsCardBuilder quickActionsCardBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder quickStartCardBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quicklinksribbon.QuickLinkRibbonBuilder quickLinkRibbonBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.CardsBuilder dashboardCardsBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment mySiteDefaultTabExperiment) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> build(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.QuickActionsCardBuilderParams quickActionsCardBuilderParams, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.DomainRegistrationCardBuilderParams domainRegistrationCardBuilderParams, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.QuickStartCardBuilderParams quickStartCardBuilderParams, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.DashboardCardsBuilderParams dashboardCardsBuilderParams, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.QuickLinkRibbonBuilderParams quickLinkRibbonBuilderParams, boolean isMySiteTabsEnabled) {
        return null;
    }
    
    private final boolean shouldShowQuickActionsCard(boolean isMySiteTabsEnabled) {
        return false;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DomainRegistrationCard trackAndBuildDomainRegistrationCard(org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.DomainRegistrationCardBuilderParams params) {
        return null;
    }
}
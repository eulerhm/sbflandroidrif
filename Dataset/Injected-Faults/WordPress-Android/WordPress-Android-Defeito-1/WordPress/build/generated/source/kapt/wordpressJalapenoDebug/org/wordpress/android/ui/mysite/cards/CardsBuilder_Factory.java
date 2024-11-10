package org.wordpress.android.ui.mysite.cards;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.cards.quickactions.QuickActionsCardBuilder;
import org.wordpress.android.ui.mysite.cards.quicklinksribbon.QuickLinkRibbonBuilder;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder;
import org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CardsBuilder_Factory implements Factory<CardsBuilder> {
  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  private final Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider;

  private final Provider<QuickActionsCardBuilder> quickActionsCardBuilderProvider;

  private final Provider<QuickStartCardBuilder> quickStartCardBuilderProvider;

  private final Provider<QuickLinkRibbonBuilder> quickLinkRibbonBuilderProvider;

  private final Provider<org.wordpress.android.ui.mysite.cards.dashboard.CardsBuilder> dashboardCardsBuilderProvider;

  private final Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider;

  public CardsBuilder_Factory(Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider,
      Provider<QuickActionsCardBuilder> quickActionsCardBuilderProvider,
      Provider<QuickStartCardBuilder> quickStartCardBuilderProvider,
      Provider<QuickLinkRibbonBuilder> quickLinkRibbonBuilderProvider,
      Provider<org.wordpress.android.ui.mysite.cards.dashboard.CardsBuilder> dashboardCardsBuilderProvider,
      Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider) {
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
    this.quickStartDynamicCardsFeatureConfigProvider = quickStartDynamicCardsFeatureConfigProvider;
    this.quickActionsCardBuilderProvider = quickActionsCardBuilderProvider;
    this.quickStartCardBuilderProvider = quickStartCardBuilderProvider;
    this.quickLinkRibbonBuilderProvider = quickLinkRibbonBuilderProvider;
    this.dashboardCardsBuilderProvider = dashboardCardsBuilderProvider;
    this.mySiteDefaultTabExperimentProvider = mySiteDefaultTabExperimentProvider;
  }

  @Override
  public CardsBuilder get() {
    return newInstance(buildConfigWrapperProvider.get(), quickStartDynamicCardsFeatureConfigProvider.get(), quickActionsCardBuilderProvider.get(), quickStartCardBuilderProvider.get(), quickLinkRibbonBuilderProvider.get(), dashboardCardsBuilderProvider.get(), mySiteDefaultTabExperimentProvider.get());
  }

  public static CardsBuilder_Factory create(Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider,
      Provider<QuickActionsCardBuilder> quickActionsCardBuilderProvider,
      Provider<QuickStartCardBuilder> quickStartCardBuilderProvider,
      Provider<QuickLinkRibbonBuilder> quickLinkRibbonBuilderProvider,
      Provider<org.wordpress.android.ui.mysite.cards.dashboard.CardsBuilder> dashboardCardsBuilderProvider,
      Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider) {
    return new CardsBuilder_Factory(buildConfigWrapperProvider, quickStartDynamicCardsFeatureConfigProvider, quickActionsCardBuilderProvider, quickStartCardBuilderProvider, quickLinkRibbonBuilderProvider, dashboardCardsBuilderProvider, mySiteDefaultTabExperimentProvider);
  }

  public static CardsBuilder newInstance(BuildConfigWrapper buildConfigWrapper,
      QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig,
      QuickActionsCardBuilder quickActionsCardBuilder, QuickStartCardBuilder quickStartCardBuilder,
      QuickLinkRibbonBuilder quickLinkRibbonBuilder,
      org.wordpress.android.ui.mysite.cards.dashboard.CardsBuilder dashboardCardsBuilder,
      MySiteDefaultTabExperiment mySiteDefaultTabExperiment) {
    return new CardsBuilder(buildConfigWrapper, quickStartDynamicCardsFeatureConfig, quickActionsCardBuilder, quickStartCardBuilder, quickLinkRibbonBuilder, dashboardCardsBuilder, mySiteDefaultTabExperiment);
  }
}

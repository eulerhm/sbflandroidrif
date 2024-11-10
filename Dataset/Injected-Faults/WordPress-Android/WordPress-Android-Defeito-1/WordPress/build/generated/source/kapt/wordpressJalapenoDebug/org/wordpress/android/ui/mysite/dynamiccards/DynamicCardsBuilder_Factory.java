package org.wordpress.android.ui.mysite.dynamiccards;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.dynamiccards.quickstart.QuickStartItemBuilder;
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
public final class DynamicCardsBuilder_Factory implements Factory<DynamicCardsBuilder> {
  private final Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider;

  private final Provider<QuickStartItemBuilder> quickStartItemBuilderProvider;

  public DynamicCardsBuilder_Factory(
      Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider,
      Provider<QuickStartItemBuilder> quickStartItemBuilderProvider) {
    this.quickStartDynamicCardsFeatureConfigProvider = quickStartDynamicCardsFeatureConfigProvider;
    this.quickStartItemBuilderProvider = quickStartItemBuilderProvider;
  }

  @Override
  public DynamicCardsBuilder get() {
    return newInstance(quickStartDynamicCardsFeatureConfigProvider.get(), quickStartItemBuilderProvider.get());
  }

  public static DynamicCardsBuilder_Factory create(
      Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider,
      Provider<QuickStartItemBuilder> quickStartItemBuilderProvider) {
    return new DynamicCardsBuilder_Factory(quickStartDynamicCardsFeatureConfigProvider, quickStartItemBuilderProvider);
  }

  public static DynamicCardsBuilder newInstance(
      QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig,
      QuickStartItemBuilder quickStartItemBuilder) {
    return new DynamicCardsBuilder(quickStartDynamicCardsFeatureConfig, quickStartItemBuilder);
  }
}

package org.wordpress.android.ui.sitecreation.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.ThemeStore;
import org.wordpress.android.ui.sitecreation.theme.SiteDesignPickerDimensionProvider;
import org.wordpress.android.util.config.BetaSiteDesignsFeatureConfig;

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
public final class FetchHomePageLayoutsUseCase_Factory implements Factory<FetchHomePageLayoutsUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ThemeStore> themeStoreProvider;

  private final Provider<SiteDesignPickerDimensionProvider> thumbDimensionProvider;

  private final Provider<BetaSiteDesignsFeatureConfig> betaSiteDesignsProvider;

  public FetchHomePageLayoutsUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<ThemeStore> themeStoreProvider,
      Provider<SiteDesignPickerDimensionProvider> thumbDimensionProvider,
      Provider<BetaSiteDesignsFeatureConfig> betaSiteDesignsProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.themeStoreProvider = themeStoreProvider;
    this.thumbDimensionProvider = thumbDimensionProvider;
    this.betaSiteDesignsProvider = betaSiteDesignsProvider;
  }

  @Override
  public FetchHomePageLayoutsUseCase get() {
    return newInstance(dispatcherProvider.get(), themeStoreProvider.get(), thumbDimensionProvider.get(), betaSiteDesignsProvider.get());
  }

  public static FetchHomePageLayoutsUseCase_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<ThemeStore> themeStoreProvider,
      Provider<SiteDesignPickerDimensionProvider> thumbDimensionProvider,
      Provider<BetaSiteDesignsFeatureConfig> betaSiteDesignsProvider) {
    return new FetchHomePageLayoutsUseCase_Factory(dispatcherProvider, themeStoreProvider, thumbDimensionProvider, betaSiteDesignsProvider);
  }

  public static FetchHomePageLayoutsUseCase newInstance(Dispatcher dispatcher,
      ThemeStore themeStore, SiteDesignPickerDimensionProvider thumbDimensionProvider,
      BetaSiteDesignsFeatureConfig betaSiteDesigns) {
    return new FetchHomePageLayoutsUseCase(dispatcher, themeStore, thumbDimensionProvider, betaSiteDesigns);
  }
}

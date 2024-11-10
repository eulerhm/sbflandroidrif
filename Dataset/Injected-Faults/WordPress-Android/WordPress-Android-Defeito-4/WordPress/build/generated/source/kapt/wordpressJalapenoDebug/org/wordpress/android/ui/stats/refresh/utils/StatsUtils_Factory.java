package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class StatsUtils_Factory implements Factory<StatsUtils> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<LocaleManagerWrapper> localeManagerProvider;

  public StatsUtils_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<LocaleManagerWrapper> localeManagerProvider) {
    this.resourceProvider = resourceProvider;
    this.localeManagerProvider = localeManagerProvider;
  }

  @Override
  public StatsUtils get() {
    return newInstance(resourceProvider.get(), localeManagerProvider.get());
  }

  public static StatsUtils_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<LocaleManagerWrapper> localeManagerProvider) {
    return new StatsUtils_Factory(resourceProvider, localeManagerProvider);
  }

  public static StatsUtils newInstance(ResourceProvider resourceProvider,
      LocaleManagerWrapper localeManager) {
    return new StatsUtils(resourceProvider, localeManager);
  }
}

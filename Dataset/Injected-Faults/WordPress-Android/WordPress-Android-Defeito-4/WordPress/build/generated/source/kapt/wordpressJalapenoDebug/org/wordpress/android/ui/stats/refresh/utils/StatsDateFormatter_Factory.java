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
public final class StatsDateFormatter_Factory implements Factory<StatsDateFormatter> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public StatsDateFormatter_Factory(Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public StatsDateFormatter get() {
    return newInstance(localeManagerWrapperProvider.get(), resourceProvider.get());
  }

  public static StatsDateFormatter_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ResourceProvider> resourceProvider) {
    return new StatsDateFormatter_Factory(localeManagerWrapperProvider, resourceProvider);
  }

  public static StatsDateFormatter newInstance(LocaleManagerWrapper localeManagerWrapper,
      ResourceProvider resourceProvider) {
    return new StatsDateFormatter(localeManagerWrapper, resourceProvider);
  }
}

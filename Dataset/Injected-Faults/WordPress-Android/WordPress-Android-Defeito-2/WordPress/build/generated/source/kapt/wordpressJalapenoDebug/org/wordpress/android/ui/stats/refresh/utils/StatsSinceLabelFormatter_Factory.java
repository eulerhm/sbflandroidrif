package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class StatsSinceLabelFormatter_Factory implements Factory<StatsSinceLabelFormatter> {
  private final Provider<ResourceProvider> resourceProvider;

  public StatsSinceLabelFormatter_Factory(Provider<ResourceProvider> resourceProvider) {
    this.resourceProvider = resourceProvider;
  }

  @Override
  public StatsSinceLabelFormatter get() {
    return newInstance(resourceProvider.get());
  }

  public static StatsSinceLabelFormatter_Factory create(
      Provider<ResourceProvider> resourceProvider) {
    return new StatsSinceLabelFormatter_Factory(resourceProvider);
  }

  public static StatsSinceLabelFormatter newInstance(ResourceProvider resourceProvider) {
    return new StatsSinceLabelFormatter(resourceProvider);
  }
}

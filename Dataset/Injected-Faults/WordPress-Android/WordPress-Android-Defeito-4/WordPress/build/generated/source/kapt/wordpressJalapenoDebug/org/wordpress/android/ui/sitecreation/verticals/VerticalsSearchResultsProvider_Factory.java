package org.wordpress.android.ui.sitecreation.verticals;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleManagerWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class VerticalsSearchResultsProvider_Factory implements Factory<VerticalsSearchResultsProvider> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public VerticalsSearchResultsProvider_Factory(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  @Override
  public VerticalsSearchResultsProvider get() {
    return newInstance(localeManagerWrapperProvider.get());
  }

  public static VerticalsSearchResultsProvider_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new VerticalsSearchResultsProvider_Factory(localeManagerWrapperProvider);
  }

  public static VerticalsSearchResultsProvider newInstance(
      LocaleManagerWrapper localeManagerWrapper) {
    return new VerticalsSearchResultsProvider(localeManagerWrapper);
  }
}

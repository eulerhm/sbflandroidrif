package org.wordpress.android.ui.bloggingreminders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleManagerWrapper;

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
public final class DaysProvider_Factory implements Factory<DaysProvider> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public DaysProvider_Factory(Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  @Override
  public DaysProvider get() {
    return newInstance(localeManagerWrapperProvider.get());
  }

  public static DaysProvider_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new DaysProvider_Factory(localeManagerWrapperProvider);
  }

  public static DaysProvider newInstance(LocaleManagerWrapper localeManagerWrapper) {
    return new DaysProvider(localeManagerWrapper);
  }
}

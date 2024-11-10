package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class DateUtils_Factory implements Factory<DateUtils> {
  private final Provider<ContextProvider> contextProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public DateUtils_Factory(Provider<ContextProvider> contextProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.contextProvider = contextProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  @Override
  public DateUtils get() {
    return newInstance(contextProvider.get(), localeManagerWrapperProvider.get());
  }

  public static DateUtils_Factory create(Provider<ContextProvider> contextProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new DateUtils_Factory(contextProvider, localeManagerWrapperProvider);
  }

  public static DateUtils newInstance(ContextProvider contextProvider,
      LocaleManagerWrapper localeManagerWrapper) {
    return new DateUtils(contextProvider, localeManagerWrapper);
  }
}

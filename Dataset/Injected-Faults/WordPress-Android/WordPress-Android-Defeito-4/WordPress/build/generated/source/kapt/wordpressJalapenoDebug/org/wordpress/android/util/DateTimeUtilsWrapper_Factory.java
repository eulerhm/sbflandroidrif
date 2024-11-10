package org.wordpress.android.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class DateTimeUtilsWrapper_Factory implements Factory<DateTimeUtilsWrapper> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<Context> appContextProvider;

  public DateTimeUtilsWrapper_Factory(Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<Context> appContextProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.appContextProvider = appContextProvider;
  }

  @Override
  public DateTimeUtilsWrapper get() {
    return newInstance(localeManagerWrapperProvider.get(), appContextProvider.get());
  }

  public static DateTimeUtilsWrapper_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<Context> appContextProvider) {
    return new DateTimeUtilsWrapper_Factory(localeManagerWrapperProvider, appContextProvider);
  }

  public static DateTimeUtilsWrapper newInstance(LocaleManagerWrapper localeManagerWrapper,
      Context appContext) {
    return new DateTimeUtilsWrapper(localeManagerWrapper, appContext);
  }
}

package org.wordpress.android.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class DateFormatWrapper_Factory implements Factory<DateFormatWrapper> {
  private final Provider<Context> appContextProvider;

  public DateFormatWrapper_Factory(Provider<Context> appContextProvider) {
    this.appContextProvider = appContextProvider;
  }

  @Override
  public DateFormatWrapper get() {
    return newInstance(appContextProvider.get());
  }

  public static DateFormatWrapper_Factory create(Provider<Context> appContextProvider) {
    return new DateFormatWrapper_Factory(appContextProvider);
  }

  public static DateFormatWrapper newInstance(Context appContext) {
    return new DateFormatWrapper(appContext);
  }
}

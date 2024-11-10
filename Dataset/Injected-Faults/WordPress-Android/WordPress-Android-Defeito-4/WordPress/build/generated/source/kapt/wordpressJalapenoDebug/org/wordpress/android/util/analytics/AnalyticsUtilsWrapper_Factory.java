package org.wordpress.android.util.analytics;

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
public final class AnalyticsUtilsWrapper_Factory implements Factory<AnalyticsUtilsWrapper> {
  private final Provider<Context> appContextProvider;

  public AnalyticsUtilsWrapper_Factory(Provider<Context> appContextProvider) {
    this.appContextProvider = appContextProvider;
  }

  @Override
  public AnalyticsUtilsWrapper get() {
    return newInstance(appContextProvider.get());
  }

  public static AnalyticsUtilsWrapper_Factory create(Provider<Context> appContextProvider) {
    return new AnalyticsUtilsWrapper_Factory(appContextProvider);
  }

  public static AnalyticsUtilsWrapper newInstance(Context appContext) {
    return new AnalyticsUtilsWrapper(appContext);
  }
}

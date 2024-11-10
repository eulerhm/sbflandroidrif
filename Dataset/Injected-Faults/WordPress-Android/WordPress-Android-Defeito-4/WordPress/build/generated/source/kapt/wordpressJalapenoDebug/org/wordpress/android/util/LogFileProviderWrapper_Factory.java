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
public final class LogFileProviderWrapper_Factory implements Factory<LogFileProviderWrapper> {
  private final Provider<Context> contextProvider;

  public LogFileProviderWrapper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LogFileProviderWrapper get() {
    return newInstance(contextProvider.get());
  }

  public static LogFileProviderWrapper_Factory create(Provider<Context> contextProvider) {
    return new LogFileProviderWrapper_Factory(contextProvider);
  }

  public static LogFileProviderWrapper newInstance(Context context) {
    return new LogFileProviderWrapper(context);
  }
}

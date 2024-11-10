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
public final class LocaleManagerWrapper_Factory implements Factory<LocaleManagerWrapper> {
  private final Provider<Context> contextProvider;

  public LocaleManagerWrapper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocaleManagerWrapper get() {
    return newInstance(contextProvider.get());
  }

  public static LocaleManagerWrapper_Factory create(Provider<Context> contextProvider) {
    return new LocaleManagerWrapper_Factory(contextProvider);
  }

  public static LocaleManagerWrapper newInstance(Context context) {
    return new LocaleManagerWrapper(context);
  }
}

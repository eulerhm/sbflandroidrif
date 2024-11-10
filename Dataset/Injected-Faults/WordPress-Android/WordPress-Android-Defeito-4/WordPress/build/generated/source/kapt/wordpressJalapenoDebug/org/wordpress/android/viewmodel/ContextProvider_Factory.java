package org.wordpress.android.viewmodel;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ContextProvider_Factory implements Factory<ContextProvider> {
  private final Provider<Context> contextProvider;

  public ContextProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ContextProvider get() {
    return newInstance(contextProvider.get());
  }

  public static ContextProvider_Factory create(Provider<Context> contextProvider) {
    return new ContextProvider_Factory(contextProvider);
  }

  public static ContextProvider newInstance(Context context) {
    return new ContextProvider(context);
  }
}

package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class StorageUtilsProvider_Factory implements Factory<StorageUtilsProvider> {
  private final Provider<ContextProvider> contextProvider;

  public StorageUtilsProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StorageUtilsProvider get() {
    return newInstance(contextProvider.get());
  }

  public static StorageUtilsProvider_Factory create(Provider<ContextProvider> contextProvider) {
    return new StorageUtilsProvider_Factory(contextProvider);
  }

  public static StorageUtilsProvider newInstance(ContextProvider contextProvider) {
    return new StorageUtilsProvider(contextProvider);
  }
}

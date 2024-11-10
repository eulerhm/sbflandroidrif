package org.wordpress.android.viewmodel;

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
public final class ResourceProvider_Factory implements Factory<ResourceProvider> {
  private final Provider<ContextProvider> contextProvider;

  public ResourceProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ResourceProvider get() {
    return newInstance(contextProvider.get());
  }

  public static ResourceProvider_Factory create(Provider<ContextProvider> contextProvider) {
    return new ResourceProvider_Factory(contextProvider);
  }

  public static ResourceProvider newInstance(ContextProvider contextProvider) {
    return new ResourceProvider(contextProvider);
  }
}
